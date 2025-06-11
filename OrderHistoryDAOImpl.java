	package com.tapfoods.daoimpl;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.OrderHistory;

	public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	    private Connection con;
	    private PreparedStatement pstmt;
	    private Statement stmt;
	    private ResultSet resultSet;
	    private int status = 0;
	    private ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();

	    private static final String ADD_ORDER_HISTORY = "INSERT INTO orderhistory (orderId, userId, orderDate, totalAmount, status) VALUES (?, ?, ?, ?, ?)";
	    private static final String SELECT_ALL_ORDER_HISTORIES = "SELECT * FROM orderhistory";
	    private static final String SELECT_ORDER_HISTORY_BY_ID = "SELECT * FROM orderhistory WHERE orderHistoryId = ?";
	    private static final String GET_ORDER_HISTORY_BY_USER_ID =  "SELECT * FROM orderHistory WHERE userId = ?";

	    public OrderHistoryDAOImpl() {
	        try {
	            con = DBUtils.myConnection();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public int addOrderHistory(OrderHistory orderHistory) {
	        try {
	            pstmt = con.prepareStatement(ADD_ORDER_HISTORY);
	            pstmt.setInt(1, orderHistory.getOrderId());
	            pstmt.setInt(2, orderHistory.getUserId());
	            pstmt.setTimestamp(3, new java.sql.Timestamp(orderHistory.getOrderDate().getTime()));
	            pstmt.setDouble(4, orderHistory.getTotalAmount());
	            pstmt.setString(5, orderHistory.getStatus());
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public ArrayList<OrderHistory> getAllOrderHistories() {
	        try {
	            stmt = con.createStatement();
	            resultSet = stmt.executeQuery(SELECT_ALL_ORDER_HISTORIES);
	            orderHistoryList = extractFromResultSet(resultSet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderHistoryList;
	    }

	    @Override
	    public OrderHistory getSpecificOrderHistory(int orderHistoryId) {
	        OrderHistory orderHistory = null;
	        try {
	            pstmt = con.prepareStatement(SELECT_ORDER_HISTORY_BY_ID);
	            pstmt.setInt(1, orderHistoryId);
	            resultSet = pstmt.executeQuery();
	            if (resultSet.next()) {
	                orderHistory = new OrderHistory(
	                    resultSet.getInt("orderHistoryId"),
	                    resultSet.getInt("orderId"),
	                    resultSet.getInt("userId"),
	                    resultSet.getTimestamp("orderDate"),
	                    resultSet.getDouble("totalAmount"),
	                    resultSet.getString("status")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderHistory;
	    }

	    private ArrayList<OrderHistory> extractFromResultSet(ResultSet resultSet) {
	        ArrayList<OrderHistory> orderHistoryList = new ArrayList<>();
	        try {
	            while (resultSet.next()) {
	                OrderHistory orderHistory = new OrderHistory(
	                    resultSet.getInt("orderHistoryId"),
	                    resultSet.getInt("orderId"),
	                    resultSet.getInt("userId"),
	                    resultSet.getTimestamp("orderDate"),
	                    resultSet.getDouble("totalAmount"),
	                    resultSet.getString("status")
	                );
	                orderHistoryList.add(orderHistory);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderHistoryList;
	    }
	    @Override
	    public List<OrderHistory> getOrderHistoriesByUserId(int userId) {
	        List<OrderHistory> orderHistoryList = new ArrayList<>();
	        
	        try {
	            pstmt = con.prepareStatement(GET_ORDER_HISTORY_BY_USER_ID);
	            pstmt.setInt(1, userId);
	            resultSet = pstmt.executeQuery();
	            
	            while (resultSet.next()) {
	                OrderHistory orderHistory = new OrderHistory(
	                    resultSet.getInt("orderHistoryId"),
	                    resultSet.getInt("orderId"),
	                    resultSet.getInt("userId"),
	                    resultSet.getTimestamp("orderDate"),
	                    resultSet.getDouble("totalAmount"),
	                    resultSet.getString("status")
	                );
	                
	                orderHistoryList.add(orderHistory);
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return orderHistoryList;
	    }

	}
