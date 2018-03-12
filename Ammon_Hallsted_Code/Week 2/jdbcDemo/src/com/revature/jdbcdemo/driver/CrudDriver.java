package com.revature.jdbcdemo.driver;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrudDriver {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		int value = 0;
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","demo","demo");
				
				stmt = conn.createStatement();
				
				//Create tables using statement				

//				sql = "INSERT INTO test VALUES (1, 'testtwo',20000)";
//				
//				value = stmt.executeUpdate(sql);
//				
//				if (value == 0) {
//					System.out.println(value + " rows affected. maybe this statement was a DDL statement?");
//				} else {
//					System.out.println(value + "rows affected.");
//				}
				
				//---------------------------------------------------
				
				//Example: Updating Records with STATEMENT
				// Simple example:
//				sql = "UPDATE test SET test_value = 524.25 WHERE test_id = 5";
//				
//				value = stmt.executeUpdate(sql);
//				
//				if (value == 0) {
//					System.out.println(value + " rows affected. maybe this statement was a DDL statement?");
//				} else {
//					System.out.println(value + "rows affected.");
//				}
				
				sql = "DELETE test SET test_value = 524.25 WHERE test_id = 5";
				
				value = stmt.executeUpdate(sql);
				
				if (value == 0) {
					System.out.println(value + " rows affected. maybe this statement was a DDL statement?");
				} else {
					System.out.println(value + "rows affected.");
				}
				
				
				
				conn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		
	}
}
