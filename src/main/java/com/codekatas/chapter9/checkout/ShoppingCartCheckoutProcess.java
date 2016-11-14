package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.rule.BaseItemConstantPricingRule;
import com.codekatas.chapter9.checkout.rule.ItemDiscountPricingRule;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a simple implementation of codekatas - back to the checkout (chapter 9).
 * It scans items before adding the items in the shopping cart and calculates the total price using {@link CalculateItemPrice}.
 * There are some rules to take into consideration while calculating the total price of all the items in the cart.
 * {@link BaseItemConstantPricingRule} for constant pricing items and  {@link ItemDiscountPricingRule} for discounted pricing items
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public class ShoppingCartCheckoutProcess {

    private CalculateItemPrice calculateItemPrice;

    private Map<String, Integer> shoppingCart = new HashMap<>();

    /**
     * ShoppingCartCheckoutProcess constructor
     */
    public ShoppingCartCheckoutProcess(CalculateItemPrice calculateItemPrice) {
        this.calculateItemPrice = calculateItemPrice;
    }

    public Map<String, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(Map<String, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Scan the item and add it to shopping list
     *
     * @param item to be added in the shopping cart
     */
    public void scanItems(String item) {
        if (!shoppingCart.containsKey(item)) {
            shoppingCart.put(item, 0);
        }

        shoppingCart.put(item, shoppingCart.get(item) + 1);
    }

    /**
     * Calculates the total price of all the item(s) in the shopping list
     * @return total price of scanned items.
     */
    public double calculateTotalItemsPrice() {
        return calculateItemPrice.computeAllItemsPrice(shoppingCart);
    }

    /**
     * Retrieves the total count of a particular item
     *
     * @param item name to get the total count
     * @return     the item count in the shopping cart
     */
    public int getScannedItemCount(String item) {
        Integer itemCount = shoppingCart.get(item);
        return itemCount != null ? itemCount : 0;
    }
}
