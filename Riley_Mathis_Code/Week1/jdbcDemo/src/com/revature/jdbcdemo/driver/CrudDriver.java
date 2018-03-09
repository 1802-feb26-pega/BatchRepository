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
		String sql = "";
		int value = 0;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo", "demo");
			
			stmt = conn.createStatement();
			
//			//EXAMPLE: creating tables using statement
//			String sql = "CREATE TABLE test(test_id NUMBER(6,0), test_name VARCHAR(30), test_value NUMBER(6,2))";
//			
//			int value = stmt.executeUpdate(sql);
//			
//			if(value==0) {
//				System.out.println("0 rows affected. Maybe ddl statement");
//			}
//			else {
//				System.out.println(value + " rows affected");
//			}
			
			//EXAMPLE: inserting records using statement
			sql = "INSERT INTO test(test_id, test_name) VALUES (2, 'testOne')";
			value = stmt.executeUpdate(sql);
			
			if(value==0) {
				System.out.println(value + " rows affected. Maybe ddl statement");
			}
			else {
				System.out.println(value + " rows affected");
			}
			
			//EXAMPLE: updating records with a statement
			sql = "UPDATE test SET test_value = 524.25 WHERE test_id = 5";
			value = stmt.executeUpdate(sql);
			
			if(value==0) {
				System.out.println(value + " rows affected. Maybe ddl statement");
			}
			else {
				System.out.println(value + " rows affected");
			}
			
			//update multiple records
			sql = "UPDATE  test SET test_value = 0 WHERE test_value > 500";
			value = stmt.executeUpdate(sql);
			
//			//Delete records using statement
//			sql = "DELETE FROM test WHERE test_id = 5";
//			value = stmt.executeUpdate(sql);
//			
//			//Truncate table
//			sql = "TRUNCATE TABLE test";
//			value = stmt.executeUpdate(sql);
//			//value just for showing something happens in console
//			
			
			//Using a result set
			sql = "SELECT * FROM test";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("test id: " + rs.getInt(1));
				System.out.println("test name: " + rs.getString("test_name"));
				System.out.println("test value: " + rs.getDouble(3));
				System.out.println("--------------------------------------");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
