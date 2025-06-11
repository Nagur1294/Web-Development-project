package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.RestaurantDAOImpl;
import com.tapfoods.model.Restaurant;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {

	
	private RestaurantDAO restaurantDAO;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		restaurantDAO = new RestaurantDAOImpl();
		List<Restaurant> restaurantList = restaurantDAO.getAllRestaurants();
		 
		HttpSession session = req.getSession();
		session.setAttribute("allRestaurants", restaurantList);
		resp.sendRedirect("home.jsp");
	}
}
