package com.wipro.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getDBConnection() {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="yugav123";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connect=DriverManager.getConnection(url,user,password);
			return connect;
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}


}
