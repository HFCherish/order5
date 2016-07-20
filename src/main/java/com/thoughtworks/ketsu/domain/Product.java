package com.thoughtworks.ketsu.domain;

public class Product {
    private long id;
    private String name;
    private String description;
    private double price;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }
}
