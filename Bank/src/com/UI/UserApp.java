package com.UI;

import java.util.Scanner;

import com.io.UserIO;
import com.pojo.UserObject;

/*
 * Handles all user interface the user will see when creating an account, logging in
 * and logging out.
 */
public class UserApp {

	public void userOptions() {
		
		System.out.println("To start, here are your options:");
		System.out.println("Press 1 to create a new account. Press 2 to log in "
				+ "with an existing account. Or press 0 to exit.\n");
		
		Scanner in = new Scanner(System.in);
		
		boolean loop = true;
		while (loop) {	
			
			int option = in.nextInt();
			in.nextLine();
			
			switch (option) {
			case 0:
				System.out.println("Please come again! Goodbye.");
				loop = false;
				break;
			case 1:
				createAccount();
				break;
			case 2:
				logIn();
				break;
			default:
				System.out.println("Invalid option! Press 1 to create account, 2 to "
						+ "log in, or 0 to exit.");
				break;
			}	
		}
	}
	
	public void createAccount() {
		
		System.out.println("Create an account");
		
		String username = "";
		String password = "";
		
		Scanner in = new Scanner(System.in);
		BankApp bankApp = new BankApp();
		
		while (true) {
			
			System.out.println("Please enter your username: ");
			username = in.nextLine();
			
			System.out.println("Now enter your password: ");
			password = in.nextLine();
			
			UserObject user = new UserObject(username, password);
			UserIO IO = new UserIO();
			
			// isValid is true if username exists, false if username does not exist
			boolean isValid = true;
			isValid = IO.verifyUser(user);
			
			if (!isValid) {
				System.out.println("Username: " + username + ". Password: " + password);
				IO.insert(user);
				
				// Send new user to their bank account
				bankApp.bankAccount(user);
				break;
			} else {
				System.out.println("Error! Username is already taken. Create a new username.");
			}
		}
		
	}
	
	public void logIn() {
		
		System.out.println("Enter your username and password to log in.");
		String username;
		String password;
		
		Scanner in = new Scanner(System.in);
		BankApp bankApp = new BankApp();
				
		boolean isValid = false;
		while(!isValid) {
		
			System.out.print("Username: ");
			username = in.nextLine();
			System.out.print("\nPassword: ");
			password = in.nextLine();
			
			UserObject user = new UserObject(username, password);
			UserIO IO = new UserIO();
			
			isValid = IO.verifyUser(user);
			if (isValid) {
				UserObject userO = IO.retrieve(user);
				bankApp.bankAccount(userO);
			} else {
				System.out.println("Error! Username or password is incorrect.");
			}
		}
	}
	
//	public void logOut() {
//		System.out.println("Thank you for using the service");
//	}
	
}
