	package com.tapfoods.daoimpl;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import com.tapfoods.dao.OrderItemDAO;
	import com.tapfoods.dbutils.DBUtils;
	import com.tapfoods.model.OrderItem;

	public class OrderItemDAOImpl implements OrderItemDAO {
	    private Connection con;
	    private PreparedStatement pstmt;
	    private Statement stmt;
	    private ResultSet resultSet;
	    private int status = 0;
	    private ArrayList<OrderItem> orderItemList = new ArrayList<>();

	    private static final String ADD_ORDER_ITEM = "INSERT INTO orderitem (orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?)";
	    private static final String SELECT_ALL_ORDER_ITEMS = "SELECT * FROM orderitemt";
	    private static final String SELECT_ORDER_ITEM_BY_ID = "SELECT * FROM orderitem WHERE orderItemId = ?";
	    private static final String DELETE_ORDER_ITEM_BY_ID = "DELETE FROM orderitem WHERE orderItemId = ?";
	   private static final String query = "SELECT * FROM orderitem WHERE orderId = ?";

	    public OrderItemDAOImpl() {
	        try {
	            con = DBUtils.myConnection();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public int addOrderItem(OrderItem orderItem) {
	        try {
	            pstmt = con.prepareStatement(ADD_ORDER_ITEM);
	            pstmt.setInt(1, orderItem.getOrderId());
	            pstmt.setInt(2, orderItem.getMenuId());
	            pstmt.setInt(3, orderItem.getQuantity());
	            pstmt.setDouble(4, orderItem.getSubTotal());
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public ArrayList<OrderItem> getAllOrderItems() {
	        try {
	            stmt = con.createStatement();
	            resultSet = stmt.executeQuery(SELECT_ALL_ORDER_ITEMS);
	            orderItemList = extractFromResultSet(resultSet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderItemList;
	    }
	    
	    @Override
	    public ArrayList<OrderItem> getOrderItemsByOrderId(int orderId) {
	       
	        try {
	            pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, orderId);
	            resultSet = pstmt.executeQuery();
	            orderItemList = extractFromResultSet(resultSet);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return orderItemList;
	    }

	    @Override
	    public OrderItem getSpecificOrderItem(int orderItemId) {
	        OrderItem orderItem = null;
	        try {
	            pstmt = con.prepareStatement(SELECT_ORDER_ITEM_BY_ID);
	            pstmt.setInt(1, orderItemId);
	            resultSet = pstmt.executeQuery();
	            if (resultSet.next()) {
	                orderItem = new OrderItem(
	                    resultSet.getInt("orderItemId"),
	                    resultSet.getInt("orderId"),
	                    resultSet.getInt("menuId"),
	                    resultSet.getInt("quantity"),
	                    resultSet.getDouble("subTotal")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderItem;
	    }

	    @Override
	    public int deleteOrderItem(int orderItemId) {
	        try {
	            pstmt = con.prepareStatement(DELETE_ORDER_ITEM_BY_ID);
	            pstmt.setInt(1, orderItemId);
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    private ArrayList<OrderItem> extractFromResultSet(ResultSet resultSet) {
	        ArrayList<OrderItem> orderItemList = new ArrayList<>();
	        try {
	            while (resultSet.next()) {
	                OrderItem orderItem = new OrderItem(
	                    resultSet.getInt("orderItemId"),
	                    resultSet.getInt("orderId"),
	                    resultSet.getInt("menuId"),
	                    resultSet.getInt("quantity"),
	                    resultSet.getDouble("subTotal")
	                );
	                orderItemList.add(orderItem);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderItemList;
	    }
	}
