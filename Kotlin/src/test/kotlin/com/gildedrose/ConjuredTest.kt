package com.gildedrose

import org.junit.Assert
import org.junit.Test

class ConjuredTest {

    private val conjured = Conjured()

    @Test
    fun getAllowedQualityRange_always_returnsRangeFromZeroToFifty() {
        Assert.assertEquals(0..50, conjured.allowedQualityRange)
    }

    @Test
    fun qualityChange_beforeExpiration_returnsMinusTwo() {
        Assert.assertEquals(-2, conjured.qualityChange(5))
    }

    @Test
    fun qualityChange_afterExpiration_returnsMinusFour() {
        Assert.assertEquals(-4, conjured.qualityChange(-5))
    }

    @Test
    fun qualityChange_inExpirationDay_returnsMinusFour() {
        Assert.assertEquals(-4, conjured.qualityChange(0))
    }

    @Test
    fun moveSellInDate_always_reducesValueByOne() {
        Assert.assertEquals(14, conjured.moveSellInDate(15))
    }
}