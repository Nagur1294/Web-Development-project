package com.tapfoods.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tapfoods.dao.OrderHistoryDAO;
import com.tapfoods.dao.RestaurantDAO;
import com.tapfoods.daoimpl.OrderHistoryDAOImpl;
import com.tapfoods.model.OrderHistory;
import com.tapfoods.model.Restaurant;
import com.tapfoods.model.User;

@WebServlet("/OrderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderHistoryDAO orderHistoryDAO;

    @Override
    public void init() throws ServletException {
        orderHistoryDAO = new OrderHistoryDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect("SignIn.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            resp.sendRedirect("SignIn.jsp");
            return;
        }

        int userId = user.getUserId();
        List<OrderHistory> orderHistoryList = orderHistoryDAO.getOrderHistoriesByUserId(userId);
        

        req.setAttribute("orderHistoryList", orderHistoryList);
        req.getRequestDispatcher("OrderHistory.jsp").forward(req, resp);
    }
}
