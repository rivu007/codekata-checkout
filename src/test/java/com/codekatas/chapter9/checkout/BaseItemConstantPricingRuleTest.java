package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.rule.BaseItemConstantPricingRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BaseItemConstantPricingRuleTest {

    private BaseItemConstantPricingRule pricingRule;

    @Before
    public void setUp() throws Exception {
        int priceForNormalItem = 100;
        pricingRule = new BaseItemConstantPricingRule(priceForNormalItem);
    }

    @Test(expected = NumberFormatException.class)
    public void  baseItemConstantPricingRule_itemWithNegativePrice_throwsNumberFormatException() {
        pricingRule = new BaseItemConstantPricingRule(-10.99);
    }

    @Test()
    public void  baseItemConstantPricingRule_itemWithPositivePrice_throwsNumberFormatException() {
        pricingRule = new BaseItemConstantPricingRule(5.99);
        assertNotNull(pricingRule);
        assertEquals(5.99, pricingRule.getOriginalPrice(), 0);

    }

    @Test
    public void calculatePrice_shouldCalculatePrice_success() {
        assertEquals(0, pricingRule.computeItemPrice(0), 0);
        assertEquals(100, pricingRule.computeItemPrice(1), 0);
        assertEquals(200, pricingRule.computeItemPrice(2), 0);
        assertEquals(300, pricingRule.computeItemPrice(3), 0);
        assertEquals(400, pricingRule.computeItemPrice(4), 0);
        assertEquals(500, pricingRule.computeItemPrice(5), 0);
    }
}