package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.rule.BaseItemConstantPricingRule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CalculateItemPriceTest {

    private CalculateItemPrice calculateItemPrice = new CalculateItemPrice();

    @Test
    public void computeAllItemsPrice_shouldCalculateTotalItemsPrice_success() throws Exception {
        Map<String, Integer> items = new HashMap();
        items.put("A", 3);
        items.put("B", 1);
        calculateItemPrice.initPricingRule("A", new BaseItemConstantPricingRule(50));
        calculateItemPrice.initPricingRule("B", new BaseItemConstantPricingRule(30));

        assertEquals(180, calculateItemPrice.computeAllItemsPrice(items), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void computeAllItemsPrice_missingPricingRule_failure() throws Exception {
        Map<String, Integer> items = new HashMap();
        items.put("C", 3);

        calculateItemPrice.computeAllItemsPrice(items);
    }
}