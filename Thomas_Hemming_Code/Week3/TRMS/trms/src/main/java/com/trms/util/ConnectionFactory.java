package com.trms.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
	private ConnectionFactory(){
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance(){
		if(build == true){
			cf = new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try{
			Properties prop = new Properties();
			String path = "C:\\Users\\Thomas\\Desktop\\TRMS\\trms\\src\\main\\java\\com\\trms\\util\\database.properties";
			prop.load(new FileReader(path));
			
			// register JDBC driver
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"), 
					prop.getProperty("pwd"));
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	
}