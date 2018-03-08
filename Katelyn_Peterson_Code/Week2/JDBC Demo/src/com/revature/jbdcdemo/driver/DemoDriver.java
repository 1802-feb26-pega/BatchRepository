package com.revature.jbdcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoDriver
{

	public static void main(String[] args)
	{
		// 1) Simple sysout
		System.out.println("Connecting to our database...");
		
		try
		{
			// 2) Obtain the proper driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"Tin", "Tin");
			
			conn.close();
			
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Connected to the database! Yay!");
	}

}