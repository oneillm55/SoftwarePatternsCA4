package com.example.patterns;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;
    private Double total;
    private String CustomerID;

    public Order(ArrayList<Item> items, Double total, String customerID) {
        this.items = items;
        this.total = total;
        CustomerID = customerID;
    }

    public Order() {
    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }


}
