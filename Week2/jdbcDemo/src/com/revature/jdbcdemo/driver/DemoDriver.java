package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoDriver {
	
	public static void main(String[] args) {
		
		// 1) Simple sysout
		System.out.println("Connecting to our database...");
		
		// 2) Obtain the proper driver class
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Alternate way: DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe"", "demo", "demo"
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo", "demo");
			
			conn.close();
				
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Connected to the database! Yay!");
	}

}
