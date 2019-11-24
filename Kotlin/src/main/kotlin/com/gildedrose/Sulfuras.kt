package com.gildedrose

class Sulfuras(item: Item): ItemWrapper(item) {
    override fun degrade() = Unit
    override fun moveSellInDate() = Unit
}
