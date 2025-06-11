package com.tapfoods.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.daoimpl.MenuDAOImpl;
import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.Menu;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		if(cart==null)
		{
			cart=new Cart();
			session.setAttribute("cart",cart);
		}
		String action=request.getParameter("action");
		if(action.equals("add"))
		{
			addItemToCart(request,cart);
		}
		else if("update".equals(action))
		{
			updateCartItem(request,cart);
		}
		else if("remove".equals(action))
		{
			removeItemFromCart(request,cart);
		}
		session.setAttribute("cart",cart);
		response.sendRedirect("Cart.jsp");
	}

	private void removeItemFromCart(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    }

	private void updateCartItem(HttpServletRequest request, Cart cart) {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        cart.updateItem(itemId, quantity);
    }

	 private void addItemToCart(HttpServletRequest request, Cart cart) {
	        int itemId = Integer.parseInt(request.getParameter("itemId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));

	        MenuDAO menuDAO = new MenuDAOImpl();
	        Menu menuItem = menuDAO.getMenuById(itemId);

	        if (menuItem != null) {
	            CartItem item = new CartItem(
	                menuItem.getMenuId(),
	                menuItem.getRestaurantId(),
	                menuItem.getMenuName(),
	                menuItem.getPrice(),
	                quantity,
	                quantity*menuItem.getPrice()
	            );
	            cart.addItem(item);
		}
	 }
}

