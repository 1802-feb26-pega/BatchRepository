package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoDriver {
	public static void main(String[] args) {
		
		System.out.println("Connecting to our Database...");
		
		// Obtain the proper driver class
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo", "demo");
			
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Conncected to the database!");
	}
}
