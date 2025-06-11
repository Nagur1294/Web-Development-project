package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Menu;
import com.tapfoods.model.Restaurant;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	
       @Override
    	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	   
    		int restaurantId=Integer.parseInt(req.getParameter("restaurantId"));
    		MenuDAOImpl menudao=new MenuDAOImpl();
    		List<Menu> menulist=menudao.getMenusByRestaurantId(restaurantId);	
    		HttpSession session=req.getSession();
    		session.setAttribute("menuList",menulist);
    		
    		resp.sendRedirect("Menu.jsp");
      
   }
    		
}
