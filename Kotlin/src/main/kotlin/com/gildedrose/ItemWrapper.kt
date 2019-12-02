package com.gildedrose

class ItemActionsFactory {
    fun wrapItem(item: Item,
                 strategy: ItemWrapperStrategy = item.chooseStrategy()): ItemActions = ItemWrapper(item, strategy)
}

interface ItemActions {
    fun degrade()
    fun moveSellInDate()
}

private class ItemWrapper(private val item: Item,
                          private val strategy: ItemWrapperStrategy) : ItemActions {

    override fun degrade() = with(strategy) {
        if (item.quality !in allowedQualityRange) {
            return
        }

        if (resetQualityIfExpired && item.sellIn.isExpired) {
            item.quality = 0
        } else {
            item.quality = (item.quality + qualityChange(item.sellIn)).coerceIn(allowedQualityRange)
        }
    }

    override fun moveSellInDate() = with(strategy) {
        item.sellIn = moveSellInDate(item.sellIn)
    }

}