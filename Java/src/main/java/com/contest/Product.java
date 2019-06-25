package com.contest;

public abstract class Product extends Item {

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;
    public static final int QUALITY_SULFURAS = 80;
    public static final String QUALITY_SHOULD_NOT_BE_NEGATIVE = "quality should not be negative.";
    public static final String QUALITY_SHOULD_BE_LESS_THAN_50 = "quality should be less than 50.";

    public Product(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        if (!Sulfuras.SULFURAS.equals(name) && quality < MIN_QUALITY) {
            throw new IllegalArgumentException(QUALITY_SHOULD_NOT_BE_NEGATIVE);
        }
        if (!Sulfuras.SULFURAS.equals(name) && quality > MAX_QUALITY) {
            throw new IllegalArgumentException(QUALITY_SHOULD_BE_LESS_THAN_50);
        }
    }

    public String getName() {
        return super.name;
    }

    public int getSellIn() {
        return super.sellIn;
    }

    public int getQuality() {
        return super.quality;
    }

    public abstract Product updateSellInAndQuality();

    protected int makeQualityInRange(int quality) {
        return quality > 50 ? 50 : quality;
    }
}
