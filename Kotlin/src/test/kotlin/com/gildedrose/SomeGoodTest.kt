package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class SomeGoodTest {

    private val someGood = SomeGood()

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(someGood.allowedQualityRange, 0..Int.MAX_VALUE)
    }

    @Test
    fun qualityChange_beforeExpiration_returnsMinusOne() {
        assertEquals(-1, someGood.qualityChange(5))
    }

    @Test
    fun qualityChange_afterExpiration_returnsMinusTwo() {
        assertEquals(-2, someGood.qualityChange(-5))
    }

    @Test
    fun qualityChange_inExpirationDay_returnsMinusTwo() {
        assertEquals(-2, someGood.qualityChange(0))
    }
}