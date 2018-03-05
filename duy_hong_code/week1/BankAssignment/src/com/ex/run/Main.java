package com.ex.run;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.ex.pojos.User;
import com.ex.io.UserIO;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//UserIO io = new UserIO();
		
		boolean again = true;
		
		while(again) {
			System.out.print("Welcome to Best Mock Bank. Please choose an option:\n"
					+ "1. Create an account with a unique email or username.\n"
					+ "2. Log in\n"
					+ "Please enter your choice (1 or 2): ");
				
			//Scanner sc = new Scanner(System.in);
			int number = sc.nextInt();
	
			switch(number) {
				case 1: createNewUser();
						break;
				case 2: logIn();
						break;
				default: System.out.println("You entered invalid number.");
			};
			
			System.out.println("Do you want to continue? (Y or N): ");
			String answer = sc.next();
			if(answer.toUpperCase().equals("Y"))
				again = true;
			else
				again = false;
		}
	}
	
	public static void createNewUser() {
		//Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter your email or username: ");
		String userName = sc.next();
		
		System.out.println("Please enter your first name: ");
		String firstName = sc.next();
		
		System.out.println("Please enter your last name: ");
		String lastName = sc.next();
		
		System.out.println("Please enter your password: ");
		String password = sc.next();

		System.out.println("Please deposit an amount: ");
		int balance = sc.nextInt();
		
		User newUser = new User(userName, firstName, lastName, password, balance);
		
		UserIO uio = new UserIO();
		uio.writeUser(newUser);
		//return;
	}
	
	public static void logIn() {
		//Scanner sc = new Scanner(System.in);
		User user = null;
		double bal = 0;
		
		System.out.print("Please enter your username or email: ");
		String username = sc.next();
		
		System.out.print("Please enter your passord: ");
		String password = sc.next();
		
		UserIO uio = new UserIO();
		HashMap<String, User> users = uio.readUsers();
		
		
		if(users.containsKey(username)) {
			// Get user object
			user = users.get(username);
			
			//Get password from the user object
			String pass = users.get(username).getPassword();
			
			if(password.equals(pass))
				System.out.println("You logged in successfully");
			else {
				System.out.println("Your password is wrong");
				return;
			}
		}
		
		boolean again = true;
		
		while(again) {
			System.out.print("You have four options:\n"
					+ "1. Deposit money\n"
					+ "2. Withdraw money\n"
					+ "3. View balance\n"
					+ "4. Log out\n"
					+ "Please enter your choice (1, 2, 3, or 4): ");
			
			//sc = new Scanner(System.in);
			int number = sc.nextInt();
			
			switch(number) {
				case 1: System.out.print("Please enter your amount: ");
						number = sc.nextInt();
						bal = user.getBalance();
						user.setBalance(bal + number);
						System.out.println("You deposited successfully. Now your balance is " + user.getBalance());
						break;
				case 2: System.out.print("Please enter your amount to withdraw: ");
						number = sc.nextInt();
						bal = user.getBalance();
						user.setBalance(bal - number);
						System.out.println("You withdrew successfully. Now your balance is " + user.getBalance());
						break;
				case 3: System.out.println("Your balance is " + user.getBalance());
						break;
				case 4: System.out.println("You logged out successfully");
						user = null;
						return;
				default: System.out.println("You entered invalid number.");
			};
			System.out.println("Do you want to continue? (Y or N): ");
			String answer = sc.next();
			if(answer.toUpperCase().equals("Y"))
				again = true;
			else
				again = false;
		}
	}

}
