package com.assign.run;


import java.util.Scanner;
import com.assign.pojos.Bank;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Bank myBank = new Bank();

		String selection1 = null;
		

		boolean bankOpen = myBank.openBank();
		
		while(bankOpen) {
			System.out.println("Hello and welcome to myBank!");
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
				
				if(!myBank.login(email, password)) {
					System.out.println("Incorrect email or password");
				}
				
				while(myBank.getIsLoggedIn()) {
					System.out.println("Select from from the following options");
					System.out.println("1 - Deposit");
					System.out.println("2 - Withdraw");
					System.out.println("3 - View Balance");
					System.out.println("4 - Logout");
					
					String selection2 = validateInput(1);
					
					switch(selection2) {
						case "1": 	System.out.println("Enter the amount to Deposit:");
									String depositAmount = validateInput(3);
									if(myBank.deposit(Double.valueOf(depositAmount))) {
										System.out.println("Deposit completed.");
									}
									break;
						case "2": 	System.out.println("Enter the amount to Withdraw:");
									String withdrawAmount = validateInput(3);
									if(!myBank.withdraw(Double.valueOf(withdrawAmount))) {
										System.out.println("You have insufficiant funds.");
									}
									else {
										System.out.println("Withdrawl completed");
									}
									break;
						case "3": 	System.out.println(myBank.viewBalance());
									break;
						case "4": 	myBank.logout();
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
				
				if(!myBank.register(firstName, lastName, email, password1)) {
					System.out.println("User with myBank email already exists.");
				}
				else {
					System.out.println("Registration complete!");
				}
					
			}
			else if(selection1.equals("3")) {
				bankOpen = myBank.closeBank();
				System.out.println("myBank is now closed.");
			}
		}
				
	}
	
	private static String validateInput(int type) {
		
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

}
