package com.revature.jdbcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CallableDriver {
	public static void main(String[] args) {
		Properties prop = new Properties();
		String sql;
		int value;
		
		try {
			
			prop.load(new FileReader("C:/Users/Thomas/Desktop/my_git_repos/BatchRepository/Thomas_Hemming_Code/Week2/jdbcDemo/src/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"), 
				prop.getProperty("pwd"))) {
			
			// EXAMPLE: CALLING A STORED PROCEDURE
			// Notice that our SQL string looks similar to a PreparedStatement,
			// except we surround the entire statement with curly brackets
			sql = "{CALL insertTest(?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			// Binding values = same syntax as PreparedStatement
			cstmt.setInt(1, 3);
			cstmt.setString(2, "testTwo");
			cstmt.setDouble(3, 456.78);
			
			value = cstmt.executeUpdate();
			
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
