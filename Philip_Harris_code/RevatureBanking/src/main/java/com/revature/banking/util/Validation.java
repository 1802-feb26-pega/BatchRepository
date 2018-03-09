package com.revature.banking.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.banking.dao.Client_Dao;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public class Validation {
	static Scanner scan = new Scanner(System.in);

	public static boolean access(Client c,Account a,String usrname, String pass) {
		//Implement multiple login in attempts
		if(Client_Dao.readCustomer(c,a,usrname, pass)) return true;
		return false;
	}
	public static int validateNumSSN(String n) {	
		while(true) {
			if(n.matches(("[0-9]+")) == false | (n.length() !=8) ){
				System.out.print("Please enter in a number that contains 8 digits: ");
				n = scan.next();
				System.out.println();
			}
			else break;
		}

		int v = Integer.parseInt(n);

		return v;
	}
	public static int validateNum(String n) {		

		while(true) {
			if(n.matches(("[0-9]+")) == false){
				System.out.print("Please enter in a number that contains 8 digits: ");
				n = scan.next();
				System.out.println();
			}
			else break;
		}

		int v = Integer.parseInt(n);

		return v;
	}
	public static String validateNames(String name) {

		while(true) {
			if(name.contains(" ")) {
				break;
			}

		}

		return name;
	}
	public static String encode(String string) {
		// TODO Write an implementation for this method declaration

		String msg = "";
		string = string.toLowerCase();
		for(int i = 0; i < string.length(); i++) {
			int ascii = (int) string.charAt(i);				
			ascii = ascii - 97;
			ascii = 122 - ascii;
			msg += (char) ascii;
		}

		return msg;
	}
	public static String decode(String string) {
		// TODO Write an implementation for this method declaration
		string = string.toLowerCase();
		String msg = "";
		for(int i = 0; i < string.length(); i++) {
			int ascii = (int) string.charAt(i);				
			ascii = 122 - ascii;
			ascii += 97;
			msg += (char) ascii;
		}
		return msg;
	}

	public static int eROT5(String ssn) {
		// TODO Write an implementation for this method declaration

		String msg = "";
		for(int i = 0; i < ssn.length(); i++) {
			int ascii = (int) ssn.charAt(i);	
			if((ascii + 5) > 57) {// if the initial ascii code is above 57
				ascii += 48;
				ascii -= 53;
			}else { // if the the initial ascii code is below 57
				ascii -= 48;
				ascii += 53;
			}
			msg += (char) ascii;
		}

		return Integer.parseInt(msg);
	}
	public static int dROT5(String ssn) {
		// TODO Write an implementation for this method declaration
		String msg = "";
		for(int i = 0; i < ssn.length(); i++) {
			int ascii = (int) ssn.charAt(i);	
			if((ascii+5) > 57) {// if the initial ascii code is above 57
				ascii -= 53;
				ascii += 48;
			}else {// if the the initial ascci code is below 57
				ascii -= 48;
				ascii += 53;
			}
			msg += (char) ascii;
		}
		if(msg.matches(("[0-9]+")) == false) msg = msg.substring(0, msg.length() - 1);
		return Integer.parseInt(msg);

	}
	public static boolean checking_signup(Client c, Account a) {
		int client_check = Client.write(c);
		int account_check = Account.writeA(a,c);

		if(client_check  > 0 & account_check > 0) {
			try(Connection conn = ConnectionFactory.getInstance().getConnection()){
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
		
	}
}
