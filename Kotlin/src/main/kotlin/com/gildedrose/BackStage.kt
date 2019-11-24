package com.gildedrose

class BackStage(private val item: Item): ItemWrapper {
    private val qualityRange = 0..50

    override fun degrade() {
        if (item.quality < 50) {
            item.quality = item.quality + when (item.sellIn) {
                in 6..10 -> 2
                in 0..5 -> 3
                else -> 1
            }
            item.quality = item.quality.coerceIn(qualityRange)
            if (item.sellIn <= 0) {
                item.quality = item.quality - item.quality
            }
        }
    }
}
