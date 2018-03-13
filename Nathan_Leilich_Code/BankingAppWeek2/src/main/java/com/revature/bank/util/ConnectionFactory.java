package com.revature.bank.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * This class provides data base connections for DAO objects 
 */
public class ConnectionFactory {

	private static ConnectionFactory cf = null;
	private static boolean build = true;
	
	private ConnectionFactory() {
		
		build = false;
	}//constructor
	
///////////////////////////////////////
////Constructors and static getters////
///////////////////////////////////////
	
	public static synchronized ConnectionFactory getInstance() {
		
		if(build) {
			
			cf = new ConnectionFactory();
		}//if
		return cf;
	}//getInstance()
	
///////////////
////Methods////
///////////////
	
	/*
	 * returns a connection object allowing for access 
	 * to the bank database
	 */
	public Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		try {
			
			prop.load(new FileReader("C:/Users/nate/eclipse-revature-week2/BankingAppWeek2" + 
												"/src/main/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("usr"),
											   prop.getProperty("pwd"));
		}//try
		catch(SQLException se) {
			
			se.printStackTrace();
		}//catch
		catch(IOException e) {
			
			e.printStackTrace();
		}//catch
		catch(ClassNotFoundException ce){
			
			ce.printStackTrace();
		}//catch
		
		return conn;
	}//getConnection()
}//class
