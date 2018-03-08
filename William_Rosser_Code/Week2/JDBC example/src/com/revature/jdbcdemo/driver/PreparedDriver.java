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

		try {
			prop.load(new FileReader("src/app.properties"));
			Class.forName(prop.getProperty("driver"));
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
			sql = "INSERT INTO test VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql); // Faster & resistant to SQL injection
			//Value binding.
			//parameter index (which question mark), value
			pstmt.setInt(1, 5);
			pstmt.setString(2, "testOne");
			pstmt.setDouble(3, 27.0);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
