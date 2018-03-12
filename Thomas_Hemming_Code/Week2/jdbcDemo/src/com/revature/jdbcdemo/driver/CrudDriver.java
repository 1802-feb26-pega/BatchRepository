package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
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
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "demo", "demo");
			
			stmt = conn.createStatement();
			
//			//EXAMPLE: CREATING TABLES USING STATEMENT
//			String drop = "DROP TABLE test";
//			int value1 = stmt.executeUpdate(drop);
//			
//			String sql = "CREATE TABLE test(test_id NUMBER(6,0), test_name VARCHAR2(30), test_value NUMBER(6,2))";
//			//mess up syntax get SQLSyntaxErrorException
//			int value = stmt.executeUpdate(sql);
//			
//			if (value == 0) {
//				System.out.println(value + " rows affected. Maybe this statement was a DDL statement?");
//			} else {
//				System.out.println(value + " rows affected!");
//			}
			
			//EXAMPLE: INSERTING RECORDS USING STATEMENT
			sql = "INSERT INTO test VALUES (1, 'testOne', 42.0)";
			value = stmt.executeUpdate(sql);
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}
}
