package org.example;

import lombok.Data;

import java.util.Date;

@Data

public class Order {
    private int id;
    private String date;
    private double amount;
    private int customerId;

    public Order (int id, String date, double amount, int customerId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
