package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {
	 private Connection con;
	    private static final String ADD_RESTAURANT = "INSERT INTO `restaurant` (restaurantName, cuisineType, deliveryTime, address, adminId, ratings, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    private static final String SELECT_ALL = "SELECT * FROM `restaurant`";
	    private static final String SELECT_BY_ID = "SELECT * FROM `restaurant` WHERE `restaurantId`=?";
	    private static final String UPDATE_BY_ID = "UPDATE `restaurant` SET restaurantName=?, cuisineType=?, deliveryTime=?, address=?, adminId=?, ratings=?, isActive=?, imagePath=? WHERE `restaurantId`=?";
	    private static final String DELETE_BY_ID = "DELETE FROM `restaurant` WHERE `restaurantId`=?";

	    public RestaurantDAOImpl() {
	        try {
	            con = DBUtils.myConnection();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public int addRestaurant(Restaurant r) {
	        int status = 0;
	        try (PreparedStatement pstmt = con.prepareStatement(ADD_RESTAURANT)) {
	            pstmt.setString(1, r.getRestaurantName());
	            pstmt.setString(2, r.getCuisineType());
	            pstmt.setInt(3, r.getDeliveryTime());
	            pstmt.setString(4, r.getAddress());
	            pstmt.setInt(5, r.getAdminId());
	            pstmt.setDouble(6, r.getRating());
	            pstmt.setString(7, r.getIsActive());
	            pstmt.setString(8, r.getImagePath());
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public List<Restaurant> getAllRestaurants() {
	    	
	        List<Restaurant> restaurantList = new ArrayList<>();
	        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
	        	
	            while (resultSet.next()) {
	                Restaurant restaurant = extractFromResultSet(resultSet);
	                restaurantList.add(restaurant);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return restaurantList;
	    }

	    @Override
	    public Restaurant getRestaurantById(int restaurantId) {
	        Restaurant restaurant = null;
	        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID)) {
	            pstmt.setInt(1, restaurantId);
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                    restaurant = extractFromResultSet(resultSet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return restaurant;
	    }

	    @Override
	    public int updateRestaurant(Restaurant r) {
	        int status = 0;
	        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_BY_ID)) {
	            pstmt.setString(1, r.getRestaurantName());
	            pstmt.setString(2, r.getCuisineType());
	            pstmt.setInt(3, r.getDeliveryTime());
	            pstmt.setString(4, r.getAddress());
	            pstmt.setInt(5, r.getAdminId());
	            pstmt.setDouble(6, r.getRating());
	            pstmt.setString(7, r.getIsActive());
	            pstmt.setString(8, r.getImagePath());
	            pstmt.setInt(9, r.getRestaurantId());
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public int deleteRestaurant(int restaurantId) {
	        int status = 0;
	        try (PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID)) {
	            pstmt.setInt(1, restaurantId);
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    private Restaurant extractFromResultSet(ResultSet resultSet) throws SQLException {
	        return new Restaurant(
	                resultSet.getInt("restaurantId"),
	                resultSet.getString("restaurantName"),
	                resultSet.getString("cuisineType"),
	                resultSet.getInt("deliveryTime"),
	                resultSet.getString("address"),
	                resultSet.getInt("adminId"),
	                resultSet.getDouble("ratings"),
	                resultSet.getString("isActive"),
	                resultSet.getString("imagePath")
	        );
	    }
}
