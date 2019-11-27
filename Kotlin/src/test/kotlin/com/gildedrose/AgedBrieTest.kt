package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class AgedBrieTest {

    private val agedBrie = AgedBrie()

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(Int.MIN_VALUE..50, agedBrie.allowedQualityRange)
    }

    @Test
    fun qualityChange_beforeExpiration_returnsOne() {
        assertEquals(1, agedBrie.qualityChange(5))
    }

    @Test
    fun qualityChange_afterExpiration_returnsTwo() {
        assertEquals(2, agedBrie.qualityChange(-5))
    }

    @Test
    fun qualityChange_inExpirationDay_returnsTwo() {
        assertEquals(2, agedBrie.qualityChange(0))
    }
}