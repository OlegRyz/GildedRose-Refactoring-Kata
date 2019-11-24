package com.gildedrose

fun wrap(it: Item): ItemWrapper {
    return when (it.name) {
        GildedRose.AGED_BRIE ->
            AgedBrie(it)
        GildedRose.BACKSTAGE ->
            BackStage(it)
        GildedRose.SULFURAS ->
            Sulfuras(it)
        else -> {
            SomeGood(it)
        }
    }
}

interface ItemWrapper {
    fun degrade()
}