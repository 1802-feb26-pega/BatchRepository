package com.revature.bankapp.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class test {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		Properties prop = new Properties();
		String sql;
		int value;
		
		try {
			prop.load(new FileReader("C:/Users/Marvin/Documents/github/BankingApplicationWeek2/BankApp/src/main/resources/application.properties"));
			Class.forName(prop.getProperty("driver"));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
