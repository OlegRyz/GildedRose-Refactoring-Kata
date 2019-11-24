package com.gildedrose

import org.junit.Test
import kotlin.test.assertEquals

class AgedBrieTest {

    @Test
    fun agedBrie_always_reducesSellInTerm() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 10, 10)))

        gildedRose.updateQuality()

        assertEquals(9, gildedRose.items[0].sellIn)
    }

    @Test
    fun agedBrie_withSomeQuality_increasesQualityByOne() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 3, 10)))

        gildedRose.updateQuality()

        assertEquals(11, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withQualityZero_increasesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 0, 10)))

        gildedRose.updateQuality()

        assertEquals(12, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withNegativeSellIn_increasesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", -19, 10)))

        gildedRose.updateQuality()

        assertEquals(12, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withMaximumQuality_doesNotIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 15, 50)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withMaximumQualityAndNegativeSellIn_doesNotIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", -10, 50)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withMoreThanMaximumQuality_doesNotIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 15, 70)))

        gildedRose.updateQuality()

        assertEquals(70, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withMoreThanMaximumQualityAndNegativeSellIn_doesNotIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", -10, 70)))

        gildedRose.updateQuality()

        assertEquals(70, gildedRose.items[0].quality)
    }

    @Test
    fun agedBrie_withNegativeQuality_doesIncreaseQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Aged Brie", 10, -2)))

        gildedRose.updateQuality()

        assertEquals(-1, gildedRose.items[0].quality)
    }
}