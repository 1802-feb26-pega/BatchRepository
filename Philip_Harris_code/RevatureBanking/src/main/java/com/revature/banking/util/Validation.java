package com.revature.banking.util;


import java.util.Scanner;
import com.revature.banking.dao.DaoImpl;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public class Validation {
	static Scanner scan = new Scanner(System.in);

	public static boolean access(Client c, Account a, String usrname, String pass) {

		DaoImpl.readCustomer(c, usrname, pass);
		if(c.getUsrName().equals(usrname) & c.getPassword().equals(pass)) {
			DaoImpl.readAccount(c,a);
			return true;
		}

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
				System.out.print("Please enter in a number that is valid");
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
	public static boolean checking_signup(int client_check, int account_check) {
		if(client_check  > 0 & account_check > 0) return true;		
		return false;		
	}
	public static boolean writeNewUser(Client c, Account a) {
		return DaoImpl.writeUser(c, a);

	}
	public static boolean check_update(int num) {
		if(num > 0) return true;
		return false;
	}
}
