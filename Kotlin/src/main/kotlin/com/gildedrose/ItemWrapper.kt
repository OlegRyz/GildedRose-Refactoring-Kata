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

    override fun qualityAfterSellIn(quality: Int) = 0

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
    open val allowedQualityRange = 0..50

    open fun moveSellInDate() {
        item.sellIn = item.sellIn - 1
    }

    abstract fun qualityChange(sellIn: Int): Int

    open fun qualityAfterSellIn(quality: Int) = quality

    fun degrade() {
        if (item.quality in allowedQualityRange) {
            item.quality = (item.quality + qualityChange(item.sellIn)).coerceIn(allowedQualityRange)
            if (item.sellIn <= 0) {
                item.quality = qualityAfterSellIn(item.quality)
            }
        }
    }
}