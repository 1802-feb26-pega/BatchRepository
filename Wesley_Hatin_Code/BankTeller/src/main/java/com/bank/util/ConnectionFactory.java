package com.bank.util;

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
		Properties prop = new Properties();
		Connection conn = null;
		
		try {
			prop.load(new FileReader("C:\\Users\\whati\\eclipse-workspace\\BatchRepository\\Wesley_Hatin_Code\\BankTeller\\src\\resources"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("user"), prop.getProperty("password"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

}

