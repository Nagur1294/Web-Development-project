package com.tapfoods.model;

public class CartItem {
    private int itemId;
    private int restaurantId;
    private String name;
    private double price;
    private int quantity;
    private double subTotal;

    public CartItem(int itemId, int restaurantId, String name, double price, int quantity,double subTotal) {
        this.itemId = itemId;
        this.restaurantId = restaurantId;
        this.name = name;
        this.price =  price;
        this.quantity = quantity;
        this.subTotal = price * quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.subTotal = price * this.quantity;  // Update subTotal when price is set
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subTotal = this.price * quantity;  // Update subTotal when quantity is set
    }

    public double getSubTotal() {
        return price*quantity;
    }

    @Override
    public String toString() {
        return "CartItem [itemId=" + itemId + ", restaurantId=" + restaurantId + ", name=" + name + ", price=" + price
                + ", quantity=" + quantity + ", subTotal=" + subTotal + "]";
    }
}
