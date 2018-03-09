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
		
		String sql = "";
		int value = 0;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:/Users/riley/my_git_repos/BatchRepository/Riley_Mathis_Code/Week1/jdbcDemo/src/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("usr"),
				prop.getProperty("pwd"))){
			
			//Example: insert into table using preparedstatements
			sql = "INSERT INTO test VALUES (?, ?, ?)";
			
			//similar syntax, except we use the connection method
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//here we will bind the values of our parameters inside of our parameterized query
			pstmt.setInt(1, 1);
			pstmt.setString(2, "testOne");
			pstmt.setDouble(3, 1.1);
			
			value = pstmt.executeUpdate();
			
			PreparedDriver.helper(value);
			
			
			pstmt.setInt(1, 2);
			pstmt.setString(2, "testTwo");
			pstmt.setDouble(3, 1.2);
			
			value = pstmt.executeUpdate();
			
			PreparedDriver.helper(value);
			
			
			//EXAMPLE: updating records using preparedstatements
			sql = "UPDATE test SET test_value = ? WHERE test_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, 300);
			pstmt.setInt(2, 2);
			
			pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			
			//EXAMPLE: deleting records using preparedstatements
//			sql = "DELETE FROM test WHERE test_value > ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setDouble(1, 100);
//			
//			value = pstmt.executeUpdate();
//			PreparedDriver.helper(value);
//			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void helper(int value) {
		if(value==0) {
			System.out.println(value + " rows affected. Maybe ddl statement?");
		}
		else {
			System.out.println(value + " rows affected.");
		}
	}
}
