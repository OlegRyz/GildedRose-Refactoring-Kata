package com.gildedrose

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class GildedRoseTest {

    val wrappedItems = mutableListOf<Item>()
    val wrappers = mutableListOf<ItemActions>()
    lateinit var gildedRose: GildedRose
    lateinit var items: Array<Item>

    @Before
    fun setUp() {
        val rnd = Random(42)
        val repeatedItem = Item("duplicate", 1, 1)
        val repeatIndexes = arrayOf(1, 15, 400)

        items = Array(1000) {
            when (it) {
                in repeatIndexes -> repeatedItem
                else -> Item("item", rnd.nextInt(-1..20), rnd.nextInt(0..20))
            }
        }

        val factory = mock<ItemActionsFactory>()
        whenever(factory.wrapItem(any(), any())).then { item ->
            mock<ItemActions>().apply {
                wrappers.add(this)
                wrappedItems.add(item.getArgument(0))
            }
        }
        gildedRose = GildedRose(items, factory)

    }

    @Test
    fun constructor_withALotItems_wrapsAll() {
        assertEquals(items.size, wrappedItems.size)
        items.forEach { expectedItem ->
            assertEquals(items.filter { expectedItem == it }.size,
                    wrappedItems.filter { expectedItem == it }.size,
                    "The number of original items and number of wrapped items with value $expectedItem should match.")
        }
    }

    @Test
    fun updateItems_withALotItems_callsDegradeOnAll() {
        gildedRose.updateQuality()

        wrappers.forEach {
            verify(it).degrade()
        }
    }

    @Test
    fun updateItems_withALotItems_callsMoveSellInDateOnAll() {
        gildedRose.updateQuality()

        wrappers.forEach {
            verify(it).moveSellInDate()
        }
    }
}