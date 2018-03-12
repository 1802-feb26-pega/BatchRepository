package com.revature.bank.service;

import java.util.Scanner;

import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.User;
import com.revature.bank.util.ConsoleConnectionFactory;

public class FirstMenu {
	
	public void initialMenu() {
		FirstMenu fm = new FirstMenu();
		int option = 0;
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();

		System.out.println("To log in, enter 1");
		System.out.println("To create an account, enter 2");
		try {
			option = Integer.parseInt(scan.nextLine());
//			scan.nextLine();
			if(option != 1 && option != 2) {
				System.out.println("Enter a valid menu option.\n");
				fm.initialMenu();
			} 
		} catch (NumberFormatException ime) {
			System.out.println("Enter a valid menu option.\n");
			fm.initialMenu();
		}
		
		if(option == 1) {
			fm.loginMenu();
		} else if(option == 2){
			fm.createUser();
		} else {
			System.out.println("Something went wrong; please try again.");
			fm.initialMenu();
		}
	}

	private void createUser() {
		FirstMenu fm = new FirstMenu();
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		System.out.println("Enter the username you wish to have.");
		String un = scan.nextLine();
		boolean validUN = fm.verifyUsername(un);
		while(validUN == true) {
			System.out.println("\nThat username is not available. Choose another.");
			un = scan.nextLine();
			validUN = fm.verifyUsername(un);
		}
		System.out.println("\nEnter the password you wish to have.");
		String pwd = scan.nextLine();
		System.out.println("\nEnter your first name.");
		String fn = scan.nextLine();
		System.out.println("\nEnter your last name.");
		String ln = scan.nextLine();
		UserDAO userDao = new UserDAOImpl();
		User newUser = new User(0, un, pwd, fn, ln, null);
		newUser = userDao.addUser(newUser);
		System.out.println("User created! You have been logged in.");
		MainMenu mm = new MainMenu();
		mm.mainMenu(newUser);
	}

	private void loginMenu() {
		FirstMenu fm = new FirstMenu();
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		System.out.println("Enter your username.");
		String un = scan.nextLine();
		boolean validUN = fm.verifyUsername(un);
		if(validUN == false) {
			System.out.println("There is no user with that username.");
			fm.initialMenu();
		}
		System.out.println("Enter your password.");
		String pwd = scan.nextLine();
		boolean validPWD = fm.verifyPassword(un, pwd);
		if(validPWD == false) {
			System.out.println("Invalid password.");
			fm.initialMenu();
		} else {
			UserDAO userDao = new UserDAOImpl();
			MainMenu mm = new MainMenu();
			User user = userDao.getUserByUsername(un);
			System.out.println("Logged in.\n");
			mm.mainMenu(user);
		}
	}
	
	private boolean verifyUsername(String un) {	
		UserDAO userDao = new UserDAOImpl();
		User login = userDao.getUserByUsername(un);
		if(login.getUserId() > 0) {
			return true;
		}
		return false;
	}
	
	private boolean verifyPassword(String un, String pwd) {	
		UserDAO userDao = new UserDAOImpl();
		User login = userDao.getUserByUsername(un);
		if(login.getPassword().equals(pwd)) {
			return true;
		}
		return false;
	}
	
	
}
