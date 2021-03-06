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
    fun someGood_withNegtiveSellIn_degradesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("some good", -10, 10)))

        gildedRose.updateQuality()

        assertEquals(8, gildedRose.items[0].quality)
    }

    @Test
    fun someGood_withNegtiveSellInAndQualityOne_degradesQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("some good", -10, 1)))

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
    fun agedBrie_withNegativeQuality_increasesQualityByTwo() {
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
    fun backstagePasses_withSellInMoreThanTen_doesNotExceedsFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 11, 50)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInTen_doesNotExceedsFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInMoreThanFive_doesNotExceedsFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 6, 49)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInFive_doesNotExceedsFifty() {
        val gildedRose = GildedRose(arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", 5, 48)))

        gildedRose.updateQuality()

        assertEquals(50, gildedRose.items[0].quality)
    }

    @Test
    fun backstagePasses_withSellInLessThanFive_doesNotExceedsFifty() {
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
    fun backstagePasses_withNegativeSellIn_dropsToZero() {
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

    @Test
    fun conjured_withSomeQuality_degradesQualityByTwo() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 1, 10)))

        gildedRose.updateQuality()

        assertEquals(8, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withQualityTwo_degradesQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 1, 2)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withQualityOne_degradesQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 1, 2)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withQualityZero_doesNotChangeQuality() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 1, 2)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withSellInZero_degradesQualityByFour() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 0, 5)))

        gildedRose.updateQuality()

        assertEquals(1, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withSellInZeroAndSmallQuality_degradesQualityToZero() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 0, 2)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withNegativeSellIn_degradesQualityByFour() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", -3, 10)))

        gildedRose.updateQuality()

        assertEquals(6, gildedRose.items[0].quality)
    }

    @Test
    fun conjured_withPositiveSellIn_degradesSellInByOne() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 5, 10)))

        gildedRose.updateQuality()

        assertEquals(4, gildedRose.items[0].sellIn)
    }

    @Test
    fun conjured_withNegativeSellIn_degradesSellInByOne() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", -5, 10)))

        gildedRose.updateQuality()

        assertEquals(-6, gildedRose.items[0].sellIn)
    }

    @Test
    fun conjured_withSellInZero_degradesSellInByOne() {
        val gildedRose = GildedRose(arrayOf(Item("Conjured", 0, 10)))

        gildedRose.updateQuality()

        assertEquals(0, gildedRose.items[0].sellIn)
    }
}


