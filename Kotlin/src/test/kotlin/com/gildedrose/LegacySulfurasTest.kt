package com.gildedrose

import org.junit.Test
import kotlin.test.assertEquals

class LegacySulfurasTest {

    @Test
    fun sulfuras_always_keepsSellInConstant() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 20, 80)))

        gildedRose.updateQuality()

        assertEquals(20, gildedRose.items[0].sellIn)
    }

    @Test
    fun sulfuras_withNegativeSellIn_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", -10, 80)))

        gildedRose.updateQuality()

        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun sulfuras_withPositiveSellIn_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 15, 80)))

        gildedRose.updateQuality()

        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun sulfuras_withSellInZero_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 80)))

        gildedRose.updateQuality()

        assertEquals(80, gildedRose.items[0].quality)
    }

    @Test
    fun sulfuras_withWrongQualityAndNegativeSellIn_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", -10, 20)))

        gildedRose.updateQuality()

        assertEquals(20, gildedRose.items[0].quality)
    }

    @Test
    fun sulfuras_withWrongQualityAndPositiveSellIn_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 15, 20)))

        gildedRose.updateQuality()

        assertEquals(20, gildedRose.items[0].quality)
    }

    @Test
    fun sulfuras_withWrongQualityAndSellInZero_doesNotDecreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Sulfuras, Hand of Ragnaros", 0, 20)))

        gildedRose.updateQuality()

        assertEquals(20, gildedRose.items[0].quality)
    }
}