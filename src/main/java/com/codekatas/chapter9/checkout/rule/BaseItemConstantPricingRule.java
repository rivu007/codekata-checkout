package com.codekatas.chapter9.checkout.rule;

/**
 * Pricing rule that applicable to constant price items.
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public class BaseItemConstantPricingRule implements PricingRule {

    private double originalPrice;

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BaseItemConstantPricingRule( double originalPrice) {
        if (originalPrice < 0) {
            throw new NumberFormatException("Error: Price of the item cannot be negative");
        }
        this.originalPrice = originalPrice;
    }

    /**
     * Calculates the price of the items.
     * FORMULA:
     *          Original price of the item (o) * no. of items scanned (n)
     *
     * @param itemCount item count
     * @return          returns the total price of individual item
     */
    @Override
    public double computeItemPrice(int itemCount) {
        return originalPrice * itemCount;
    }
}
