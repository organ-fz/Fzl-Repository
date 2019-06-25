package com.contest;

public class AgedBrie extends Product {
    public static final String AGED_BRIE = "Aged Brie";

    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Product updateSellInAndQuality() {
        if (quality >= MAX_QUALITY) {
            return new AgedBrie(AgedBrie.AGED_BRIE, sellIn - 1, MAX_QUALITY);
        }
        return new AgedBrie(AgedBrie.AGED_BRIE, sellIn - 1, quality + 1);
    }
}
