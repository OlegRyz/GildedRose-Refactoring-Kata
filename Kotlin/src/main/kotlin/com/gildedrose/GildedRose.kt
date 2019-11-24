package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach {
            when (it.name) {
                AGED_BRIE ->
                    AgedBrie(it)
                BACKSTAGE ->
                    BackStage(it)
                SULFURAS ->
                    Sulfuras(it)
                else -> {
                    SomeGood(it)
                }
            }.degrade()

            moveSellInDate(it)
        }
    }

    private fun moveSellInDate(it: Item) {
        if (it.name != SULFURAS) {
            it.sellIn = it.sellIn - 1
        }
    }

    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
    }
}