package com.tapfoods.daoimpl;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import com.tapfoods.dao.OrderDAO;
	import com.tapfoods.dbutils.DBUtils;
	import com.tapfoods.model.Order;

	public class OrderDAOImpl implements OrderDAO {
	    private Connection con;
	    private static final String ADD_ORDER = "INSERT INTO `order` (restaurantId, userId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
	    private static final String SELECT_ALL = "SELECT * FROM `order`";
	    private static final String SELECT_BY_ID = "SELECT * FROM `order` WHERE `orderId`=?";

	    public OrderDAOImpl() {
	        try {
	            con = DBUtils.myConnection();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public int addOrder(Order order) {
	        int status = 0;
	        try (PreparedStatement pstmt = con.prepareStatement(ADD_ORDER)) {
	            pstmt.setInt(1, order.getRestaurantId());
	            pstmt.setInt(2, order.getUserId());
	            pstmt.setTimestamp(3, new java.sql.Timestamp(order.getOrderDate().getTime()));
	            pstmt.setDouble(4, order.getTotalAmount());
	            pstmt.setString(5, order.getStatus());
	            pstmt.setString(6, order.getPaymentMode());
	            status = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return status;
	    }

	    @Override
	    public List<Order> getAllOrders() {
	        List<Order> orderList = new ArrayList<>();
	        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
	            while (resultSet.next()) {
	                Order order = extractFromResultSet(resultSet);
	                orderList.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return orderList;
	    }

	    @Override
	    public Order getSpecificOrderById(int orderId) {
	        Order order = null;
	        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID)) {
	            pstmt.setInt(1, orderId);
	            try (ResultSet resultSet = pstmt.executeQuery()) {
	                if (resultSet.next()) {
	                    order = extractFromResultSet(resultSet);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return order;
	    }

	    private Order extractFromResultSet(ResultSet resultSet) throws SQLException {
	        return new Order(
	                resultSet.getInt("orderId"),
	                resultSet.getInt("restaurantId"),
	                resultSet.getInt("userId"),
	                resultSet.getTimestamp("orderDate"),
	                resultSet.getDouble("totalAmount"),
	                resultSet.getString("status"),
	                resultSet.getString("paymentMode")
	        );
	    }
}
