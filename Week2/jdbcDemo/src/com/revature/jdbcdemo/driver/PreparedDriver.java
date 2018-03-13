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
		int value;
		
		try {
			
			prop.load(new FileReader("C:/Users/Revature/revature-trainer/jdbcDemo/src/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
			
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"), 
				prop.getProperty("pwd"))) {
			
			// EXAMPLE: INSERT INTO TABLE USING PREPAREDSTATEMENT
			sql = "INSERT INTO test VALUES (?, ?, ?)";
			
			// Similar syntax, except we use the Connection method 'prepareStatement'
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// Here we are going to bind the values of our parameters inside of our
			// parameterized query!
			pstmt.setInt(1, 1);
			pstmt.setString(2, "testOne");
			pstmt.setDouble(3, 42.0);
			
			// Execute as usual
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//---------------------------------
			
			pstmt.setInt(1, 2);
			pstmt.setString(2, "testTwo");
			pstmt.setDouble(3, 99.99);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//---------------------------------
			
			pstmt.setInt(1, 3);
			pstmt.setString(2, "testThree");
			pstmt.setDouble(3, 54.55);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//---------------------------------
			
			pstmt.setInt(1, 4);
			pstmt.setString(2, "testFour");
			pstmt.setDouble(3, 4875.1);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//---------------------------------------------------------------------------
			
			// EXAMPLE: UPDATING RECORDS USING PREPAREDSTATEMENT
			//A simple update
			sql = "UPDATE test SET test_value = ? WHERE test_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, 5000.0);
			pstmt.setInt(2, 3);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//-----------------------------------------------------------------
			
			// EXAMPLE: DELETING RECORDS USING PREPAREDSTATEMENT
			// A simple delete
			sql = "DELETE FROM test WHERE test_value > ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, 1000.00);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//-----------------------------------------------------------------

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void helper(int value) {
		if(value == 0) {
			System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
		} else {
			System.out.println(value + " rows affected!");
		}
	}
}
