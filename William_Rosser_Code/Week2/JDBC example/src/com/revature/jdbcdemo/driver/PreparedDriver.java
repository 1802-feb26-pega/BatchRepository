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
		
		String sql = "";
		int value = 0;
		
		try (Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"), 
				prop.getProperty("pwd"))) {
			
			//Example: Insert into table using prepared statement
			sql = "TRUNCATE TABLE test";
			PreparedStatement pstmt = conn.prepareStatement(sql); // Faster & resistant to SQL injection
			pstmt.executeUpdate();
			
			sql = "INSERT INTO test VALUES (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//Value binding.
			//parameter index (which question mark), value
			pstmt.setInt(1, 1);
			pstmt.setString(2, "testOne");
			pstmt.setDouble(3, 1.1);
			help(pstmt.executeUpdate());
			
			pstmt.setInt(1, 2);
			pstmt.setString(2, "testTwo");
			pstmt.setDouble(3, 2.2);
			help(pstmt.executeUpdate());
			
			pstmt.setInt(1, 3);
			pstmt.setString(2, "testThree");
			pstmt.setDouble(3, 3.3);
			help(pstmt.executeUpdate());
			
			
			//Updating records
			sql = "UPDATE test SET test_value = ? WHERE test_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, 5000.0);
			pstmt.setInt(2, 3);
			help(pstmt.executeUpdate());
			
			//Deleting records
			sql = "DELETE FROM test WHERE test_value > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, 1000);
			help(pstmt.executeUpdate());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
