package com.bank.applications;

import java.util.Scanner;

@SuppressWarnings("unused")
public class Teller {
	//TODO: ask whether username and email are exclusive
	
	private String username=null;
	private String firstName;
	private String lastName;
	private String password=null;
	
	// create an account with a unique email or username
	public void createAccount() {
		//TODO: ask for help with the writing to a file
		//TODO: Write username, firstName, lastName, password
		//TODO: Have runBank class do all console printing
	}
	
	// log in 
	public void logIn() {
		Scanner scan = new Scanner(System.in);
		
		scan.close();
	}
	
	//log out
	public void logOut() {
		Scanner scan = new Scanner(System.in);
		String answer = "";
		
		System.out.println("Are you sure you want to log out?");
		System.out.print("Type 'y' or 'n': ");
		while (answer=="") {
			answer = scan.nextLine();
			if (answer == "y") {
				username=null;
				password=null;
			}
			else if(answer == "n"){
				System.out.println("Returning to menu...");
				System.out.print("\n");
			} else {
				scan.nextLine();
				System.out.print("That was not a valid entry. Please type 'y' or 'n':");

			} 
		}
		scan.close();
	}
	
	//deposit money
	public void deposit() {
	}
	
	//withdraw money
	public boolean withdraw() {
		return false;
	}
	
	//view balance
	public float getBalance() {
		return 0;
	}

	public static void main(String[] args) {
		//TODO: ask whether multi-threading is necessary, or at least useful
		
	}

}
