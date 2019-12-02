package com.gildedrose

typealias SellIn = Int

val SellIn.isExpired get() = this <= 0

class ItemActionsFactory {
    fun wrapItem(item: Item, strategy: ItemWrapperStrategy = item.chooseStrategy()): ItemActions = ItemWrapper(item, strategy)
}

interface ItemActions {
    fun degrade()
    fun moveSellInDate()
}

private class ItemWrapper(private val item: Item,
                          private val strategy: ItemWrapperStrategy) : ItemActions {

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

    override fun degrade() = with(strategy) {
        if (quality in allowedQualityRange) {
            quality = (quality + qualityChange(sellIn)).coerceIn(allowedQualityRange)
            if (sellIn.isExpired) {
                quality = qualityAfterExpiryDate(quality)
            }
        }
    }

    override fun moveSellInDate() = with(strategy) {
        sellIn = moveSellInDate(sellIn)
    }

}