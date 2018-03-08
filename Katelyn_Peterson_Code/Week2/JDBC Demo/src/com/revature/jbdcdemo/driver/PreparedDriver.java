package com.revature.jbdcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedDriver
{
	public static void main(String[] args)
	{
		Properties prop = new Properties();
		String sql;
		int value;
		
		try
		{
			prop.load(new FileReader("D:/Users/Katelyn/Documents/"
					+ "GitHub/BatchRepository/Katelyn_Peterson_Code/Week2/JDBC Demo/src/resources"));
			Class.forName(prop.getProperty("driver"));
		}
		catch(FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"),
				prop.getProperty("pwd")))
		{
			
			// Example: Insert into table with prepared statements
			sql = "INSERT INTO test VALUES (?, ?, ?)";
			
			// Similar syntax, except we use the Connection function 'prepareStatement'
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// Here we are going to bind the values of our parameters inside of our parameterized query
			pstmt.setInt(1, 1);
			pstmt.setString(2,  "TestOne");
			pstmt.setDouble(3, 42.0);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void helper(int value)
	{
		if(value == 0)
		{
			System.out.println(value + " rows affected.  Maybe this statement was a DDL statement?");
		}
		else
		{
			System.out.println(value + " rows affected");
		}
	}
}
