package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class SomeGoodTest {

    private val someGood = SomeGood()

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(someGood.allowedQualityRange, 0..Int.MAX_VALUE)
    }
}