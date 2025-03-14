package com.crud.jpa_query_orders_10_03.enumeration;

public enum ProductCategory {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    HOME("Home"),
    GROCERIES("Groceries"),
    WELLNESS("Wellness");

    private final String description;

    ProductCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
