	package com.tapfoods.dao;

	import java.util.ArrayList;
import java.util.List;

import com.tapfoods.model.OrderHistory;

	public interface OrderHistoryDAO {
	    int addOrderHistory(OrderHistory orderHistory);
	    ArrayList<OrderHistory> getAllOrderHistories();
	    OrderHistory getSpecificOrderHistory(int orderHistoryId);
	    List<OrderHistory> getOrderHistoriesByUserId(int userId);
	}

