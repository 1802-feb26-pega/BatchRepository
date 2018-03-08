package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoDriver {

	public static void main(String[] args) {
		System.out.println("Connecting to our database...");
		
		//Load & Register driver class.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo","demo");
			System.out.println("Connected to the database.");
			conn.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Exiting the database.");
		}
		
		
	}

}
