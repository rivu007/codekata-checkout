package com.codekatas.chapter9.checkout.rule;

/**
 * Simple Interface to calculate the pricing rule.
 *
 * @author Abhilash Ghosh
 * @since 1.0
 * @version 1.0
 */
public interface PricingRule {

    double computeItemPrice(int itemCount);
}
