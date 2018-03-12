package com.revature.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Callable {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		String sql;
		int value;
		
		try {
			
			prop.load(new FileReader("C:/Users/Revature/revature-trainer/FullStack/src/target/application.properties"));
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
			sql = "CALL insertTest(?, ?, ?)";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			
			// Binding values = same syntax as PreparedStatement
			cstmt.setInt(1, 4);
			cstmt.setString(2, "testFour");
			cstmt.setDouble(3, 4658.0);
			
			value = cstmt.executeUpdate();
			PreparedDriver.helper(value);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	

}
