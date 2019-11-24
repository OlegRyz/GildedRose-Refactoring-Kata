package com.gildedrose

class AgedBrie(val item: Item) {
    fun degrade() {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
        if (item.sellIn <= 0) {
            if (item.quality < 50) {
                item.quality = item.quality + 1
            }
        }
    }
}
