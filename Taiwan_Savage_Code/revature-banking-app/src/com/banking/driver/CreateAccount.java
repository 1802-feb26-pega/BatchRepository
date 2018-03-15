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

public class CreateAccount {
	
	public CreateAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to create a new Account?\nEnter 1 for yes or 2 for no");
		boolean response;
		while (true) {
			try {
				Integer input = Integer.parseInt(scan.next());
				clearConsole();
				scan.nextLine();
				if (input == 1) {
					response = true;
					break;
				}
				else if (input == 2) {
					response = false;
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
		
		/**
		 * 
		 *  this is the actual process for creating a new account
		 * 	the parameters for creating a new account will be gathered here
		 * 		- First Name
		 * 		- Last Name
		 * 		- UserName
		 * 		- Password
		 * 		- Email (optional) // adding in second iteration
		 *  
		 */
		if (response == true) {
			String first = getFirstName();
			String last = getLastName();
			String username = getUsername();
			String password = getPassword();
			
			/**
			 * create a new user -- will be serialized into it's own file upon logout
			 */
			User newUser = new User(first, last, username, password);
			
			// create an actions menu object -- business processes
			clearConsole();
			ChooseAction ca = new ChooseAction(newUser);
			return;	
		}
		
		// end process
		else {
			System.out.println("Have a nice day!");
			return;
		}
	}
	
	// gets first name for user creation
	public String getFirstName() {
		clearConsole();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your first name: ");
		String first = scan.nextLine();
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) >= '0' && first.charAt(i) <= '9') {
				System.out.println("Please enter a valid name (no offense)");
				return getFirstName();
			}
			else if ((first.charAt(i) >= 'a' && first.charAt(i) <= 'z')
					|| (first.charAt(i) >= 'A' && first.charAt(i) <= 'Z')){
				
			}
			else if (first.charAt(i) == '\'') {
				
			}
			else {
				System.out.println("Please enter a valid name (no offense)");
				return getFirstName();
			}
		}
		return first;
	}
	
	// gets last name for user creation
	public String getLastName() {
		clearConsole();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your last name: ");
		String last = scan.nextLine();
		for (int i = 0; i < last.length(); i++) {
			if (last.charAt(i) >= '0' && last.charAt(i) <= '9') {
				System.out.println("Please enter a valid name (no offense)");
				return getLastName();
			}
			else if ((last.charAt(i) >= 'a' && last.charAt(i) <= 'z')
					|| (last.charAt(i) >= 'A' && last.charAt(i) <= 'Z')){
				
			}
			else if (last.charAt(i) == '\'') {
				
			}
			else {
				System.out.println("Please enter a valid name (no offense)");
				return getLastName();
			}
		}
		return last;
	}
	
	// gets password for user creation
	public String getPassword() {
		clearConsole();
		Scanner scan = new Scanner(System.in);
		String password;
		System.out.println("Create a password (must be more than 5 characters and no spaces): ");
		password = scan.nextLine();
		boolean valid = false;
		if (password.length() < 5) {
			System.out.println("Password too short.");
			return getPassword();
		}
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == ' ') {
				System.out.println("Invalid password. No spaces please.");
				return getPassword();
			}
		}
		System.out.println("Confirm password. Please re-enter: ");
		if (password.equals(scan.nextLine())) {
			valid = true;
			return password;
		}
		else {
			System.out.println("Passwords do not match. Please try again.");
			return getPassword();
		}
	}
	
	// gets the username for user creation
	public String getUsername() {
		clearConsole();
		Scanner scan = new Scanner(System.in);
		String username;
		System.out.println("Create a username (any character but no spaces please / at most 10 characters): ");
		username = scan.nextLine();
		boolean valid = false;
		if (username.length() > 10) {
			System.out.println("Username too long.");
			getUsername();
		}
		for (int i = 0; i < username.length(); i++) {
			if (username.charAt(i) == ' ') {
				System.out.println("Invalid username. No spaces please.");
				break;
			}
			if (i == username.length() -1) {
				valid = true;
			}
		}
		if (valid == true) {
			File directory = new File("src/users");
			File[] files = directory.listFiles();
			User user = new User();
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
					System.out.println("Username already exists.");
					valid = false;
					break;
				}
				
				//re-serialize the file we just deserialized to check
				serialize(user);
			}
		}
		if (valid == true) {
			return username;
		}
		else {
			return getUsername();
		}
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

}
