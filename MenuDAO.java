package com.tapfoods.dao;

import java.util.List;
	import com.tapfoods.model.Menu;

	public interface MenuDAO {

	    int addMenu(Menu menu);

	    List<Menu> getAllMenus();

	    Menu getMenuById(int menuId);

	    List<Menu> getMenusByRestaurantId(int restaurantId);

	    int updateMenu(Menu menu);

	    int deleteMenu(int menuId);

}
