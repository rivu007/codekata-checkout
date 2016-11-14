package com.codekatas.chapter9.checkout.exception;

/**
 * This exception indicated that the Rule can't be supported
 * @see com.codekatas.chapter9.checkout.rule.ItemDiscountPricingRule
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public class RuleNotSupportedException extends RuntimeException {

    public RuleNotSupportedException(double originalPrice, double discountedPrice) {
        super("Invalid Rule - DiscountedPrice: " + discountedPrice + " can't be greater than original price: " + originalPrice);
    }
}
