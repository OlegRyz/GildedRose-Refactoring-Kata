package com.gildedrose

import org.junit.Test
import kotlin.test.assertEquals

class GildedRoseTest {

    @Test
    fun someGood_withSomeQuality_degradesQualityByOne() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 3, 10)))

        gildedRose.updateQuality()

        assertEquals(9, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withQualityOne_degradesToQualityZero() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 10, 1)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withQualityZero_doesNotDegradeQuality() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 10, 0)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withQualityHigherThan80_degradesQualityByOne() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 3, 90)))

        gildedRose.updateQuality()

        assertEquals(89, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withQualityHigherThan50_degradesQualityByOne() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 3, 70)))

        gildedRose.updateQuality()

        assertEquals(69, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withSellInZero_degradesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 0, 10)))

        gildedRose.updateQuality()

        assertEquals(8, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withSellInZeroAndQualityOne_degradesQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 0, 1)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withNegativeQuality_DoesNotDegradeQuality() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 0, -21)))

        gildedRose.updateQuality()

        assertEquals(-21, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withNoQuality_reducesSellInTerm() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 10, 0)))

        gildedRose.updateQuality()

        assertEquals(9, gildedRose.items[0].sellIn)
    }

    @Test
    fun someGood_withSellInZero_reducesSellInTerm() {
        val gildedRose = GildedRose(arrayOf(Item("some good", 0, 10)))

        gildedRose.updateQuality()

        assertEquals(-1, gildedRose.items[0].sellIn)
    }
}


