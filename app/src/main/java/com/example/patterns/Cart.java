package com.example.patterns;

import java.util.ArrayList;

public class Cart {
    // private static Cart cart;


    private ArrayList<Item> items;
    private Double total;


    private Cart() {

    }

    public Cart(ArrayList<Item> items, Double total) {
        this.items = items;
        this.total = total;
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



    public double calculateTotal() {
        double t=0;
        for (Item i : this.items) {
        t = t+ i.getPrice();

        }
        return t;
    }


    //    public static Cart getInstance() {
//        if (cart == null) {
//            cart = new Cart();
//        }
//        return cart;
//    }
}






