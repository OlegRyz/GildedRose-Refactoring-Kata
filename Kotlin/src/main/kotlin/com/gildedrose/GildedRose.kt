package com.gildedrose

class GildedRose(var items: Array<Item>,
                 private val itemActionsFactory: ItemActionsFactory = ItemActionsFactory()) {

    private val wrappedItems = items.map { itemActionsFactory.wrapItem(it) }

    fun updateQuality() = wrappedItems.forEach {
        it.degrade()
        it.moveSellInDate()
    }
}