package com.tapfoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            String newName = request.getParameter("name");
            String newEmail = request.getParameter("email");
            String newPassword = request.getParameter("password");
            String newAddress = request.getParameter("address");
            String newPhone = request.getParameter("phone");

            loggedInUser.setUsername(newName);
            loggedInUser.setEmail(newEmail);
            loggedInUser.setPassword(newPassword);
            loggedInUser.setAddress(newAddress);
            loggedInUser.setPhonenumber(newPhone);

            UserDAO userDAO = new UserDAOImpl();
            userDAO.updateUser(loggedInUser);

            session.setAttribute("loggedInUser", loggedInUser);
            response.sendRedirect("home.jsp"); 
        } else {
            response.sendRedirect("SignIn.jsp"); 
        }
    }
}
