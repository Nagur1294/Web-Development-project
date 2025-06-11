	package com.tapfoods.dao;

	import java.util.ArrayList;
	import com.tapfoods.model.OrderItem;

	public interface OrderItemDAO {
	    int addOrderItem(OrderItem orderItem);
	    ArrayList<OrderItem> getAllOrderItems();
	    OrderItem getSpecificOrderItem(int orderItemId);
	    int deleteOrderItem(int orderItemId);
		ArrayList<OrderItem> getOrderItemsByOrderId(int orderId);
	}
