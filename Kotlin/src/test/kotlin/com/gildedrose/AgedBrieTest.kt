package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class AgedBrieTest {

    private val agedBrie = AgedBrie()

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(agedBrie.allowedQualityRange, Int.MIN_VALUE..50)
    }

    @Test
    fun qualityChange_returns() {

    }
}