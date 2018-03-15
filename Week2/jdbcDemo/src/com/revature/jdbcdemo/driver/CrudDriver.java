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
		String sql;
		int value;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo", "demo");
			
			stmt = conn.createStatement();
			
			// EXAMPLE: CREATING TABLES USING STATEMENT
			sql = "CREATE TABLE test(test_id NUMBER(6,0), test_name VARCHAR2(30), " + 
					"test_value NUMBER(6, 2))";
			
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			// EXAMPLE: INSERTING RECORDS USING STATEMENT
			sql = "INSERT INTO test VALUES (1, 'testOne', 42.0)";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			sql = "INSERT INTO test VALUES (2, 'testTwo', 19.55)";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			sql = "INSERT INTO test VALUES (3, 'testThree', 561.0)";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			sql = "INSERT INTO test VALUES (4, 'testFour', 13.1)";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			sql = "INSERT INTO test(test_id, test_name) VALUES (5, 'testFive')";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			//------------------------------------------------------------------------------------------
			
			// EXAMPLE: UPDATING RECORDS WITH STATEMENT
			// Simple update example
			sql = "UPDATE test SET test_value = 524.25 WHERE test_id = 5";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			// Update multiple records
			sql = "UPDATE test SET test_value = 0 WHERE test_value > 500";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			//------------------------------------------------------------------------------------------
			
			// EXAMPLE: DELETING RECORDS USING STATEMENT
			// Simple delete example
			sql = "DELETE FROM test WHERE test_id = 5";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			// Truncate table
			sql = "TRUNCATE TABLE test";
			value = stmt.executeUpdate(sql);
			
			if(value == 0) {
				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
			} else {
				System.out.println(value + " rows affected!");
			}
			
			//---------------------------------------------------------------------------
			
			sql = "SELECT * FROM employee";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("employee id: " + rs.getInt(1));
				System.out.println("employee hire date: " + rs.getTimestamp(7));
				System.out.println("-----------------------------------------");
			}
			
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
