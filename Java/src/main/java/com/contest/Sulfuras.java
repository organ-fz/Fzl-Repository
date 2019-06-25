package com.contest;

public class Sulfuras extends Product {
    public static final String SULFURAS = "Sulfuras";
    public static final String QUALITY_OF_SULFURAS_SHOULD_ALWAYS_BE_80 = "quality of sulfuras should always be 80.";

    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);

        if (Sulfuras.SULFURAS.equals(name) && quality != QUALITY_SULFURAS) {
            throw new IllegalArgumentException(Sulfuras.QUALITY_OF_SULFURAS_SHOULD_ALWAYS_BE_80);
        }
    }

    @Override
    public Product updateSellInAndQuality() {
        return new Sulfuras(Sulfuras.SULFURAS, sellIn - 1, QUALITY_SULFURAS);
    }
}
