package com.project.UI;

import java.util.Scanner;

import com.project.Logic.User;

public class ApplicationUI {
	private Scanner sc;
	public ApplicationUI() {
		sc = new Scanner(System.in);
	}
	
	/**
	 * Prompts the user to register for an account. Returns a String array with the following format:
	 *     [0] = User's First name. (I.E. "Joe")
	 *     [1] = User's middle initial (I.E. "J")
	 *     [2] = User's last name ("Smith")
	 *     [3] = User's email address.
	 *     [4] = User's chosen username.
	 *     [5] = User's password.
	 *     
	 * If any input is invalid, or the user chooses to exit, this returns null instead.
	 * @return String array of user input (length 5), or null.
	 */
	public String[] register() {
		System.out.println("Welcome to Willbank! We are glad you chose to bank with us.");
		System.out.println("Would you like to register a new account? (Y/N)");
		if (sc.nextLine().equalsIgnoreCase("n")) {
			System.out.println("You will be taken back to the login screen.");
			return null;
		}
		do {
			System.out.print("Please enter your first or personal name:");
			String first = sc.nextLine();
			System.out.println("Please enter your middle name. If you do not have a middle name, leave it blank.");
			System.out.print("Middle name: ");
			String middle = sc.nextLine();
			if (!middle.equals("")) {
				middle = middle.substring(0, 1);
			}
			System.out.print("Please enter your last or family name:");
			String last = sc.nextLine();
			System.out.print("Enter your email address: ");
			String email = sc.nextLine();
			System.out.print("Enter a username for your account:");
			String un = sc.nextLine();
			System.out.print("Enter a password for your account:");
			String pw = sc.nextLine();
			
			System.out.println("\n" + first + " " + middle + ((middle.length() == 1) ? ". " : " ") + last);
			System.out.println(email);
			System.out.println("username = " + un + ", password = " + pw);
			System.out.println("Does this look correct? (Y/N)");
			if (sc.nextLine().equalsIgnoreCase("y")) {
				String info[] = {first, middle, last, email, un, pw};
				return info;
			}
		} while (true);
	}

	/**
	 * Prompts the user to log in via the UI, asking for username & password.
	 * @return A String[] of length 2 containing the username and password, respectively.
	 */
	public String[] userLogIn() {
		String[] creds = new String[2];
		boolean unCorrect = false;
		System.out.print("Please enter your username or email address: ");
		creds[0] = sc.nextLine();
		System.out.print("Please enter your password: ");
		creds[1] = sc.nextLine();
		
		return creds;
	}
	
	/**
	 * Prompts a returning user from a previous session to log in.
	 * Returns none if the user does not choose to log into the previous account.
	 * 
	 * @param user The reference to the user.
	 * @return the password of the user, or null if they choose to log into 
	 * a different account.
	 */
	public String returningUserLogIn(User user, boolean again) {
		String response = "";
		if (!again) {
			System.out.println("Welcome back to Willbank. Are you " + user.getWholeName() + "? (Y/N)");
			response = sc.nextLine();
		}
		if (again || response.equalsIgnoreCase("y")) {
			System.out.print((again ? "" : "Hello, " + user.getFirstName() + ". ") + "Please re-enter your password: ");
			String pass = sc.nextLine();
			return pass;
		}
		return null;
	}
	
	/**
	 * Prompts the user via the UI if they want to log out.
	 * @param user The reference to the current user.
	 * @return True if the user wants to log out, or false if they want to stay logged in.
	 */
	public boolean userLogOut(User user) {
		System.out.println("Do you want to log out? (Y/N)");
		String temp = sc.nextLine();
		boolean log = temp.equalsIgnoreCase("y");
		if (log) {
			System.out.println("You have been sucessfully logged out.\n");
		} else {
			System.out.println("You have not been logged out, " + user.getFirstName() + ".\n");
		}
		return log;
	}
	
	/**
	 * Prompts the user to deposit money into their account.
	 * @param user The reference to the current user.
	 * @param currentBalance The current money balance in their account.
	 * @return The amount the user deposits, or -1 if the input is invalid.
	 */
	public double depositMoney(User user) {
		System.out.println("How much would you like to deposit?");
		System.out.print("$");
		double dep = -1;
		while (dep == -1) {
			try {
				String temp = sc.nextLine();
				if (temp.equals("")) return -1;
				else dep = Double.parseDouble(temp);
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid dollar amount.");
			}
		}
		return dep;
	}

	/**
	 * Prompts the user to withdraw money from their account.
	 * @param user The reference to the current user.
	 * @return The amount they wish to withdraw, regardless of their actual balance. Returns -1 if the input is invalid.
	 */
	public double withdrawMoney(User user) {
		viewBalance(user);
		System.out.println("Would you like to withdraw? (Y/N)");
		if (sc.nextLine().equalsIgnoreCase("y")) {
			System.out.println("How much would you like to withdraw?");
			System.out.print("Withdraw $");
			while (true) {
				try {
					return Double.parseDouble(sc.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Please input a valid dollar amount.");
					System.out.println("You have $" + user.getBalance() + ".");
					System.out.print("Withdraw $");
				}
			}
			
		} else {
			System.out.println("Withdrawl cancled.");
			return -1;
		}
	}
	
	/**
	 * Prints out a formatted string of the user's name and their current balance.
	 * @param user
	 */
	public void viewBalance(User user) {
		String out = String.format("\n%1$s,\nYour current balance is: $%2$.2f.\n", user.getWholeName(), user.getBalance());
		System.out.println(out);
	}
}
