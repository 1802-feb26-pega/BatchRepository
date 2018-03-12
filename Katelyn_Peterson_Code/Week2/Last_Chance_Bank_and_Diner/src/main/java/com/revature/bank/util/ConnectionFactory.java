package com.revature.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Singletons are characterized by a private constructor and a public getter 
// that gets us an instance (getter must be static)

public class ConnectionFactory
{
	private static ConnectionFactory cf = null;
	private static Boolean build = true;
	
	private ConnectionFactory()
	{
		build = false;
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		if(build == true)
		{
			cf = new ConnectionFactory();
		}
		
		return cf;
	}
	
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
