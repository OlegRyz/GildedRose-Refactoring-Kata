package com.gildedrose

class GildedRose(var items: Array<Item>) {

    private val qualityRange = 0..50

    fun updateQuality() {
        items.forEach {
            when {
                it.name != AGED_BRIE && it.name != BACKSTAGE -> {
                    if (it.quality > 0) {
                        if (it.name != SULFURAS) {
                            it.quality = it.quality - 1
                        }
                    }
                }
                else -> {
                    if (it.quality < 50) {
                        it.quality = it.quality + 1

                        if (it.name == BACKSTAGE) {
                            if (it.quality < 50) {
                                it.quality = it.quality + when (it.sellIn) {
                                    in 6..10 ->  1
                                    in 0..5 -> 2
                                    else -> 0
                                }
                                it.quality = it.quality.coerceIn(qualityRange)
                            }
                        }
                    }
                }
            }

            if (it.name != SULFURAS) {
                it.sellIn = it.sellIn - 1
            }

            if (it.sellIn < 0) {
                when {
                    it.name != AGED_BRIE -> {
                        if (it.name != BACKSTAGE) {
                            if (it.quality > 0) {
                                if (it.name != SULFURAS) {
                                    it.quality = it.quality - 1
                                }
                            }
                        } else {
                            it.quality = it.quality - it.quality
                        }
                    }
                    else -> {
                        if (it.quality < 50) {
                            it.quality = it.quality + 1
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val AGED_BRIE = "Aged Brie"
        const val SULFURAS = "Sulfuras, Hand of Ragnaros"
        const val BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert"
    }

}

