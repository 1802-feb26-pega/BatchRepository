package com.revature.jdbcdemo.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudDriver {
	public static void main(String[] args) {
		System.out.println("Connecting to our database...");

		//Load & Register driver class.
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"demo","demo");
			System.out.println("Connected to the database.");

			stmt = conn.createStatement();

			//EXAMPLE: CREATING TABLES USING STATEMENT;
			String sql = "";
			//sql = "CREATE TABLE test(test_id NUMBER(6,0), test_name VARCHAR2(30), test_value NUMBER(6,2))";

			int value = stmt.executeUpdate(sql); //Vulnerable to SQL injection.

//			sql = "INSERT INTO test(test_id, test_name) VALUES(3, 'testTHREE')";
//			test(stmt.executeUpdate(sql));
//			sql = "UPDATE test SET test_value = 1.0 WHERE test_id = 2"; //WTF
//			test(stmt.executeUpdate(sql));

			sql = "SELECT * FROM test";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("test_id: " + rs.getInt(1));
				System.out.println("test_name: " + rs.getString(2));
				System.out.println("test_value: " + rs.getFloat(3));
				System.out.println("----------------------------");

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Exiting the database.");
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void test(int value) {
		if (value == 0) {
			System.out.println(value + " rows affected. Maybe this was a DDL statement?");
		} else {
			System.out.println(value + " rows affected.");
		}
	}
}
