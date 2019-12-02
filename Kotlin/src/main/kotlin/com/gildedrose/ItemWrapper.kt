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

    private val sellIn: SellIn
        get() = item.sellIn

    private val quality: Int
        get() = item.quality

    override fun degrade() = with(strategy) {
        if (quality in allowedQualityRange) {
            item.quality = (quality + qualityChange(sellIn)).coerceIn(allowedQualityRange)
            if (sellIn.isExpired) {
                item.quality = qualityAfterExpiryDate(quality)
            }
        }
    }

    override fun moveSellInDate() = with(strategy) {
        item.sellIn = moveSellInDate(sellIn)
    }

}