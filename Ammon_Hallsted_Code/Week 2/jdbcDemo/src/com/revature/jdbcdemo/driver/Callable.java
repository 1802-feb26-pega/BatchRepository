package com.revature.jdbcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Callable {
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		String sql;
		int values = 0;
		
		try {
			prop.load(new FileReader("C:\\Users\\ammon\\my_git_repos\\jdbcDemo\\src\\resources"));
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection(
				prop.getProperty("url"),
				prop.getProperty("usr"),
				prop.getProperty("pwd"))) {
			
			sql = "CALL insertTest(?, ?, ?)";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			
			
			
		}
		
	}
}
