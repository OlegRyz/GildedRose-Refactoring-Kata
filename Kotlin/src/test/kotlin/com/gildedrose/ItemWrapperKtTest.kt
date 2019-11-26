package com.gildedrose

import org.junit.Assert.*
import org.junit.Test

class ItemWrapperKtTest {
    @Test
    fun wrap_forAgedBrie_returnsAgedBrieInstance() {
        val instance = wrap(Item("Aged Brie", 10, 10))

        assertTrue(instance is AgedBrie)
    }

    @Test
    fun wrap_forBackStage_returnsBackstageInstance() {
        val instance = wrap(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10))

        assertTrue(instance is BackStage)
    }

    @Test
    fun wrap_forSulfuras_returnsSulfurasInstance() {
        val instance = wrap(Item("Sulfuras, Hand of Ragnaros", 10, 10))

        assertTrue(instance is Sulfuras)
    }

    @Test
    fun wrap_forOtherGoods_returnsSomeGoodsInstance() {
        val instance = wrap(Item("sdafsd", 10, 10))

        assertTrue(instance is SomeGood)
    }
}