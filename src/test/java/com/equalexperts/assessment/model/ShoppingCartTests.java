package com.equalexperts.assessment.model;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTests {
    @Test
    public void testProductAdditionToCart() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("Dove Soap", BigDecimal.valueOf(39.99));
        CartItem item = new CartItem(product, 5);

        List<CartItem> expectedItems = new ArrayList<>();
        expectedItems.add(item);
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(199.95);

        cart.addProductToCart(item);

        Assert.assertEquals(expectedItems, cart.getAllProducts());
        Assert.assertEquals(cart.getTotalPrice(), expectedTotalPrice);
    }

    @Test
    public void testAdditionalProductAdditionToCartOfSameType() {
        ShoppingCart cart = new ShoppingCart();
        Product product = new Product("Dove Soap", BigDecimal.valueOf(39.99));
        CartItem item1 = new CartItem(product, 5);
        CartItem item2 = new CartItem(product, 3);

        List<CartItem> expectedItems = new ArrayList<>();
        CartItem expectedEightItem = new CartItem(product, 8);
        expectedItems.add(expectedEightItem);
        BigDecimal expectedTotalPrice = BigDecimal.valueOf(319.92);

        cart.addProductToCart(item1);
        cart.addProductToCart(item2);

        Assert.assertEquals(expectedItems.size(), 1);
        Assert.assertEquals(expectedItems.get(0).getProduct().getName(), cart.getAllProducts().get(0).getProduct().getName());
        Assert.assertEquals(expectedItems.get(0).getProduct().getPrice(), cart.getAllProducts().get(0).getProduct().getPrice());
        Assert.assertEquals(cart.getTotalPrice(), expectedTotalPrice);
    }

    @Test
    public void testAdditionalProductAdditionToCartOfDifferentType() {
        ShoppingCart cart = new ShoppingCart(BigDecimal.valueOf(12.5));
        Product product1 = new Product("Dove Soap", BigDecimal.valueOf(39.99));
        Product product2 = new Product("Axe Deo", BigDecimal.valueOf(99.99));
        CartItem item1 = new CartItem(product1, 2);
        CartItem item2 = new CartItem(product2, 2);

        BigDecimal expectedTotalPrice = BigDecimal.valueOf(314.96);
        BigDecimal expectedTotalTax= BigDecimal.valueOf(35.00).setScale(2);

        cart.addProductToCart(item1);
        cart.addProductToCart(item2);

        Assert.assertEquals(expectedTotalTax, cart.getTotalTax());
        Assert.assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }

    @Test
    public void testAdditionalProductAdditionToCartOfDifferentTypeAndAssociatedOffers() {
        ShoppingCart cart = new ShoppingCart(BigDecimal.valueOf(12.5));
        Product product1 = new Product("Dove Soap", BigDecimal.valueOf(39.99));
        Product product2 = new Product("Axe Deo", BigDecimal.valueOf(99.99));
        CartItem item1 = new CartItem(product1, 2);
        CartItem item2 = new CartItem(product2, 2);

        BigDecimal expectedTotalPrice = BigDecimal.valueOf(314.96);
        BigDecimal expectedTotalTax= BigDecimal.valueOf(35.00).setScale(2);

        cart.addProductToCart(item1);
        cart.addProductToCart(item2);

        Assert.assertEquals(expectedTotalTax, cart.getTotalTax());
        Assert.assertEquals(expectedTotalPrice, cart.getTotalPrice());
    }
}
