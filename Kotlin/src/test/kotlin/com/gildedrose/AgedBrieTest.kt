package com.gildedrose

import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

class AgedBrieTest {

    val instance = AgedBrie(Item("asdf", 10, 10))

    @Test
    @Ignore("After issue with undefined behavior for negative quality is fixed")
    fun getAllowedQualityRange_always_returnsDefaultRange() {
        assertEquals(instance.allowedQualityRange, 0..50)
    }

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(instance.allowedQualityRange, Int.MIN_VALUE..50)
    }

    @Test
    fun qualityChange_returns() {

    }
}