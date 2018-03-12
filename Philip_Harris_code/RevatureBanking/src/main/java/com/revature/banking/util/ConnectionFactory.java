package com.revature.banking.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
/*
 * 
 * This code is for the SINGLETON DESIGN PATTERN
 * private ConnectionFactory() {
		build = false;
	}
	public static synchronized ConnectionFactory getInstance() {
		
		if(build == true) {
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
 * */
 
	
	//The connections factory is a singleton and factory based connection
	//There can only be on instance of the connection at all times
	//Which reduces the ammount of conns that have to open and close.
	//Also contains the DB info in a property file
	private ConnectionFactory() {
		build = false;
	}
	public static synchronized ConnectionFactory getInstance() {
		
		if(build == true) {
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		Properties prop = new Properties();
		
		
		try {
			prop.load(new FileReader(
					"C:/Users/phili/my_git_repos/BatchRepository/Philip_Harris_code/RevatureBanking/src/main/resources/Application.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("password"));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
