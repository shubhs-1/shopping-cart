package com.equalexperts.assessment.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private List<CartItem> items;
    private BigDecimal totalPrice;
    private BigDecimal taxRate;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.taxRate = new BigDecimal(0);
    }

    public ShoppingCart(BigDecimal taxRate) {
        this.items = new ArrayList<>();
        this.totalPrice = new BigDecimal(0);
        this.taxRate = taxRate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.add(this.getTotalTax());
    }

    public BigDecimal getTotalTax() {
        return this.totalPrice.multiply(taxRate).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.CEILING);
    }

    public void addProductToCart(CartItem item) {
        Optional<CartItem> cartItem = items.stream().filter(product -> product.getProduct().equals(item.getProduct())).findFirst();
        if(cartItem.isPresent()) {
            cartItem.get().setQuantity(cartItem.get().getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }

        this.totalPrice = items.stream().map(product -> {
            int discountedQuantity = product.getQuantity() / 3;
            int actual = product.getQuantity();
            if(product.isOfferAssociated()) {
                return product.getProduct().getPrice().multiply(BigDecimal.valueOf(product.getQuantity()).subtract(BigDecimal.valueOf(discountedQuantity)));
            }
            return product.getProduct().getPrice().multiply(BigDecimal.valueOf(actual));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        this.totalPrice = items.stream().map(product -> product.getProduct().getPrice().multiply(BigDecimal.valueOf(product.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<CartItem> getAllProducts() {
        return items;
    }
}
