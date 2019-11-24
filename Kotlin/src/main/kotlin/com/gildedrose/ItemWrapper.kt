package com.gildedrose

fun wrap(it: Item): ItemWrapper {
    return when (it.name) {
        GildedRose.AGED_BRIE -> AgedBrie(it)
        GildedRose.BACKSTAGE -> BackStage(it)
        GildedRose.SULFURAS -> Sulfuras(it)
        else -> SomeGood(it)
    }
}

class AgedBrie(item: Item) : ItemWrapper(item) {
    val allowedQualityRange = Int.MIN_VALUE..50
    override fun degrade() {
        if (item.quality in allowedQualityRange) {
            item.quality = (item.quality + qualityChange(item.sellIn)).coerceAtMost(50)
        }
    }

    private fun qualityChange(sellIn: Int) = when {
        sellIn <= 0 -> 2
        else -> 1
    }
}

class BackStage(item: Item) : ItemWrapper(item) {
    val allowedQualityRange = Int.MIN_VALUE..50
    override fun degrade() {
        if (item.quality in allowedQualityRange) {
            item.quality = (item.quality + qualityChange(item.sellIn)).coerceAtMost(50)
            if (item.sellIn <= 0) {
                item.quality = 0
            }
        }
    }

    private fun qualityChange(sellIn: Int) = when (sellIn) {
        in 6..10 -> 2
        in 0..5 -> 3
        else -> 1
    }
}

class SomeGood(item: Item) : ItemWrapper(item) {
    val allowedQualityRange = 0..Int.MAX_VALUE
    override fun degrade() {
        if (item.quality in allowedQualityRange) {
            item.quality = (item.quality + qualityChange(item.sellIn)).coerceAtLeast(0)
        }
    }

    private fun qualityChange(sellIn: Int) = when {
        sellIn <= 0 -> -2
        else -> -1
    }
}

class Sulfuras(item: Item) : ItemWrapper(item) {
    override fun degrade() = Unit
    override fun moveSellInDate() = Unit
}

sealed class ItemWrapper(val item: Item) {
    abstract fun degrade()
    open fun moveSellInDate() {
        item.sellIn = item.sellIn - 1
    }
}