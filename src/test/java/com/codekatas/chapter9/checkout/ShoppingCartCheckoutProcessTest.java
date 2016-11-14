package com.codekatas.chapter9.checkout;

import com.codekatas.chapter9.checkout.rule.BaseItemConstantPricingRule;
import com.codekatas.chapter9.checkout.rule.ItemDiscountPricingRule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingCartCheckoutProcessTest {

    private ShoppingCartCheckoutProcess shoppingCartCheckoutProcess;

    @Before
    public void setUp() throws Exception {
        CalculateItemPrice calculateItemPrice = new CalculateItemPrice();
        calculateItemPrice.initPricingRule("A", new ItemDiscountPricingRule(50, 30, 3));
        calculateItemPrice.initPricingRule("B", new ItemDiscountPricingRule(30, 15, 2));
        calculateItemPrice.initPricingRule("C", new BaseItemConstantPricingRule(20));
        calculateItemPrice.initPricingRule("D", new BaseItemConstantPricingRule(15));

        shoppingCartCheckoutProcess = new ShoppingCartCheckoutProcess(calculateItemPrice);
    }

    @Test
    public void scanItems_shouldScanItems_success() throws Exception {
        shoppingCartCheckoutProcess.scanItems("A");
        shoppingCartCheckoutProcess.scanItems("B");
        shoppingCartCheckoutProcess.scanItems("B");
        shoppingCartCheckoutProcess.scanItems("D");
        shoppingCartCheckoutProcess.scanItems("D");
        shoppingCartCheckoutProcess.scanItems("B");

        assertEquals(1, shoppingCartCheckoutProcess.getScannedItemCount("A"));
        assertEquals(3, shoppingCartCheckoutProcess.getScannedItemCount("B"));
        assertEquals(0, shoppingCartCheckoutProcess.getScannedItemCount("C"));
        assertEquals(2, shoppingCartCheckoutProcess.getScannedItemCount("D"));
    }

    @Test
    public void calculateTotalItemsPrice_multipleItemsSupplied_success() throws Exception {
        assertEquals(  0, processEachItem(""), 0);
        assertEquals( 50, processEachItem("A"), 0);
        assertEquals( 80, processEachItem("AB"), 0);
        assertEquals(115, processEachItem("CDBA"), 0);

        assertEquals(100, processEachItem("AA"), 0);
        assertEquals(130, processEachItem("AAA"), 0);
        assertEquals(180, processEachItem("AAAA"), 0);
        assertEquals(230, processEachItem("AAAAA"), 0);
        assertEquals(260, processEachItem("AAAAAA"), 0);

        assertEquals(160, processEachItem("AAAB"), 0);
        assertEquals(175, processEachItem("AAABB"), 0);
        assertEquals(190, processEachItem("AAABBD"), 0);
        assertEquals(190, processEachItem("DABABA"), 0);
    }

    @Test
    public void calculateTotalItemsPrice_singleItemAddedIncrementally_success() throws Exception {
        assertEquals(0, processEachItem(""), 0);
        shoppingCartCheckoutProcess.scanItems("A");
        assertEquals(50, shoppingCartCheckoutProcess.calculateTotalItemsPrice(), 0);
        shoppingCartCheckoutProcess.scanItems("B");
        assertEquals(80, shoppingCartCheckoutProcess.calculateTotalItemsPrice(), 0);
        shoppingCartCheckoutProcess.scanItems("A");
        assertEquals(130, shoppingCartCheckoutProcess.calculateTotalItemsPrice(), 0);
        shoppingCartCheckoutProcess.scanItems("A");
        assertEquals(160, shoppingCartCheckoutProcess.calculateTotalItemsPrice(), 0);
        shoppingCartCheckoutProcess.scanItems("B");
        assertEquals(175, shoppingCartCheckoutProcess.calculateTotalItemsPrice(), 0);
    }

    private double processEachItem(String items) {
        for (int i = 0; i < items.length(); i++) {
            char item = items.charAt(i);
            shoppingCartCheckoutProcess.scanItems("" + item);
        }

        double totalItemsPrice = shoppingCartCheckoutProcess.calculateTotalItemsPrice();
        shoppingCartCheckoutProcess.getShoppingCart().clear();
        return totalItemsPrice;
    }

}