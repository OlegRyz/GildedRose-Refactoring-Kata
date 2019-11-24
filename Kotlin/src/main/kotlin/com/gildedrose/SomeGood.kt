package com.gildedrose

class SomeGood(item: Item): ItemWrapper(item) {
    override fun degrade() {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
        if (item.sellIn <= 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1
            }
        }
    }
}
