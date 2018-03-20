package com.revature.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
* <h1> ConnectionFactory </h1> 
* The ConnectionFactory class includes a ConnectionFactory and a boolean.  The ConnectionFactory can only create
* one build per program run.
*
*/
public class ConnectionFactory
{
	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
	/**
	 * This is the default constructor for the ConnectionFactory class.
	 * It is set to private so only ConnectionFactory's getInstance method can be used.
	 */
	private ConnectionFactory()
	{
		build = false;
	}
	
	/**
	 * This is the method to instantiate a ConnectionFactory, as long as one has not been created yet.
	 * @return cf - This is the ConnectionFactory.
	 */
	public static synchronized ConnectionFactory getInstance()
	{
		if(build == true)
		{
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
	
	/**
	 * This is the method to start a Connection with the database.
	 * @return conn - This is the database Connection.
	 */
	public Connection getConnection()
	{
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try
		{
			prop.load(new FileReader("D:/Users/Katelyn/Documents/GitHub/BatchRepository/Katelyn_Peterson_Code"
					+ "/Week2/Last_Chance_Bank_and_Diner/src/main/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("pwd"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
}