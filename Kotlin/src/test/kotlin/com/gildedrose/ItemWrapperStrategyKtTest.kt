package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class ItemWrapperStrategyKtTest {

    @Test
    fun chooseStrategy_forAgedBrie_returnsAgedBrieInstance() {
        val strategy = Item("Aged Brie", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is AgedBrie)
    }

    @Test
    fun chooseStrategy_forBackStage_returnsBackstageInstance() {
        val strategy = Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is BackStage)
    }

    @Test
    fun chooseStrategy_forSulfuras_returnsSulfurasInstance() {
        val strategy = Item("Sulfuras, Hand of Ragnaros", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is Sulfuras)
    }

    @Test
    fun chooseStrategy_forOtherGoods_returnsSomeGoodsInstance() {
        val strategy = Item("sdafsd", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is SomeGood)
    }

    @Test
    fun chooseStrategy_forConjuredGoods_returnsConjuredGoodsInstance() {
        val strategy = Item("Conjured", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is Conjured)
    }

    @Test
    fun chooseStrategy_forConjuredManaCake_returnsConjuredGoodsInstance() {
        val strategy = Item("Conjured Mana Cake", 10, 10)
                .chooseStrategy()

        assertTrue(strategy is Conjured)
    }
}