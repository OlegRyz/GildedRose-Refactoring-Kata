package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class DefaultStrategyTest {

    private val defaultStrategy = DefaultStrategy()

    @Test
    fun getAllowedQualityRange_always_returnsRangeFromZeroToFifty() {
        assertEquals(0..50, defaultStrategy.allowedQualityRange)
    }

    @Test
    fun qualityChange_beforeExpiration_returnsMinusOne() {
        assertEquals(-1, defaultStrategy.qualityChange(5))
    }

    @Test
    fun qualityChange_afterExpiration_returnsMinusTwo() {
        assertEquals(-2, defaultStrategy.qualityChange(-5))
    }

    @Test
    fun qualityChange_inExpirationDay_returnsMinusTwo() {
        assertEquals(-2, defaultStrategy.qualityChange(0))
    }

    @Test
    fun moveSellInDate_always_reducesValueByOne() {
        assertEquals(14, defaultStrategy.moveSellInDate(15))
    }
}