package com.revature.jbdcdemo.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Callable {

	public static void main(String[] args)
	{
		Properties prop = new Properties();
		String sql;
		int value;
		CallableStatement cstmt;
		
		try
		{
			prop.load(new FileReader("D:/Users/Katelyn/Documents/GitHub/BatchRepository/Katelyn_Peterson_Code/"
						+ "Week2/JDBC Demo/src/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
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
			// Example: Calling a stored procedure
			// Notice that our SQL string looks similar to a PreparedStatement except we surround the entire
			// statement with curly brackets
			sql = "{CALL insertTest(?, ?, ?)}";
			
			cstmt = conn.prepareCall(sql);
			
			// Binding values = same syntax as PreparedStatement
			cstmt.setInt(1, 3);
			cstmt.setString(2, "TestThree");
			cstmt.setDouble(3,  456.78);
			
			value = cstmt.executeUpdate();
			PreparedDriver.helper(value);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
