package edu.cqupt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static String DB_URL = "jdbc:mysql://localhost:3306/book?useSSL=false&useUnicode=true&characterEncoding=UTF8";
	private static String USERNAME = "root";
	private static String PASSWORD = "123456";
	
	static {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
