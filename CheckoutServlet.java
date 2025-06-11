package com.tapfoods.controller;

import com.tapfoods.model.Cart;
import com.tapfoods.model.CartItem;
import com.tapfoods.model.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/confirmOrder")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve cart from session
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            response.sendRedirect("Cart.jsp");
            return;
        }

        // Retrieve user from session
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect("SignIn.jsp");
            return;
        }

        // Get parameters from form
        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");
        double total = 0.0;
        Map<Integer, CartItem> items = cart.getItems();

        // Calculate total amount
        for (CartItem item : items.values()) {
            total += item.getPrice() * item.getQuantity();
        }

        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement orderItemStmt = null;
        PreparedStatement orderHistoryStmt = null;
        ResultSet rs = null;

        try {
            // Load database driver and connect to database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfoods", "root", "Basha@123");
            conn.setAutoCommit(false); // Disable auto-commit

            // Insert order into database
            String orderSql = "INSERT INTO `order` (restaurantId, userId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, NOW(), ?, 'pending', ?)";
            orderStmt = conn.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

            // Assuming there's only one restaurant for this order; adjust as needed
            int restaurantId = items.values().iterator().next().getRestaurantId();

            orderStmt.setInt(1, restaurantId);
            orderStmt.setInt(2, user.getUserId());
            orderStmt.setDouble(3, total);
            orderStmt.setString(4, paymentMethod);
            orderStmt.executeUpdate();

            // Get the generated order ID
            rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert each cart item into the orderItem table
            String orderItemSql = "INSERT INTO orderItem (orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?)";
            orderItemStmt = conn.prepareStatement(orderItemSql);
            for (CartItem item : items.values()) {
                orderItemStmt.setInt(1, orderId);
                orderItemStmt.setInt(2, item.getItemId()); // Use itemId instead of menuId
                orderItemStmt.setInt(3, item.getQuantity());
                orderItemStmt.setDouble(4, item.getPrice() * item.getQuantity());
                orderItemStmt.executeUpdate();
            }

            // Insert order history into orderHistory table
            String orderHistorySql = "INSERT INTO orderHistory (orderId, userId, orderDate, totalAmount, status) VALUES (?, ?, NOW(), ?, 'pending')";
            orderHistoryStmt = conn.prepareStatement(orderHistorySql);
            orderHistoryStmt.setInt(1, orderId);
            orderHistoryStmt.setInt(2, user.getUserId());
            orderHistoryStmt.setDouble(3, total);
            orderHistoryStmt.executeUpdate();

            // Commit transaction
            conn.commit();

            // Clear the cart and redirect to order confirmation page
            session.setAttribute("cart", new Cart());
            response.sendRedirect("OrderConfirm.jsp");

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // Rollback transaction on error
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
            response.sendRedirect("Error.jsp");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (orderStmt != null) orderStmt.close();
                if (orderItemStmt != null) orderItemStmt.close();
                if (orderHistoryStmt != null) orderHistoryStmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
