package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudDriver {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		int value = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo", "demo");
			
			stmt = conn.createStatement();
			
			//EXAMPLE: CREATING TABLES USING STATEMENTS
//			sql = "CREATE TABLE test(test_id NUMBER(6,0), test_name VARCHAR2(30), test_value NUMBER(6,2))";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			// EXAMPLE: INSERTING RECORDS USING STATEMENT
//			sql = "INSERT INTO test VALUES (1, 'testOne', 42.0)";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
//			
//			sql = "INSERT INTO test VALUES (2, 'testTwo', 19.55)";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
//			sql = "INSERT INTO test VALUES (3, 'testThree', 561.0)";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
//			sql = "INSERT INTO test VALUES (4, 'testFour', 452.0)";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
//			
//			sql = "INSERT INTO test(test_id, test_name) VALUES (5, 'testFive')";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			// EXAMPLE: UPDATING RECORDS WITH STATEMENT
			// Simple update example
//			sql = "UPDATE test SET test_value = 524.25 WHERE test_id = 5";
//			
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			//Update multiple records
//			sql = "UPDATE test SET test_value = 0 WHERE test_value > 500";
//			value = stmt.executeUpdate(sql);
//			
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			//EXAMPLE: DELETING RECORDS USING STATEMENT
			//simple delete example
//			sql ="DELETE FROM test WHERE test_id = 5";
//			value = stmt.executeUpdate(sql);
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			// Truncate table
//			sql = "TRUNCATE TABLE test";
//			value = stmt.executeUpdate(sql);
//			if(value == 0 ) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement.");
//				
//			} 
//			else {
//				System.out.println(value + " rows affected.");
//			}
			
			//---------------------------------------------------------------------------------------------
			
//			sql = "SELECT * FROM test";
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				System.out.println("test id: " + rs.getInt(1));
//				System.out.println("test name: " + rs.getString("test_name"));
//				System.out.println("test value: " + rs.getDouble("test_value"));
//			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
			
	}
}
