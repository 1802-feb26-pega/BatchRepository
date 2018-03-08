package com.ex.jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDriver {
	
	public static void main(String[] args) {
		//Connection conn = null;
		//Statement stmt = null;
		String sql;
		int value;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("C:/Users/phili/my_git_repos/BatchRepository/Philip_Harris_code/JavaEx/src/Resources/Application.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				prop.getProperty("url")
				,prop.getProperty("user")
				,prop.getProperty("password"))){
			
			
			
			//stmt=conn.createStatement(); 
			
//			String sql = "CREATE TABLE TEST(test_id NUMBER(6,0),  test_name VARCHAR2(30), test_value NUMBER(6,2))";
//			int value = stmt.executeUpdate(sql);
//			
//			if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
//			else {System.out.println(value + " rows affected");}

			
			//INSERTING RECORDS USING STATEMENTS
//			sql = "INSERT INTO test VALUES(1,'testOne', 513.0)";
//			value = stmt.executeUpdate(sql);
//		
//			
//			if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
//			else {System.out.println(value + " rows affected");}		
//			
//			sql = "INSERT INTO test VALUES(1,'testOne', 83.0)";
//			value = stmt.executeUpdate(sql);
//			if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
//			else {System.out.println(value + " rows affected");}
//			
//			sql = "INSERT INTO test VALUES(1,'testOne', 5198423549815.0)";
//			value = stmt.executeUpdate(sql);
//			
//			if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
//			else {System.out.println(value + " rows affected");}	
			
//			sql = "INSERT INTO test(test_id,test_name) VALUES (5, 'TestFive')";
//			value = stmt.executeUpdate(sql);
			
			//EXAMPLE UPDATING RECORDS WITH STATEMENT
			//SIMPLE VERSION
//			sql = "UPDATE test SET test_value = 524.25 WHERE test_id = 5";
//			value = stmt.executeUpdate(sql);
			
			//UPDATE MULTIPLE RECORDS
//			sql = "UPDATE test SET test_value = 0 WHERE test_value  > 500";
//			value = stmt.executeUpdate(sql);
//		
			
			//EXAMPLE: DELETING RECORDS USING STATEMENT
			//SIMPLE
//			sql = "DELETE FROM test WHERE test_id = 5";
//			value = stmt.executeUpdate(sql);
			
			//sql = "TRUNCATE TABLE test";
			//value = stmt.executeUpdate(sql);
			
			//if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
			//else {System.out.println(value + " rows affected");}
			
//			sql = "SELECT * FROM test";
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				System.out.println("Test_id: " + rs.getInt(1) +  " Test_name: " + rs.getInt(2) + "Test_value: " + rs.getInt(3));
//			}
			
			
//			sql = "SELECT * FROM employees";
//			ResultSet rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				System.out.println("Test_id: " + rs.getInt(1) +  " Test_name: " + rs.getInt(2) + "Test_value: " + rs.getInt(3));
//			}
			
			//EXAMPLES: PREPARED STATEMENTS using INSERT
			
			sql = "INSERT INTO Test VALUES (?,?,?)";
//			//Similar syntax, except we use the COnnection method 'prepareStatement'
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			
//			//Here we are going to ind the values of our parameters inside of our 
//			//parameterized query
//			pstmt.setInt(1, 10);
//			pstmt.setString(2, "test10");
//			pstmt.setDouble(3, 42.0);
//			
//			//EXECUTE
//			value = pstmt.executeUpdate();
//			helper(value);
//			//-----------------------------------------------------------------------------		
//			pstmt.setInt(1, 11);
//			pstmt.setString(2, "test11");
//			pstmt.setDouble(3, 99.99);
//			
//			//EXECUTE
//			value = pstmt.executeUpdate();
//			helper(value);
//			
//			//-----------------------------------------------------------------------------
//			
//			pstmt.setInt(1, 12);
//			pstmt.setString(2, "test12");
//			pstmt.setDouble(3, 54.55);
//			
//			//EXECUTE
//			value = pstmt.executeUpdate();
//			helper(value);
//			
//			//-----------------------------------------------------------------------------
//			
//			pstmt.setInt(1, 13);
//			pstmt.setString(2, "test13");
//			pstmt.setDouble(3, 4875.1);
//			
//			//EXECUTE
//			value = pstmt.executeUpdate();
//			helper(value);
			
			//-----------------------------------------------------------------------------
			
			//EXAMPLE: UPDATING RECORD USING PREPAREDSTATEMENTS
//			sql = "UPDATE test SET test_value = ? WHERE test_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setDouble(1,1);
//			pstmt.setInt(2, 3);
//			value = pstmt.executeUpdate();
//			helper(value);
//			
//			sql = "DELETE FROM test WHERE test_id = ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1,12);
//			value = pstmt.executeUpdate();
//			helper(value);
			
			//PLAY CODE
//			sql = "DELETE FROM test WHERE test_value ? < ?";
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, "1");
//			pstmt.setInt(2,10);
//			value = pstmt.executeUpdate();
//			helper(value);
			
			
			
				
			
			
		}catch(SQLException e){ System.out.println(e);
		 e.printStackTrace();}
	}
	public static void helper(int value) {
		if(value==0) System.out.println(value + " Rows affected. Maybe this satement was a DDL statement?");
		else {System.out.println(value + " rows affected");}
	}
}
