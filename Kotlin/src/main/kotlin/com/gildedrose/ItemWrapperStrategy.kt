package com.gildedrose

import com.gildedrose.DefaultStrategy.Companion.MAX_QUALITY
import com.gildedrose.DefaultStrategy.Companion.MIN_QUALITY

class AgedBrie : ItemWrapperStrategy by DefaultStrategy() {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityChange(sellIn: SellIn) = when {
        sellIn.isExpired -> 2
        else -> 1
    }
}

class BackStage : ItemWrapperStrategy by DefaultStrategy() {
    override val allowedQualityRange = Int.MIN_VALUE..MAX_QUALITY

    override fun qualityAfterExpiryDate(quality: Int) = 0

    override fun qualityChange(sellIn: SellIn) = when (sellIn) {
        in 1..5 -> 3
        in 6..10 -> 2
        else -> 1
    }
}

class SomeGood : ItemWrapperStrategy by DefaultStrategy() {
    override val allowedQualityRange = MIN_QUALITY..Int.MAX_VALUE
}

class Sulfuras : ItemWrapperStrategy by DefaultStrategy() {
    override fun moveSellInDate(sellIn: SellIn) = sellIn
    override fun qualityChange(sellIn: Int) = 0
}

class DefaultStrategy : ItemWrapperStrategy {
    override val allowedQualityRange = MIN_QUALITY..MAX_QUALITY

    override fun qualityChange(sellIn: SellIn) = when {
        sellIn.isExpired -> -2
        else -> -1
    }

    override fun qualityAfterExpiryDate(quality: Int) = quality

    override fun moveSellInDate(sellIn: SellIn) = sellIn - 1

    companion object {
        const val MIN_QUALITY = 0
        const val MAX_QUALITY = 50
    }

}

fun Item.chooseStrategy(): ItemWrapperStrategy {
    return when (name) {
        "Aged Brie" -> AgedBrie()
        "Backstage passes to a TAFKAL80ETC concert" -> BackStage()
        "Sulfuras, Hand of Ragnaros" -> Sulfuras()
        else -> SomeGood()
    }
}

interface ItemWrapperStrategy {

    @Deprecated("Allowed quality range should not be overridden anymore. " +
                "Currently overriding this range is allowed only for backward compatibility with legacy code.")
    val allowedQualityRange: IntRange

    fun qualityChange(sellIn: Int): Int

    fun qualityAfterExpiryDate(quality: Int): Int

    fun moveSellInDate(sellIn: SellIn): Int
}