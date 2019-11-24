package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach {
            if (it.name != AGED_BRIE && it.name != BACKSTAGE) {
                if (it.quality > 0) {
                    if (it.name != SULFURAS) {
                        it.quality = it.quality - 1
                    }
                }
            } else {
                if (it.quality < 50) {
                    it.quality = it.quality + 1

                    if (it.name == BACKSTAGE) {
                        if (it.sellIn < 11) {
                            if (it.quality < 50) {
                                it.quality = it.quality + 1
                            }
                        }

                        if (it.sellIn < 6) {
                            if (it.quality < 50) {
                                it.quality = it.quality + 1
                            }
                        }
                    }
                }
            }

            if (it.name != SULFURAS) {
                it.sellIn = it.sellIn - 1
            }

            if (it.sellIn < 0) {
                if (it.name != AGED_BRIE) {
                    if (it.name != BACKSTAGE) {
                        if (it.quality > 0) {
                            if (it.name != SULFURAS) {
                                it.quality = it.quality - 1
                            }
                        }
                    } else {
                        it.quality = it.quality - it.quality
                    }
                } else {
                    if (it.quality < 50) {
                        it.quality = it.quality + 1
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

