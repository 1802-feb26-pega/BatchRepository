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
		PreparedStatement pstmt;
		
		try
		{
			prop.load(new FileReader("D:/Users/Katelyn/Documents/GitHub/BatchRepository/Katelyn_Peterson_Code/"
					+ "Week2/JDBC Demo/src/resources/application.properties"));
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
			pstmt = conn.prepareStatement(sql);
			
			// Here we are going to bind the values of our parameters inside of our parameterized query
			pstmt.setInt(1, 1);
			pstmt.setString(2,  "TestOne");
			pstmt.setDouble(3, 42.0);
			
			// Execute as usual
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			// 2
			pstmt.setInt(1, 2);
			pstmt.setString(2,  "TestTwo");
			pstmt.setDouble(3, 99.99);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			// 3
			pstmt.setInt(1, 3);
			pstmt.setString(2,  "TestThree");
			pstmt.setDouble(3, 54.55);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			// 4
			pstmt.setInt(1, 4);
			pstmt.setString(2,  "TestFour");
			pstmt.setDouble(3, 4875.1);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			// Example: Updating Records using PreparedStatement
			sql = "UPDATE test SET test_value = ? WHERE test_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, 5000);
			pstmt.setInt(2, 3);
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			// Example: Deleting records using PreparedStatement
			sql = "DELETE FROM test WHERE test_value > ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDouble(1, 1000.00);
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
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
