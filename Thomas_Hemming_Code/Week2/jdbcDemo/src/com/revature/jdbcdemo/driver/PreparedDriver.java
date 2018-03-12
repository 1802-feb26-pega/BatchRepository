package com.revature.jdbcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedDriver {
	
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		String sql;
		PreparedStatement pstmt = null;
		int value;
		
		try {
			prop.load(new FileReader("C:/Users/Thomas/Desktop/my_git_repos/BatchRepository/Thomas_Hemming_Code/Week2/jdbcDemo/src/resources/application.properties"));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		System.out.println(prop.getProperty("url"));
//		System.out.println(prop.getProperty("usr"));
//		System.out.println(prop.getProperty("pwd"));
		
		try (Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"), 
				prop.getProperty("pwd"))) {
			
			//EXAMPLE: INSERT INTO TABLE USING PREPAREDSTATEMENT
			sql = "INSERT INTO test VALUES (?, ?, ?)";
			
			// Similar syntax, except we use the Connection method prepareStatement
			pstmt = conn.prepareStatement(sql); //prepared statements are pre-compiled
			
			// Here we are going to bind the values of our parameters inside of our 
			// parameterized query!
			
			pstmt.setInt(1, 1);
			pstmt.setString(2, "testOne");
			pstmt.setDouble(3,  42.0);
			
			value = pstmt.executeUpdate();
			helper(value);
			
			
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void helper(int value) {
		if (value == 0) {
			System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
		} else {
			System.out.println(value + " rows affected!");
		}
	}
}
