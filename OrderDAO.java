package com.tapfoods.dao;

	import java.util.List;
	import com.tapfoods.model.Order;
import com.tapfoods.model.OrderItem;

	public interface OrderDAO {

	    int addOrder(Order order);

	    List<Order> getAllOrders();

	    Order getSpecificOrderById(int orderId);

	}

