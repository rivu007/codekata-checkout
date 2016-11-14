package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.exception.RuleNotSupportedException;
import com.codekatas.chapter9.checkout.rule.ItemDiscountPricingRule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemDiscountPricingRuleTest {

    private ItemDiscountPricingRule pricingRuleForDiscountedItem;

    @Test(expected = RuleNotSupportedException.class)
    public void calculatePrice_discountedPriceGreaterThanOriginalPrice_throwsRuleNotSupportedException() throws RuntimeException {
        pricingRuleForDiscountedItem = new ItemDiscountPricingRule(100, 200, 3);
        pricingRuleForDiscountedItem.computeItemPrice(5);
    }

    @Test
    public void calculatePrice_discountedPriceEqualsToOriginalPrice_success() throws RuntimeException {
        pricingRuleForDiscountedItem = new ItemDiscountPricingRule(100, 100, 3);
        assertEquals(500, pricingRuleForDiscountedItem.computeItemPrice(5), 0);
    }

    @Test
    public void calculatePrice_discountedPriceLessThanOriginalPrice_success() throws RuntimeException {
        pricingRuleForDiscountedItem = new ItemDiscountPricingRule(100, 80, 3);
        assertEquals(480, pricingRuleForDiscountedItem.computeItemPrice(5), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePrice_discountedIntervalIsZero_throwsIllegalArgumentException() throws Exception {
        double originalPrice = 100;
        double discountedPrice = 50;
        int discountedInterval = 0;

        pricingRuleForDiscountedItem = new ItemDiscountPricingRule(originalPrice, discountedPrice, discountedInterval);
    }

    @Test
    public void calculatePrice_computesThePriceOfDiscountedItems_success() throws Exception {
        double originalPrice = 100;
        double discountedPrice = 50;
        int discountedInterval = 3;
        int deltaValue = 0;

        pricingRuleForDiscountedItem = new ItemDiscountPricingRule(originalPrice, discountedPrice, discountedInterval);

        assertEquals(0, pricingRuleForDiscountedItem.computeItemPrice(0), deltaValue);
        assertEquals(100, pricingRuleForDiscountedItem.computeItemPrice(1), deltaValue);
        assertEquals(200, pricingRuleForDiscountedItem.computeItemPrice(2), deltaValue);
        assertEquals(250, pricingRuleForDiscountedItem.computeItemPrice(3), deltaValue);
        assertEquals(350, pricingRuleForDiscountedItem.computeItemPrice(4), deltaValue);
        assertEquals(450, pricingRuleForDiscountedItem.computeItemPrice(5), deltaValue);
        assertEquals(500, pricingRuleForDiscountedItem.computeItemPrice(6), deltaValue);
        assertEquals(600, pricingRuleForDiscountedItem.computeItemPrice(7), deltaValue);
        assertEquals(700, pricingRuleForDiscountedItem.computeItemPrice(8), deltaValue);
    }

}