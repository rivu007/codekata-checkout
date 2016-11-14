package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.rule.PricingRule;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class is responsible for calculating the price of the scanned items.
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public class CalculateItemPrice {

    private Map<String, PricingRule> pricingRules = new HashMap<>();

    /**
     * Initialise the pricing rule of an item.
     * @param item
     * @param pricingRule
     */
    public void initPricingRule(String item, PricingRule pricingRule) {
        pricingRules.put(item, pricingRule);
    }

    /**
     * Calculates the total price of all the scanned items.
     * @param items name of the items
     * @return      total price of all the items
     */
    public double computeAllItemsPrice(Map<String, Integer> items) {
        double totalPriceOfitems = 0;

        for (Entry<String, Integer> itemEntry : items.entrySet()) {
            if (pricingRules.containsKey(itemEntry.getKey())) {
                PricingRule pricingRule = pricingRules.get(itemEntry.getKey());
                totalPriceOfitems += pricingRule.computeItemPrice(itemEntry.getValue());
            } else {
                throw new IllegalStateException("204 Error: No item found with name:" + itemEntry.getKey());
            }
        }

        return totalPriceOfitems;
    }
}
