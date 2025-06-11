package com.tapfoods.model;

public class Restaurant {
	private int restaurantId;;
	private String restaurantName;
	private String cuisineType;
	private int deliveryTime;
	private String address;
	private int adminId;
	private double rating;
	private String isActive; 	
	private String imagePath;
	public Restaurant() {
		super();
	}
	public Restaurant(int restaurantId, String restaurantName, String cuisineType, int deliveryTime, String address,
			int adminId, double rating, String isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminId = adminId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}
	public Restaurant(String restaurantName, String cuisineType, int deliveryTime, String address, int adminId,
			double rating, String isActive, String imagePath) {
		super();
		this.restaurantName = restaurantName;
		this.cuisineType = cuisineType;
		this.deliveryTime = deliveryTime;
		this.address = address;
		this.adminId = adminId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getCuisineType() {
		return cuisineType;
	}
	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setActive(String isActive) {
		this.isActive = isActive;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName + ", cuisineType="
				+ cuisineType + ", deliveryTime=" + deliveryTime + ", address=" + address + ", adminId=" + adminId
				+ ", rating=" + rating + ", isActive=" + isActive + ", imagePath=" + imagePath + "]";
	}
	
	
		
	
}
