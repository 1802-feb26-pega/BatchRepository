package com.ex.run;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.ex.pojos.User;
import com.ex.io.UserIO;

public class Main {
	public static void main(String[] args) {
		
		//UserIO io = new UserIO();
			
		System.out.println("Welcome to Best Mock Bank. Please choose an option:\n"
				+ "1. Create an account with a unique email or username.\n"
				+ "2. Log in\n"
				+ "Please enter your choice (1 or 2): ");
			
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();

		switch(number) {
		case 1: createNewUser();
		break;
		case 2: logIn();
		break;
		default: System.out.println("You entered invalid number.");
		};
	}
	
	public static void createNewUser() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter your email or username: ");
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
	}
	
	public static void logIn() {
		Scanner sc = new Scanner(System.in);
		User user = null;
		double bal = 0;
		
		System.out.println("Please enter your username or email: ");
		String username = sc.next();
		
		UserIO uio = new UserIO();
		HashMap<String, User> users = uio.readUsers();
		
		if(users.containsKey(username)) {
			System.out.println("You logged in successfully");
			user = users.get(username);
		}
		
		System.out.println("You have four options:\n"
				+ "1. Deposit money\n"
				+ "2. Withdraw money\n"
				+ "3. View balance\n"
				+ "4. Log out\n"
				+ "Please enter your choice (1, 2, 3, or 4): ");
		
		//sc = new Scanner(System.in);
		int number = sc.nextInt();
		
		switch(number) {
			case 1: System.out.println("Please enter your amount: ");
					number = sc.nextInt();
					bal = user.getBalance();
					user.setBalance(bal + number);
					System.out.println("You deposited successfully. Now your balance is " + user.getBalance());
					break;
			case 2: System.out.println("Please enter your amount to withdraw: ");
					number = sc.nextInt();
					bal = user.getBalance();
					user.setBalance(bal - number);
					System.out.println("You withdrew successfully. Now your balance is " + user.getBalance());
					break;
			case 3: System.out.println("Your balance is " + user.getBalance());
					break;
			case 4: System.out.println("You logged out successfully");
					user = null;
					break;
			default: System.out.println("You entered invalid number.");
		};
	}

}
