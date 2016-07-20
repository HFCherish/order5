package com.thoughtworks.ketsu.domain;

public class Payment {
    private long orderId;
    private PayType type;
    private double amount;

    public long getOrderId() {
        return orderId;
    }

    public PayType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
