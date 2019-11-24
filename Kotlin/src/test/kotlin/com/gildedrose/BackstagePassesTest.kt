package com.gildedrose

import org.junit.Test
import kotlin.test.assertEquals

class BackstagePassesTest {

    @Test
    fun backstagePasses_always_reducesSellInTerm() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)))

        gildedRose.updateQuality()

        assertEquals(10, gildedRose.items[0].sellIn)
    }

    @Test
    fun backstagePasses_withSellInMoreThanTen_increasesQualityByOne() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 10)))

        gildedRose.updateQuality()

        assertEquals(11, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInTen_increasesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 10)))

        gildedRose.updateQuality()

        assertEquals(12, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInMoreThanFive_increasesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 6, 10)))

        gildedRose.updateQuality()

        assertEquals(12, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInFive_increasesQualityByThree() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)))

        gildedRose.updateQuality()

        assertEquals(13, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInLessThanFive_increasesQualityByThree() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 1, 10)))

        gildedRose.updateQuality()

        assertEquals(13, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInMoreThanTen_doesNotExceedsQualityFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 50)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInTen_doesNotExceedsQualityFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInMoreThanFive_doesNotExceedsQualityFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 6, 49)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInFive_doesNotExceedsQualityFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInLessThanFive_doesNotExceedsQualityFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 1, 48)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInZero_dropsToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, 10)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInZeroAndNegativeQuality_dropsToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 0, -10)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withNegativeSellIn_dropsQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", -15, 10)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withMoreThanMaximumQuality_doesNotIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 1, 70)))

        gildedRose.updateQuality()

        assertEquals(70, gildedRose.items[0].quality)
    }
}