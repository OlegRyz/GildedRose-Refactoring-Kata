package com.gildedrose

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemWrapperTest {
    @Mock
    lateinit var mockStrategy: ItemWrapperStrategy
    @Mock
    lateinit var item: Item
    lateinit var wrapper: ItemActions

    val factory = ItemActionsFactory()

    @Before
    fun setUp() {
        whenever(mockStrategy.moveSellInDate(any())).thenReturn(7)
        whenever(mockStrategy.allowedQualityRange).thenReturn(0..37)
        whenever(mockStrategy.qualityAfterExpiryDate(any())).thenReturn(-11)
        whenever(mockStrategy.qualityChange(any())).thenReturn(3)
        item = Item("asdf", 10, 30)
        wrapper = factory.wrapItem(item, mockStrategy)
    }

    @Test
    fun degrade_whenQualityIsInRange_addsStrategyQualityChange() {
        wrapper.degrade()

        assertEquals(33, item.quality)
    }

    @Test
    fun degrade_whenQualityIsInRangeAndCalledTwice_addsStrategyQualityChange() {
        wrapper.degrade()
        wrapper.degrade()

        assertEquals(30 + 3 + 3, item.quality)
    }

    @Test
    fun degrade_whenQualityIsInRange_passesSellInToStrategyQualityChange() {
        wrapper.degrade()

        verify(mockStrategy).qualityChange(10)
    }

    @Test
    fun degrade_whenSellInIsExpiring_setsQualityToQualityAfterExpireDateValue() {
        val item = Item("asdf", 0, 30)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        assertEquals(-11, item.quality)
    }

    @Test
    fun degrade_whenSellInIsExpired_setsQualityToQualityAfterExpireDateValue() {
        val item = Item("asdf", -2, 30)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        assertEquals(-11, item.quality)
    }

    @Test
    fun degrade_whenSellInIsExpiring_passesQualityToQualityAfterExpireDate() {
        val item = Item("asdf", 0, 30)
        whenever(mockStrategy.qualityChange(any())).thenReturn(0)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        verify(mockStrategy).qualityAfterExpiryDate(30)
    }

    @Test
    fun degrade_whenSellInIsExpired_passesQualityToQualityAfterExpireDate() {
        val item = Item("asdf", -2, 30)
        whenever(mockStrategy.qualityChange(any())).thenReturn(0)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        verify(mockStrategy).qualityAfterExpiryDate(30)
    }

    @Test
    fun degrade_whenQualityIsMoreThanRange_doesNotDoAnything() {
        val item = Item("asdf", 10, 40)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        assertEquals(40, item.quality)
    }


    @Test
    fun degrade_whenQualityIsLessThanRange_doesNotDoAnything() {
        val item = Item("asdf", 10, -2)
        val wrapper = factory.wrapItem(item, mockStrategy)

        wrapper.degrade()

        assertEquals(-2, item.quality)
    }

    @Test
    fun moveSellInDate_always_takesValueFromStrategyMoveSellIn() {
        wrapper.moveSellInDate()

        assertEquals(7, item.sellIn)
    }

    @Test
    fun moveSellInDate_calledTwice_takesValueFromStrategyMoveSellIn() {
        whenever(mockStrategy.moveSellInDate(any())).thenReturn(9)
        whenever(mockStrategy.moveSellInDate(9)).thenReturn(8)

        wrapper.moveSellInDate()
        wrapper.moveSellInDate()

        assertEquals(8, item.sellIn)
    }

    @Test
    fun moveSellInDate_always_passesPreviousSellInValueToStrategyMoveSellIn() {
        wrapper.moveSellInDate()

        verify(mockStrategy).moveSellInDate(10)
    }
}