package com.project.Logic;

import java.util.Scanner;

import com.project.Data.Archiver;
import com.project.Data.Serializer;
import com.project.UI.ApplicationUI;

public class ApplicationLogic {
	
	private static AppMemory app;
	private static User user;
	
	public static void main(String[] args) {
		ApplicationUI aui = new ApplicationUI();
		app = new AppMemory();
		Scanner sc = new Scanner(System.in);
		load();
		
		//check for serialized user from previous session.
		if (user != null) {
			//if user is found, prompt for password.
			//if password is correct, log in. If password is not, prompt error.
			boolean good = false;
			boolean again = false;
			String password = null;
			while (!good) {
				password = aui.returningUserLogIn(user, again);
				if (password != null && user.getPassword().equals(password)) { // If password is correct...
					//Print login success notification.
					System.out.println("Success! Welcome back to Willbank, " + user.getWholeName() + ".\n");
					good = true;
				} else if (password != null && !user.getPassword().equals(password)) { // If password is wrong...
					System.out.println("Incorrect password. Please try logging in again.");
					again = true;
				} else if (password == null) { //If password is null, ignore recovered user account.
					user = null;
					good = true;
				}
			}
		}
		
		//If no previous session, log in/register.
		if (user == null) {
			//Print generic welcome statement.
			//Ask if user wants to register or log in.
			boolean logged = false;
			while (!logged) {
				System.out.println("Welcome to Willbank, valued customer. Do you wish to log in or register?");
				System.out.println("Please choose an option:");
				System.out.println("Press 1 to log in.");
				System.out.println("Press 2 to register as a new customer.");
				int choice = 0;
				try {
					choice = Integer.parseInt(sc.nextLine());
					if (choice < 1 || choice > 2) {
						System.out.println("Please enter either 1 or 2.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter either 1 or 2.");
				}
				
				if (choice == 1) { //Log in
					String[] credentials = aui.userLogIn();
					for (User u: app.getUserList()) {
						if (logged) break;
						boolean umatch = u.getUsername().equals(credentials[0]);
						boolean pmatch = u.getPassword().equals(credentials[1]);
						if (umatch && pmatch) {
							user = u;
							System.out.println("\nWelcome to Willbank, " + user.getWholeName() + ".");
							logged = true;
							break;
						} else if (umatch && !pmatch) {
							System.out.println("Incorrect password.");
							do {
								System.out.println("Please re-enter your password.");
								String pass = sc.nextLine();
								pmatch = u.getPassword().equals(pass);
								if (!pmatch) {
									System.out.println("Incorrect password.");
								} else {
									user = u;
									System.out.println("\nWelcome to Willbank, " + u.getWholeName() + ".");
									logged = true;
								}
							} while (!pmatch);
							break;
						}
					}
					if (!logged) System.out.println("We could not find your username in the system.\n");
				} else if (choice == 2) {
					String[] accountDetails = aui.register();
					//TODO: Register
				}
			}
		}
		save();
		
		//Main program loop.
		boolean quit = false;
		while (!quit) {
			//Ask if user wants to:
				//Deposit Money
				//View Balance
				//Withdraw money
				//Log out
			System.out.println("Please choose an option:");
			System.out.println("Press 1 to deposit money.");
			System.out.println("Press 2 to view your balance.");
			System.out.println("Press 3 to withdraw money from your account.");
			System.out.println("Press 4 to log out of the Willbank system.");
			System.out.println("Press 5 to quit.");
			int choice = 0;
			try {
				choice = Integer.parseInt(sc.nextLine());
				if (choice < 1 || choice > 5) {
					System.out.println("Please enter a number between 1 and 5.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number between 1 and 5.");
			}
			if (choice == 1) {
				//Deposit
				double deposit = aui.depositMoney(user);
				//TODO: Deposit logic
			} else if (choice == 2) {
				// View Balance
				aui.viewBalance(user);
			} else if (choice == 3) {
				//Withdraw Money
				double withdraw = aui.withdrawMoney(user);
				//TODO: withdraw logic.
			} else if (choice == 4) {
				//Log out
				boolean log = aui.userLogOut(user);
				if (log) {		
					user = null;
					save();
				}
			} else if (choice == 5) {
				//print thank you message.
				System.out.println("Thank you for using Willbank.");
				quit = true;
			}
			save();
		}
		sc.close();
	}
	
	private static void save() {
		//Serialization + archiving;
		app.setUser(user);
		Archiver.writeUsers(app.getUserList());
		Serializer.serialize(app);
	}
	
	private static boolean load() {
		AppMemory temp = Serializer.deserialize();
		if (temp == null) {
			return false;
		}
		app = temp;
		user = app.getUser();
		return true;
	}

}
