package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach {
            wrap(it).apply {
                degrade()
                moveSellInDate()
            }
        }
    }
}