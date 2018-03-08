package com.ex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDDriver {
	public static void test(String... args) {
		try{  
			//step1 load the driver class  
			//Obtain the proper DriverClass
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			System.out.println("Attempting to connect");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","demo","jerome"); 
			
			//Alternate way for connection is DriverManger.getConnection(jdbc:oracle:thin:@localhost:1521:xe","demo","jerome)
			
			System.out.println("Connected");
			  
			System.out.println("Attempting to create the statement");
			//step3 create the statement object  
			Statement stmt=con.createStatement(); 
			
			System.out.println("Statment created");
			  
			System.out.println("Attempting to get records");
			//step4 execute query  
			ResultSet rs=stmt.executeQuery("select * from employees");
			System.out.println("Records were received");
			
			System.out.println("Printing records....");
			int idx = 1;
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
			con.close();

			  
			}catch(ClassNotFoundException e){ System.out.println(e);}  
			 catch(SQLException e){ System.out.println(e);}
			 finally {
					//step5 close the connection object  
					
					System.out.println("Connection is now closed....");
			 }
	}
	
	
	

}
