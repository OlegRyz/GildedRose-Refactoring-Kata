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

abstract class ItemWrapper(val item: Item) {
    abstract fun degrade()
    open fun moveSellInDate() {
        item.sellIn = item.sellIn - 1
    }
}