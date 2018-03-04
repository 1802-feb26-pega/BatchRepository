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
			Scanner s = new Scanner(System.in);
			try{
				n = s.nextInt();
				if(n < 0){
					System.out.print("Please enter in a positive amount: ");
				}
				else break;					 			
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

}
