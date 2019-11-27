package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class SulfurasTest {

    private val sulfuras = Sulfuras()

    @Test
    fun moveSellInDate_always_keepsValue() {
        assertEquals(10, sulfuras.moveSellInDate(10))
    }

    @Test
    fun qualityChange_always_returnsZero() {
        assertEquals(0, sulfuras.qualityChange(10))
    }
}