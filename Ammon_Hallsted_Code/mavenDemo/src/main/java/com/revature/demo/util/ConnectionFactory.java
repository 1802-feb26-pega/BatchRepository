package com.revature.demo.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
	private ConnectionFactory() {
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance() {
		
		if(build==true) {
			cf = new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileReader("C:\\Users\\ammon\\my_git_repos\\mavenDemo\\src\\main\\java\\com\\revature\\demo\\util"));
			
			Class.forName(prop.getProperty("driver"));
			
			try {
				conn = DriverManager.getConnection(
						prop.getProperty("url"), 
						prop.getProperty("usr"),
						prop.getProperty("pwd"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return conn;
	}
}
