package com.gildedrose

import org.junit.Test

import org.junit.Assert.*

class BackStageTest {

    private val backStage = BackStage()

    @Test
    fun getAllowedQualityRange_always_returnsLeftOpenRange() {
        assertEquals(Int.MIN_VALUE..50, backStage.allowedQualityRange)
    }

    @Test
    fun qualityAfterExpiryDate_always_returnsZero() {
        assertEquals(0, backStage.qualityAfterExpiryDate(10))
    }

    @Test
    fun qualityChange_whenSellInIsOne_returnsThree() {
        assertEquals(3, backStage.qualityChange(1))
    }

    @Test
    fun qualityChange_whenSellInIsBetweenOneAndFive_returnsThree() {
        assertEquals(3, backStage.qualityChange(3))
    }

    @Test
    fun qualityChange_whenSellInIsFive_returnsThree() {
        assertEquals(3, backStage.qualityChange(5))
    }

    @Test
    fun qualityChange_whenSellInIsSix_returnsTwo() {
        assertEquals(2, backStage.qualityChange(6))
    }

    @Test
    fun qualityChange_whenSellInIsBetweenSixAndEleven_returnsTwo() {
        assertEquals(2, backStage.qualityChange(8))
    }

    @Test
    fun qualityChange_whenSellInIsTen_returnsTwo() {
        assertEquals(2, backStage.qualityChange(10))
    }

    @Test
    fun qualityChange_whenSellInIsEleven_returnsOne() {
        assertEquals(1, backStage.qualityChange(12))
    }

    @Test
    fun qualityChange_whenSellInIsMoreThanEleven_returnsOne() {
        assertEquals(1, backStage.qualityChange(30))
    }
}