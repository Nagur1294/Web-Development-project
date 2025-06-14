package com.tapfoods.model;

public class Menu {
    private int menuId;
    private int restaurantId;
    private String menuName;
    private String description;
    private double price;
    private boolean isAvailable;
    private String imagePath;  

    public Menu() {
        super();
    }

    public Menu(int menuId, int restaurantId, String menuName, String description, double price, boolean isAvailable, String imagePath) {
        super();
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath;  
    }

    public Menu(int restaurantId, String menuName, String description, double price, boolean isAvailable, String imagePath) {
        super();
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.imagePath = imagePath; 
    }

    

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getImagePath() {  
        return imagePath;
    }

    public void setImagePath(String imagePath) {  
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", menuName=" + menuName + ", description="
                + description + ", price=" + price + ", isAvailable=" + isAvailable + ", imagePath=" + imagePath + "]";
    }
}
