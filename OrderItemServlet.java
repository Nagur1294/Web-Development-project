package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderItemDAO;
import com.tapfoods.daoimpl.OrderItemDAOImpl;
import com.tapfoods.model.OrderItem;

/**
 * Servlet implementation class OrderItemServlet
 */
@WebServlet("/HistoryDetails")
public class OrderItemServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int orderId=Integer.parseInt(req.getParameter("orderId"));
		OrderItemDAO orderDAO=new OrderItemDAOImpl();
		List<OrderItem> orderitemList = orderDAO.getOrderItemsByOrderId(orderId);
		HttpSession session=req.getSession();
		session.setAttribute("orderitemList",orderitemList );
		resp.sendRedirect("DisplayList.jsp");
	}
   
}
