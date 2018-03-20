package com.pchase95.project1.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf;
	private static boolean shouldBuild = true;
	private static Properties prop;
	
	private ConnectionFactory() {
		prop = new Properties();
		shouldBuild = false;
		try {
			prop.load(new FileReader("C:/Users/Peter/Desktop/Revature/BatchRepository/Peter_Chase_Code/Week3/project1/src/main/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getInstance() {
		if (shouldBuild) {
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {			
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
