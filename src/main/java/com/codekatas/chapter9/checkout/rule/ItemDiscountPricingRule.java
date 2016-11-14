package com.codekatas.chapter9.checkout.rule;

import com.codekatas.chapter9.checkout.exception.RuleNotSupportedException;

/**
 * Pricing rule that applicable to discounted price items.. This class inherits from {@link BaseItemConstantPricingRule}
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public class ItemDiscountPricingRule extends BaseItemConstantPricingRule implements PricingRule {

    private double discountPrice;
    private int discountInterval;

    /**
     * Default price rule Constructor for discounted item.
     *
     * @param originalPrice base price
     * @param discountPrice discount price
     * @param discountInterval every discountInterval's item gets the discount price
     * @throws RuleNotSupportedException
     */
    public ItemDiscountPricingRule(double originalPrice, double discountPrice, int discountInterval) {
        super(originalPrice);

        if (discountPrice > originalPrice) {
            throw new RuleNotSupportedException(originalPrice, discountPrice);
        }

        if (discountInterval <= 0) {
            throw new IllegalArgumentException("For discounted items: discountInterval should be greater than 0");
        }
        this.discountPrice = discountPrice;
        this.discountInterval = discountInterval;
    }

    /**
     * Calculates the discounted price of the item.
     * FORMULA:
     *          Annotation:
     *          DIC = discountItemCount = item count(n)
     *          TP = total price
     *          di = Discount Interval
     *          n = Item count
     *          dp = discount price for single item
     *          op = original price of the item
     *
     *          DIC = n/di
     *          TP =  DIC * dp + (n - DIC) * op
     *
     * @param itemCount item count
     * @return          returns the total price of individual item
     * @throws ArithmeticException if the discountInterval is zero
     */
    @Override
    public double computeItemPrice(int itemCount) throws ArithmeticException {
        int discountItemCount = itemCount / discountInterval;
        return discountItemCount * discountPrice +  (itemCount - discountItemCount) * super.getOriginalPrice();
    }
}
