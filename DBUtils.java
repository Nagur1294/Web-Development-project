package com.tapfoods.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;

final public class DBUtils 
{
	private static String username="root";
	private static String password="Basha@123";
	private static String url="jdbc:mysql://localhost:3306/tapfoods";

	public static Connection myConnection()
	{
		Connection con=null;
			try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
