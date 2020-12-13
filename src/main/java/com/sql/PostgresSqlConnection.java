package com.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

public class PostgresSqlConnection {
	
private static Connection connection;
	
	private PostgresSqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DbUtil.DRIVER);
		String url=DbUtil.URL;			
		String username="postgres";
		String password="Bacon123";
		connection=DriverManager.getConnection(url, username, password);
		return connection;
	}

}
