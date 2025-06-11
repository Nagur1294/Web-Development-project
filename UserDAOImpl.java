package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfoods.dao.UserDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.User;

public class UserDAOImpl implements UserDAO {
    private static final String ADD_USER = "INSERT INTO user (userName, email, phoneNumber, password, address) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `user`";
    private static final String SELECT_ON_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
    private static final String UPDATE_ON_USERID = "UPDATE `user` SET `username` = ?, `phonenumber` = ?, `password` = ?, `address` = ?, `email` = ? WHERE `userId` = ?";
    private static final String DELETE_ON_EMAIL = "DELETE FROM `user` WHERE `email` = ?";

    private Connection con;

    public UserDAOImpl() {
        try {
            con = DBUtils.myConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addUser(User u) {
        try (PreparedStatement pstmt = con.prepareStatement(ADD_USER)) {
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getEmail());
            pstmt.setString(3, u.getPhonenumber());
            pstmt.setString(4, u.getPassword());
            pstmt.setString(5, u.getAddress());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("password"),
                        resultSet.getString("address")
                );
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUser(String email) {
        User user = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_ON_EMAIL)) {
            pstmt.setString(1, email);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("userId"),
                            resultSet.getString("username"),
                            resultSet.getString("email"),
                            resultSet.getString("phonenumber"),
                            resultSet.getString("password"),
                            resultSet.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int updateUser(User u) {
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_ON_USERID)) {
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getPhonenumber());
            pstmt.setString(3, u.getPassword());
            pstmt.setString(4, u.getAddress());
            pstmt.setString(5, u.getEmail());
            pstmt.setInt(6, u.getUserId()); // Set userId for the update
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteUser(String email) {
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_ON_EMAIL)) {
            pstmt.setString(1, email); // Use email for deletion
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
