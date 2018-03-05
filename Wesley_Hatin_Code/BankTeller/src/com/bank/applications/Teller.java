package com.bank.applications;

import java.util.Scanner;

public class Teller {
	
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	
	// create an account with a unique email or username
	public void createAccount() {
		//TODO: ask for help with the writing to a file
		//TODO: Write username, firstName, lastName, password
		//TODO: Have runBank class do all console printing
	}
	
	// log in 
	public void logIn() {
		Scanner scan = new Scanner(System.in);
		
		while(password=="") {
			System.out.println("Enter your username: ");
			this.setUsername(scan.nextLine());
			System.out.println("");
			
			System.out.println("Enter your password: ");
			this.setPassword(scan.nextLine());
			System.out.println("");
			
			
		}
		
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
				setUsername(null);
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return (firstName+lastName);
	}

	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}


}
