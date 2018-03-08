package com.revature.jdbcdemo.driver;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class Callable {
	public static void main(String[] args) {
		Properties prop = new Properties();
		System.out.println("Connecting to our database...");
		
		try {
			prop.load(new FileReader("src/app.properties"));
			Class.forName(prop.getProperty("driver"));
			System.out.println("Connected to the database.");
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("usr"),
				prop.getProperty("pwd")
				
				)) {
			String sql = "{CALL insertTest(?,?,?)}"; // Procedures are wrapped in {}
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, 6);
			cstmt.setString(2, "cstmt test6");
			cstmt.setDouble(3, 1.6);
			help(cstmt.executeUpdate());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Exiting the database.");
		}
		
	}
	
	private static void help(int value) {
		if (value == 0) {
			System.out.println(value + " rows affected. Maybe this was a DDL statement?");
		} else {
			System.out.println(value + " rows affected.");
		}
	}
}
