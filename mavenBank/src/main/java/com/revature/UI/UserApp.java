package com.revature.UI;

import java.util.Scanner;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.Verification;

/*
 * Handles all user interface the user will see when creating an account, logging in
 * and logging out.
 */
public class UserApp {

	public static void userOptions() {
		
		System.out.println("Press 1: Create a new account");
		System.out.println("Press 2: Log in with an existing account");
		System.out.println("Press 0: Exit application");
		
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
				System.out.println("Invalid option!");
				System.out.println("Press 1: Create a new account");
				System.out.println("Press 2: Log in with an existing account");
				System.out.println("Press 0: Exit application");
				break;
			}	
		}
	}
	
	public static void createAccount() {
		
		UserDAO userDao = new UserDAOImpl();
		AccountDAO accountDao = new AccountDAOImpl();
		BankApp bankApp = new BankApp();
		Verification verify = new Verification();
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Creating a new account");
		
		String username = "";
		String password = "";
		String firstname = "";
		String lastname = "";
		
		while (true) {
			
			System.out.println("Enter your username: ");
			username = in.nextLine();
			
			System.out.println("Enter your password: ");
			password = in.nextLine();
			
			System.out.println("Enter your firstname: ");
			firstname = in.nextLine();
			
			System.out.println("Enter your lastname: ");
			lastname = in.nextLine();
			
			User user = new User(username, password, firstname, lastname);
			
			// isValid is true if username is unique, false if username exists			
			boolean isValid = verify.uniqueUser(user);
			
			if (isValid) {
				user = userDao.addUser(user);
				Account account = new Account(0, user.getUserId());
				accountDao.addAccount(account);
				
				// Send new user to their bank account
				bankApp.bankAccount(user);
				break;
			} else {
				System.out.println("Error! Username is already taken. Create a new username.");
			}
		}	
	}
	
	public static void logIn() {
		
		System.out.println("Enter your username and password to log in.");
		String username;
		String password;
		
		UserDAO userDao = new UserDAOImpl();
		BankApp bankApp = new BankApp();
		Verification verify = new Verification();
		
		Scanner in = new Scanner(System.in);
						
		do {
			System.out.print("Username: ");
			username = in.nextLine();
			System.out.print("\nPassword: ");
			password = in.nextLine();
			
			User temp = new User(username, password);
			
			if (verify.verifyUser(temp)) {	
				
				if (verify.verifyPassword(temp)) {
					
					User user = userDao.getUserByName(temp.getUsername());
					bankApp.bankAccount(user);
					
					break;
					
				} else {
					System.out.println("Error! Username or password is not correct.");
				}
			} else {
				System.out.println("Error! Username does not exist.");
			}
		} while (true);
	}
}