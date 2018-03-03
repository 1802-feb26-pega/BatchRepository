package com.assign.pojos;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.text.NumberFormatter;

import com.assign.io.DataFileIO;

public class Bank implements Runnable{

	private ArrayList<User> users;
	private boolean loggedIn;
	private User currentUser;
	private DataFileIO io;
	private Scanner scan;
	
	
	public Bank() {
		users = new ArrayList<>();
		io = new DataFileIO();
		scan = new Scanner(System.in);
	}
	
	@Override
	public void run() {
		String selection1 = null;

		boolean bankOpen = this.openBank();
		
		while(bankOpen) {
			System.out.println("Hello and welcome to this!");
			System.out.println("Select from from the following options");
			System.out.println("1 - Login");
			System.out.println("2 - Register");
			System.out.println("3 - Shutdown");
			
			selection1 = validateInput(1);
			
			if(selection1.equals("1")){
				String email = null;
				String password;
				System.out.println("Please enter your email: ");
				
				email = validateInput(2);
				
				System.out.println("Please enter you password:");
				
				password = scan.next();
				
				if(!this.login(email, password)) {
					System.out.println("Incorrect email or password");
				}
				
				while(this.getIsLoggedIn()) {
					System.out.println("Select from from the following options");
					System.out.println("1 - Deposit");
					System.out.println("2 - Withdraw");
					System.out.println("3 - View Balance");
					System.out.println("4 - Logout");
					
					String selection2 = validateInput(1);
					
					switch(selection2) {
						case "1": 	System.out.println("Enter the amount to Deposit:");
									String depositAmount = validateInput(3);
									if(this.deposit(Double.valueOf(depositAmount))) {
										System.out.println("Deposit completed.");
									}
									break;
						case "2": 	System.out.println("Enter the amount to Withdraw:");
									String withdrawAmount = validateInput(3);
									if(!this.withdraw(Double.valueOf(withdrawAmount))) {
										System.out.println("You have insufficiant funds.");
									}
									else {
										System.out.println("Withdrawl completed");
									}
									break;
						case "3": 	System.out.println(this.viewBalance());
									break;
						case "4": 	this.logout();
									System.out.println("You have logged out.");
									break;
					}
				}
			}
			else if(selection1.equals("2")) {
				
				String firstName;
				String lastName;
				String email;
				String password1 = null;
				String password2;
				boolean pwNotVerified = true;
				
				System.out.println("Please enter your first name:");
				firstName = validateInput(4);
				System.out.println("Please enter your last name:");
				lastName = validateInput(4);
				System.out.println("Please enter your email:");
				email = validateInput(2);
				
				while(pwNotVerified){
					System.out.println("Please enter your password:");
					password1 = scan.next();
					System.out.println("Please verify your password:");
					password2 = scan.next();
					if(!password1.equals(password2)) {
						System.out.println("Entered passwords don't match.");
					}
					else {
						pwNotVerified = false;
					}
				}
				
				if(!this.register(firstName, lastName, email, password1)) {
					System.out.println("User with this email already exists.");
				}
				else {
					System.out.println("Registration complete!");
				}
					
			}
			else if(selection1.equals("3")) {
				bankOpen = this.closeBank();
				System.out.println("this is now closed.");
			}
		}
		
	}
	
	
	public boolean openBank() {
		this.users = io.readUsers();
		return true;
		
	}
	
	public boolean closeBank() {
		
		for(User u : users) {
			io.writeUsers(u);
		}
		return false;
	}

	public boolean login(String email, String password) {
		
		for(User u : users) {
			
			if(u.getEmail().equals(email)) {
				
				if(u.getPassword().equals(password)) {
					currentUser = u;
					loggedIn = true;
					return true;
				}
				else {
					return false;
				}	
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public boolean logout() {
		loggedIn = false;
		currentUser = null;
		return true;
	}
	
	public boolean register(String firstName, String lastName, String email, String password) {
		
		for(User u : users) {
			
			if(u.getEmail().equals(email)) {				
				return false;
			}
		}	
		User newUser = new User(firstName, lastName, email, password, 0.0);	
		users.add(newUser);
		return true;		
	}
	
	public boolean withdraw(double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		
		double balance = currentUser.getBalance();
		
		if(balance < amount) {
			
			return false;
		}
		else {
			currentUser.setBalance(balance - amount);
			return true;
		}	
	}
	
	public boolean deposit(double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		currentUser.setBalance(currentUser.getBalance() + amount);
		return true;
	}
	
	public String viewBalance() {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(currentUser.getBalance());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
private String validateInput(int type) {
		
		String input;
		boolean loopFlag = false;
		
		do {
			input = scan.next();
			switch(type) {
			case 1:	if(!validateNumberOnlyInput(input)) {
						System.out.println("You have entered an invalid selection.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 2:	if(!validateEmailInput(input)) {
						System.out.println("You have entered an invalid email.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 3:	if(!validateCurrencyInput(input)) {
						System.out.println("You have entered an invalid amount.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			case 4: if(!validateNameInput(input)) {
						System.out.println("You have entered an invalid character.");
						loopFlag = true;
					}
					else {
						loopFlag = false;
					}
					break;
			}	
		} while(loopFlag);
		
		return input;
	}
	
	private static boolean validateNumberOnlyInput(String input) {
		
		if(input.matches("[^0-9]{1}")) {
			return false;
		}
		
		return true;
	}
	
	private static boolean validateCurrencyInput(String input) {
		
		if(input.matches("[-+]?\\d*\\.?\\d{2}")) {
			return true;
		}
		else return false;
	}
	
	private static boolean validateEmailInput(String input) {
		
		if(input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		
		return false;
	}
	private static boolean validateNameInput(String input) {
		if(input.matches("[a-zA-Z]+")) {
			return true;
		}
		
		return false;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public boolean getIsLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	
	
	
	
	
}
