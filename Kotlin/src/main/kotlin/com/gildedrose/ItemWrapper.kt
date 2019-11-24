package com.gildedrose

fun wrap(it: Item): ItemWrapper {
    return when (it.name) {
        GildedRose.AGED_BRIE -> AgedBrie(it)
        GildedRose.BACKSTAGE -> BackStage(it)
        GildedRose.SULFURAS -> Sulfuras(it)
        else -> SomeGood(it)
    }
}

class AgedBrie(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = Int.MIN_VALUE..50

    override fun qualityChange(sellIn: Int) = when {
        sellIn <= 0 -> 2
        else -> 1
    }
}

class BackStage(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = Int.MIN_VALUE..50

    override fun qualityAfterExpiryDate(quality: Int) = 0

    override fun qualityChange(sellIn: Int) = when (sellIn) {
        in 6..10 -> 2
        in 0..5 -> 3
        else -> 1
    }
}

class SomeGood(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = 0..Int.MAX_VALUE

    override fun qualityChange(sellIn: Int) = when {
        sellIn <= 0 -> -2
        else -> -1
    }
}

class Sulfuras(item: Item) : ItemWrapper(item) {
    override fun moveSellInDate() = Unit
    override fun qualityChange(sellIn: Int) = 0
}

sealed class ItemWrapper(private val item: Item) {
    @Deprecated("Allowed quality range should not be overridden anymore. " +
                "Currently overriding this range is allowed only for backward compatibility with legacy code.")
    open val allowedQualityRange = 0..50

    private var sellIn: Int
    get() = item.sellIn
    set(value) {
        item.sellIn = value
    }

    private var quality: Int
        get() = item.quality
        set(value) {
            item.quality = value
        }

    open fun moveSellInDate() {
        sellIn -= 1
    }

    abstract fun qualityChange(sellIn: Int): Int

    open fun qualityAfterExpiryDate(quality: Int) = quality

    fun degrade() {
        if (quality in allowedQualityRange) {
            quality = (quality + qualityChange(sellIn)).coerceIn(allowedQualityRange)
            if (sellIn <= 0) {
                quality = qualityAfterExpiryDate(quality)
            }
        }
    }
}