package com.gildedrose

typealias SellIn = Int

val SellIn.isExpired get() = this <= 0

class ItemWrapper(private val item: Item) {

    private var sellIn: SellIn
        get() = item.sellIn
        set(value) {
            item.sellIn = value
        }

    private var quality: Int
        get() = item.quality
        set(value) {
            item.quality = value
        }

    private val strategy = item.chooseStrategy()

    fun degrade() = with(strategy) {
        if (quality in allowedQualityRange) {
            quality = (quality + qualityChange(sellIn)).coerceIn(allowedQualityRange)
            if (sellIn.isExpired) {
                quality = qualityAfterExpiryDate(quality)
            }
        }
    }

    fun moveSellInDate() = with(strategy) {
        sellIn = moveSellInDate(sellIn)
    }

}