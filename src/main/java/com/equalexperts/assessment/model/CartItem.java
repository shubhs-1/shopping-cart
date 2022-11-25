package com.equalexperts.assessment.model;

public class CartItem {
    private Product product;
    private int quantity;
    private boolean isOfferAssociated;

    public boolean isOfferAssociated() {
        return isOfferAssociated;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isOfferAssociated = false;
    }

    public CartItem(Product product, int quantity, boolean isOfferAssociated) {
        this.product = product;
        this.quantity = quantity;
        this.isOfferAssociated = isOfferAssociated;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
