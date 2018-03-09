package com.revature.jdbcdemo.driver;

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
			
			prop.load(new FileReader("C:/Users/riley/my_git_repos/BatchRepository/Riley_Mathis_Code/Week1/jdbcDemo/src/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			
			//EXAMPLE: calling a stored procedure
			//notice that entire statement is surrounded by curly brackets
			sql = "{CALL insertTest(?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, 3);
			cstmt.setString(2, "testTwo");
			cstmt.setDouble(3, 43.24);
			
			value = cstmt.executeUpdate();
			PreparedDriver.helper(value);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
}
