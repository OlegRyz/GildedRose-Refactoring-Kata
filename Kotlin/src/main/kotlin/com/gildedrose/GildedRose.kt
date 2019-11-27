package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() = with(items.map { ItemWrapper(it) }) {
        forEach {
            it.degrade()
            it.moveSellInDate()
        }
    }
}