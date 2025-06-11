package com.tapfoods.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Menu;

public class MenuDAOImpl implements MenuDAO {
    private Connection con;
    private static final String ADD_MENU = "INSERT INTO `menu` (restaurantId, menuName, description, price, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM `menu`";
    private static final String SELECT_BY_ID = "SELECT * FROM `menu` WHERE `menuId`=?";
    private static final String SELECT_BY_RESTAURANT_ID = "SELECT * FROM `menu` WHERE `restaurantId`=?";
    private static final String UPDATE_BY_ID = "UPDATE `menu` SET restaurantId=?, menuName=?, description=?, price=?, isAvailable=?, imagePath=? WHERE `menuId`=?";
    private static final String DELETE_BY_ID = "DELETE FROM `menu` WHERE `menuId`=?";

    public MenuDAOImpl() {
        try {
            con = DBUtils.myConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addMenu(Menu menu) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(ADD_MENU)) {
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getImagePath()); // Include imagePath
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> menuList = new ArrayList<>();
        try (Statement stmt = con.createStatement(); ResultSet resultSet = stmt.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                Menu menu = extractFromResultSet(resultSet);
                menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BY_ID)) {
            pstmt.setInt(1, menuId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    menu = extractFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SELECT_BY_RESTAURANT_ID)) {
            pstmt.setInt(1, restaurantId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Menu menu = extractFromResultSet(resultSet);
                    menuList.add(menu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

    @Override
    public int updateMenu(Menu menu) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(UPDATE_BY_ID)) {
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getMenuName());
            pstmt.setString(3, menu.getDescription());
            pstmt.setDouble(4, menu.getPrice());
            pstmt.setBoolean(5, menu.getIsAvailable());
            pstmt.setString(6, menu.getImagePath()); // Include imagePath
            pstmt.setInt(7, menu.getMenuId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int deleteMenu(int menuId) {
        int status = 0;
        try (PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID)) {
            pstmt.setInt(1, menuId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private Menu extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Menu(
            resultSet.getInt("menuId"),
            resultSet.getInt("restaurantId"),
            resultSet.getString("menuName"),
            resultSet.getString("description"),
            resultSet.getDouble("price"),
            resultSet.getBoolean("isAvailable"),
            resultSet.getString("imagePath") // Extract imagePath from ResultSet
        );
    }
}
