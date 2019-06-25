package com.contest;

public class BackstagePass extends Product {
    public static final String BACKSTAGE_PASS = "Backstage Pass";

    public BackstagePass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public Product updateSellInAndQuality() {
        if (sellIn > 10) {
            return new BackstagePass(BackstagePass.BACKSTAGE_PASS, sellIn - 1, makeQualityInRange(quality + 1));
        }
        if (sellIn > 5 && sellIn <= 10) {
            return new BackstagePass(BackstagePass.BACKSTAGE_PASS, sellIn - 1, makeQualityInRange(quality + 2));
        }
        if (sellIn > 0 && sellIn <= 5) {
            return new BackstagePass(BackstagePass.BACKSTAGE_PASS, sellIn - 1, makeQualityInRange(quality + 3));
        }
        return new BackstagePass(BackstagePass.BACKSTAGE_PASS, sellIn - 1, Product.MIN_QUALITY);
    }
}
