package com.revature.jdbc;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedDriver {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		String sql;
		int value;
		
		try {
			
			prop.load(new FileReader("C:\\Users\\user\\eclipse-workspace\\FullStack\\application.properties"));
			Class.forName(prop.getProperty("driver"));
			
		} catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url"), 
				prop.getProperty("usr"), 
				prop.getProperty("pwd"))) {
			
			// EXAMPLE: INSERT INTO TABLE USING PREPAREDSTATEMENT
			sql = "INSERT INTO Users VALUES (?, ?, ?, ?)";
			
			// Similar syntax, except we use the Connection method 'prepareStatement'
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// Here we are going to bind the values of our parameters inside of our
			// parameterized query!
		/*	pstmt.setInt(1, 1234);
			pstmt.setString(2, "Joe Smith");
			pstmt.setString(3, "Saving");
			pstmt.setDouble(4, 4196703344.0);
			
			// Execute as usual
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
				
			//---------------------------------------------------------------------------
		/*	
			// EXAMPLE: UPDATING RECORDS USING PREPAREDSTATEMENT
			//A simple update
			sql = "UPDATE Users SET Pass = ? WHERE Name = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, 1734.0);
			pstmt.setString(2, "Josh");
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
		*/	
			//-----------------------------------------------------------------
			
			// EXAMPLE: DELETING RECORDS USING PREPAREDSTATEMENT
			// A simple delete
			sql = "DELETE FROM Users WHERE Name = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "Josh");
			
			value = pstmt.executeUpdate();
			PreparedDriver.helper(value);
			
			//-----------------------------------------------------------------
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
			

		
	

		
	public static void helper(int value) {
		if(value == 0) {
			System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
		} else {
			System.out.println(value + " rows affected!");
		}
	}
}