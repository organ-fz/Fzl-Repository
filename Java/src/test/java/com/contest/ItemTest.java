package com.contest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class ItemTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void quality_of_normal_product_should_decrease_1_each_day() {
        Product product = new NormalProduct(NormalProduct.NORMAL, 10, 20);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(NormalProduct.NORMAL));
        assertThat(updatedProduct.getSellIn(), is(9));
        assertThat(updatedProduct.getQuality(), is(19));
    }

    @Test
    public void quality_of_normal_product_should_be_0_if_quality_is_0() {
        Product product = new NormalProduct(NormalProduct.NORMAL, 10, Product.MIN_QUALITY);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(NormalProduct.NORMAL));
        assertThat(updatedProduct.getSellIn(), is(9));
        assertThat(updatedProduct.getQuality(), is(Product.MIN_QUALITY));
    }

    @Test
    public void quality_of_normal_product_should_degrade_twice_when_sell_by_date_passes() {
        Product product = new NormalProduct(NormalProduct.NORMAL, 0, 10);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(NormalProduct.NORMAL));
        assertThat(updatedProduct.getSellIn(), is(-1));
        assertThat(updatedProduct.getQuality(), is(8));
    }

    @Test
    public void quality_of_normal_product_should_not_be_negative_when_sell_by_date_passes() {
        Product product = new NormalProduct(NormalProduct.NORMAL, 0, 1);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(NormalProduct.NORMAL));
        assertThat(updatedProduct.getSellIn(), is(-1));
        assertThat(updatedProduct.getQuality(), is(Product.MIN_QUALITY));
    }

    @Test
    public void quality_of_normal_product_should_not_be_negative_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_NOT_BE_NEGATIVE);

        Product product = new NormalProduct(NormalProduct.NORMAL, 0, -1);
    }

    @Test
    public void quality_of_normal_product_should_be_less_than_50_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_BE_LESS_THAN_50);

        Product product = new NormalProduct(NormalProduct.NORMAL, 0, Product.MAX_QUALITY + 1);
    }

    @Test
    public void quality_of_aged_brie_should_increase_1_each_day() {
        Product product = new AgedBrie(AgedBrie.AGED_BRIE, 1, 49);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(AgedBrie.AGED_BRIE));
        assertThat(updatedProduct.getSellIn(), is(0));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_aged_brie_should_not_be_greater_than_50() {
        Product product = new AgedBrie(AgedBrie.AGED_BRIE, 1, Product.MAX_QUALITY);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(AgedBrie.AGED_BRIE));
        assertThat(updatedProduct.getSellIn(), is(0));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_aged_brie_should_not_be_negative_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_NOT_BE_NEGATIVE);

        Product product = new AgedBrie(AgedBrie.AGED_BRIE, 0, -1);
    }

    @Test
    public void quality_of_aged_brie_should_be_less_than_50_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_BE_LESS_THAN_50);

        Product product = new AgedBrie(AgedBrie.AGED_BRIE, 0, Product.MAX_QUALITY + 1);
    }

    @Test
    public void quality_of_sulfuras_should_be_always_80() {
        Product product = new Sulfuras(Sulfuras.SULFURAS, 1, Product.QUALITY_SULFURAS);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(Sulfuras.SULFURAS));
        assertThat(updatedProduct.getSellIn(), is(0));
        assertThat(updatedProduct.getQuality(), is(Product.QUALITY_SULFURAS));
    }

    @Test
    public void quality_of_sulfuras_should_be_always_80_even_sell_by_date_passes() {
        Product product = new Sulfuras(Sulfuras.SULFURAS, -1, Product.QUALITY_SULFURAS);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(Sulfuras.SULFURAS));
        assertThat(updatedProduct.getSellIn(), is(-2));
        assertThat(updatedProduct.getQuality(), is(Product.QUALITY_SULFURAS));
    }

    @Test
    public void quality_of_sulfuras_should_be_always_80_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Sulfuras.QUALITY_OF_SULFURAS_SHOULD_ALWAYS_BE_80);

        Product product = new Sulfuras(Sulfuras.SULFURAS, 0, 79);
    }

    @Test
    public void quality_of_backstage_pass_should_increase_by_1_more_than_10_days_before_opening() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 11, Product.MAX_QUALITY - 1);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(10));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_not_be_greater_than_50() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 11, Product.MAX_QUALITY);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(10));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_increase_by_2_when_5_to_10_days_before_opening_10_days() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 10, Product.MAX_QUALITY - 2);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(9));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_increase_by_2_when_5_to_10_days_before_opening_6_days() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 6, Product.MAX_QUALITY - 2);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(5));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_increase_by_3_when_0_to_5_days_before_opening_5_days() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 5, Product.MAX_QUALITY - 3);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(4));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_increase_by_3_when_0_to_5_days_before_opening_1_day() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 1, Product.MAX_QUALITY - 3);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(0));
        assertThat(updatedProduct.getQuality(), is(Product.MAX_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_be_0_when_show_is_opening() {
        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 0, Product.MAX_QUALITY);

        Product updatedProduct = product.updateSellInAndQuality();

        assertThat(updatedProduct.getName(), is(BackstagePass.BACKSTAGE_PASS));
        assertThat(updatedProduct.getSellIn(), is(-1));
        assertThat(updatedProduct.getQuality(), is(Product.MIN_QUALITY));
    }

    @Test
    public void quality_of_backstage_pass_should_not_be_negative_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_NOT_BE_NEGATIVE);

        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 0, -1);
    }

    @Test
    public void quality_of_backstage_pass_should_be_less_than_50_when_created() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(Product.QUALITY_SHOULD_BE_LESS_THAN_50);

        Product product = new BackstagePass(BackstagePass.BACKSTAGE_PASS, 0, Product.MAX_QUALITY + 1);
    }

}
