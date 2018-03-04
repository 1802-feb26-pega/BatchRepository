package com.revature.projectI;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {

	static DataPersistency db = new DataPersistency();
	static Scanner scan = new Scanner(System.in);

	public boolean access(Client c,Account a,String usrname, String pass) {
		//Implement multiple login in attempts
		if(db.readCustomer(c,a,usrname, pass)) return true;
		else {
			System.out.println("Access denied");
			System.out.println("Password and/or Username was incorrect");
			System.out.println();
		}
		return false;
	}
	public String validateNumSSN(String n) {					
		while(true) {
			if(n.matches(("[0-9]+")) == false | (n.length() !=8) ){
				System.out.print("Please enter in a number that contains 8 digits: ");
				n = scan.next();
				System.out.println();
			}
			else break;
		}
		return n;
	}
	public int validateNum() {		

		int n = 0; 

		while(true) {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			try{
				n = s.nextInt();
				if(n < 0){
					System.out.print("Please enter in a positive amount: ");
				}
				else {
					break;
				}
					 			
			}catch(NumberFormatException | InputMismatchException ex) {
				System.out.print("Please enter in a number: ");
				System.out.println();
			}
		}	
		
		return n;
	}
	public String validateNames(String name) {

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
}
