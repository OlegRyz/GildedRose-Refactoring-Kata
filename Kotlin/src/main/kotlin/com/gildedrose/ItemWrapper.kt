package com.gildedrose

import com.gildedrose.ItemWrapper.Companion.AGED_BRIE
import com.gildedrose.ItemWrapper.Companion.BACKSTAGE
import com.gildedrose.ItemWrapper.Companion.SULFURAS

fun wrap(it: Item): ItemWrapper {
    return when (it.name) {
        AGED_BRIE -> AgedBrie(it)
        BACKSTAGE -> BackStage(it)
        SULFURAS -> Sulfuras(it)
        else -> SomeGood(it)
    }
}

class AgedBrie(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityChange(sellIn: Int) = when {
        isExpired -> 2
        else -> 1
    }
}

class BackStage(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityAfterExpiryDate(quality: Int) = 0

    override fun qualityChange(sellIn: Int) = when (sellIn) {
        in 6..10 -> 2
        in 0..5 -> 3
        else -> 1
    }
}

class SomeGood(item: Item) : ItemWrapper(item) {
    override val allowedQualityRange = MIN_QUALITY..Int.MAX_VALUE

    override fun qualityChange(sellIn: Int) = when {
        isExpired -> -2
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
    open val allowedQualityRange = MIN_QUALITY..MAX_QUALITY

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

    protected val isExpired get() = sellIn <= 0

    open fun moveSellInDate() {
        sellIn -= 1
    }

    abstract fun qualityChange(sellIn: Int): Int

    protected open fun qualityAfterExpiryDate(quality: Int) = quality

    fun degrade() {
        if (quality in allowedQualityRange) {
            quality = (quality + qualityChange(sellIn)).coerceIn(allowedQualityRange)
            if (isExpired) {
                quality = qualityAfterExpiryDate(quality)
            }
        }
    }

    companion object {
        const val MIN_QUALITY = 0
        const val MAX_QUALITY = 50
        const val AGED_BRIE = "Aged Brie"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
    }
}