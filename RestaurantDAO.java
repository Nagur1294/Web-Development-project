	package com.tapfoods.dao;

	import java.util.List;
	import com.tapfoods.model.Restaurant;

	public interface RestaurantDAO {
	    
	    // Add a new restaurant and return the generated restaurantId
	    int addRestaurant(Restaurant r);
	    
	    // Retrieve all restaurants
	    List<Restaurant> getAllRestaurants();
	    
	    // Retrieve a restaurant by its ID
	    Restaurant getRestaurantById(int restaurantId);
	    
	    // Update restaurant details
	    int updateRestaurant(Restaurant r);
	    
	    // Delete a restaurant by its ID
	    int deleteRestaurant(int restaurantId);
	}

