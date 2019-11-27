package com.gildedrose

import com.gildedrose.ItemWrapperStrategy.Companion.AGED_BRIE
import com.gildedrose.ItemWrapperStrategy.Companion.BACKSTAGE
import com.gildedrose.ItemWrapperStrategy.Companion.SULFURAS

class AgedBrie : ItemWrapperStrategy() {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityChange(sellIn: SellIn) = when {
        sellIn.isExpired -> 2
        else -> 1
    }
}

class BackStage : ItemWrapperStrategy() {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityAfterExpiryDate(quality: Int) = 0

    override fun qualityChange(sellIn: SellIn) = when (sellIn) {
        in 1..5 -> 3
        in 6..10 -> 2
        else -> 1
    }
}

class SomeGood : ItemWrapperStrategy() {
    override val allowedQualityRange = MIN_QUALITY..Int.MAX_VALUE

    override fun qualityChange(sellIn: SellIn) = when {
        sellIn.isExpired -> -2
        else -> -1
    }
}

class Sulfuras : ItemWrapperStrategy() {
    override fun moveSellInDate(sellIn: SellIn) = sellIn
    override fun qualityChange(sellIn: Int) = 0
}

fun Item.chooseStrategy(): ItemWrapperStrategy {
    return when (name) {
        AGED_BRIE -> AgedBrie()
        BACKSTAGE -> BackStage()
        SULFURAS -> Sulfuras()
        else -> SomeGood()
    }
}

sealed class ItemWrapperStrategy {
    abstract fun qualityChange(sellIn: Int): Int

    @Deprecated("Allowed quality range should not be overridden anymore. " +
                "Currently overriding this range is allowed only for backward compatibility with legacy code.")
    open val allowedQualityRange = MIN_QUALITY..MAX_QUALITY

    open fun moveSellInDate(sellIn: SellIn) = sellIn - 1

    open fun qualityAfterExpiryDate(quality: Int) = quality

    companion object {
        const val MIN_QUALITY = 0
        const val MAX_QUALITY = 50
        const val AGED_BRIE = "Aged Brie"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
    }
}