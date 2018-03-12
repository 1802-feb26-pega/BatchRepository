package com.revature.jdbcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedDriver {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:\\Users\\ammon\\my_git_repos\\jdbcDemo\\src\\resources"));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"), prop.getProperty("pwd"))) {
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void helper(int value) {
		if (value == 0) {
			System.out.println(value + " rows affected. maybe this statement was a DDL statement?");
		} else {
			System.out.println(value + "rows affected.");
		}
	}
}
