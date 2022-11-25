package com.equalexperts.assessment.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
