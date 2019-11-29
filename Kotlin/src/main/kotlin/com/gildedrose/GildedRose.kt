package com.gildedrose

class GildedRose(var items: Array<Item>,
                 val wrap: (Item) -> ItemWrapper = { ItemWrapper(it) }) {

    private val wrappedItems = items.map{ wrap(it) }

    fun updateQuality() = with(wrappedItems) {
        forEach {
            it.degrade()
            it.moveSellInDate()
        }
    }
}