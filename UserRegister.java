package com.tapfoods.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.daoimpl.UserDAOImpl;
import com.tapfoods.model.User;

@WebServlet("/register")
public class UserRegister extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phonenumber = req.getParameter("phonenumber");
        String password = req.getParameter("password");
        String address = req.getParameter("address");

        UserDAO userDAO = new UserDAOImpl();
        User user = null;
        try {
            user = new User(username, email, phonenumber, password, address);
            int status = userDAO.addUser(user);
            if (status > 0) {
                resp.getWriter().println("User registered successfully.");
            } else {
                resp.getWriter().println("User registration failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().println("User registration failed due to an error.");
        }
    }
}
