package com.example.patterns;

import android.media.Image;

public class Item {
    String title;
    String manufacturer;
    String category;
    double price;
    String image;
    int stockLevel;

    public Item(String title, String manufacturer, String category, double price, String image, int stockLevel) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.image = image;
        this.stockLevel = stockLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }
}
