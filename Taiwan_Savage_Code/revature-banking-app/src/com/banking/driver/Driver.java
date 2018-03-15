package com.banking.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.banking.user.User;


/**
 * 
 * @author taikwando
 *
 * This is the driver for the banking app
 * 	- This will control the flow of the program.
 * 	- The flow will be determined based on what the user would like to do
 * 	- For now, the console is the primary for of communication between the program and the user
 * 		but eventually the front end will be replaced
 * 	- This driver method will only exist to communicate with the user and direct the program to the
 * 		appropriate object depending on what the user inputs
 * 
 * Notes:
 *	- NumberFormatException:
 *		used in the driver to make sure the input is an integer
 *	- go to login:
 *		takes user to the login process
 *			- another class in the driver package
 *	- go to account creation
 *		takes user to the account creation process
 *			- another class in the driver package
 * 
 */
public class Driver {
	
	public Driver() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Savage Banking Service! \nAre you already a member?");
		boolean member = isMember();
		login(member);	
	}
	
	/**
	 * login method
	 */
	public void login(boolean member) {
		Scanner scan = new Scanner(System.in);
		clearConsole();
		if (member == true) {
			File directory = new File("src/users");
			File[] files = directory.listFiles();
			if (files.length == 0) {
				System.out.println("There are currently no members.");
				clearConsole();
				CreateAccount newAccount = new CreateAccount();
				scan.close();
				return;
			}
			while (true) {
				boolean wasUser = checkUsername(files);
				if (wasUser == false) {
					while (true) {
						System.out.println("That was not a valid username. Would you like to try again?"
								+ "\n1 for yes. 2 for no.");	
						try {
							Integer input = Integer.parseInt(scan.nextLine());
							if (input == 1) {
								clearConsole();
								break;
							}
							else if (input == 2) {
								System.out.println("Have a nice day");
								clearConsole();
								return;
							}
							else {
								System.out.println("Please enter a valid response");
								clearConsole();
							}
							
						} catch (NumberFormatException nfe) { 
							System.out.println("Please enter a valid response");
							clearConsole();
						}
					}
					
				}
				else {
					return;
				}
			}
		}
		
		// go to account creation
		else {
			CreateAccount newAccount = new CreateAccount();
			scan.close();
		}
		return;
	}
	
	/**
	 * used to see if username already exists
	 */
	public boolean checkUsername(File[] files) {
		Scanner scan = new Scanner(System.in);
		User user = new User();
		System.out.println("What is your username?");
		String username = scan.nextLine();
		clearConsole();
		for (File file : files) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				user = (User) ois.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (user.username.equals(username)) {
				boolean loggedIn = false;
				while (true) {
					System.out.println("What is your password?");
					String password = scan.nextLine();
					clearConsole();
					if (password.equals(user.getPassword())) {
						System.out.println("Welcome back " + user.getFirstName() + ".");
						clearConsole();
						ChooseAction ca = new ChooseAction(user);
						return true;
					}
				}
			}
			
			//re-serialize the file we just deserialized to check
			serialize(user);
		}
		return false;
	}
	
	/**
	 * checks to see if the user thinks they are already a member
	 */
	public boolean isMember() {
		Scanner scan = new Scanner(System.in);
		boolean member;
		while (true) {
			System.out.println("Enter 1 for yes or 2 for no");
			try {
				Integer input = Integer.parseInt(scan.nextLine());
				clearConsole();
				if (input == 1) {
					member = true;
					break;
				}
				else if (input == 2) {
					member = false;
					break;
				}
				else {
					System.out.println("Please enter a valid response");
					clearConsole();
				}
				
			} catch (NumberFormatException nfe) { 
				System.out.println("Please enter a valid response");
				clearConsole();
			}
		}
		return member;
	}
	
	/**
	 * clears the console for enhanced user experience
	 */
	public void clearConsole() {
		System.out.print("\n########################\n");
	}
	
	public void serialize(User user) {
		try(ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(user.getSerializedFile()))) {
			oos.writeObject(user);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * 
	 * Using the main to start the program but no functionality will be in the main method besides
	 * calling the driver constructor for now/
	 */
	public static void main(String[] args) {
		try {
			Driver driver = new Driver();
		} catch (Exception e) {
			System.out.println("Something happened...");
		}

	}

}
