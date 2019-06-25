package com.contest;

public class NormalProduct extends Product {
    public static final String NORMAL = "Normal";

    public NormalProduct(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Product updateSellInAndQuality() {
        if (quality == MIN_QUALITY) {
            return new NormalProduct(NormalProduct.NORMAL, sellIn - 1, MIN_QUALITY);
        }
        if (sellIn <= 0 && quality > 2) {
            return new NormalProduct(NormalProduct.NORMAL, sellIn - 1, quality - 2);
        }
        if (sellIn <= 0 && quality == 1) {
            return new NormalProduct(NormalProduct.NORMAL, sellIn - 1, MIN_QUALITY);
        }
        return new NormalProduct(NormalProduct.NORMAL, sellIn - 1, quality - 1);
    }
}
