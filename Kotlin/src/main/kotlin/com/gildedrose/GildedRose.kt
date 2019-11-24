package com.gildedrose

class GildedRose(var items: Array<Item>) {

    private val qualityRange = 0..50

    fun updateQuality() {
        items.forEach {
            when (it.name) {
                AGED_BRIE -> {
                    if (it.quality < 50) {
                        it.quality = it.quality + 1
                    }
                    if (it.sellIn <= 0) {
                        if (it.quality < 50) {
                            it.quality = it.quality + 1
                        }
                    }
                }
                BACKSTAGE ->
                    if (it.quality < 50) {
                        it.quality = it.quality + when (it.sellIn) {
                            in 6..10 -> 2
                            in 0..5 -> 3
                            else -> 1
                        }
                        it.quality = it.quality.coerceIn(qualityRange)
                        if (it.sellIn <= 0) {
                            it.quality = it.quality - it.quality
                        }
                    }
                SULFURAS -> Unit
                else -> {
                    if (it.quality > 0) {
                        it.quality = it.quality - 1
                    }
                    if (it.sellIn <= 0) {
                        if (it.quality > 0) {
                            it.quality = it.quality - 1
                        }
                    }
                }
            }

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