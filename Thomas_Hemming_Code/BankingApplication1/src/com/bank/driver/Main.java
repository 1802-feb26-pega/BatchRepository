package com.bank.driver;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.bank.objects.Bank;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Bank myBank = new Bank();

		String selection = null;
		
		myBank.startBank();
		
		while(true) {
			System.out.println("The Bank of Tom");
			System.out.println("Please select from from the following options:");
			System.out.println("Login");
			System.out.println("Register");
			System.out.println("Exit");
			
			selection = validateInput(1);
			
			if(selection.equals("login")){
				String email = null;
				String password;
				System.out.println("Email:");
				
				email = validateInput(3);
				
				System.out.println("Password:");
				
				password = scan.next();
				
				if(!myBank.login(email, password)) {
					System.out.println("Incorrect email or password.");
				}
				
				while(myBank.isLoggedIn()) {
					System.out.println("Input one of the following options");
					System.out.println("Deposit");
					System.out.println("Withdraw");
					System.out.println("Balance");
					System.out.println("Logout");
					
					String selection2 = validateInput(2);
					selection2 = selection2.toLowerCase();
					
					switch(selection2) {
						case "deposit": 
							System.out.println("Deposit amount:");
							String depositAmount = validateInput(4);
							if(myBank.deposit(Double.valueOf(depositAmount))) {
								System.out.println("Deposit completed.");
							}
							break;
						case "withdraw": 	
							System.out.println("Enter the amount to Withdraw:");
							String withdrawAmount = validateInput(4);
							if(!myBank.withdraw(Double.valueOf(withdrawAmount))) {
								System.out.println("Insufficiant funds.");
							}
							else {
								System.out.println("Withdraw completed.");
							}
							break;
						case "balance": 	
							System.out.println(myBank.getBalance());
							break;
						case "logout": 	
							myBank.logout();
							System.out.println("Logged out.");
							break;
					}
				}
			} else if(selection.equals("register")) {
				
				String fn;
				String ln;
				String email;
				String pw;
				
				
				System.out.println("First name:");
				fn = validateInput(5);
				System.out.println("Last name:");
				ln = validateInput(5);
				System.out.println("Email:");
				email = validateInput(3);
				System.out.println("Password:");
				pw = scan.next();
				
				if(!myBank.register(fn, ln, email, pw)) {
					System.out.println("That e-mail has already been registered.");
				} else {
					System.out.println("Thank you for registering at The Bank of Tom.");
				}
					
			} else if(selection.equals("exit")) {
				System.out.println("Exiting.");
				myBank.closeBank();
				break;
			} else {
				System.out.println("Invalid selection. Please try again.");
			}
		}
				
	}
	
	private static String validateInput(int type) {
		
		String input;
		boolean x = false;
		
		do {
			input = scan.next();
			input = input.toLowerCase();
			List<String> list1 = Arrays.asList("login", "register", "exit");
			List<String> list2 = Arrays.asList("withdraw", "deposit", "balance", "logout");
			
			switch(type) {
			case 1: 
				if(!list1.contains(input)) {
					System.out.println("You have entered an invalid selection. Please try again.");
					x = true;
				} else {
					x = false;
				} 
				break;
			case 2: 
				if(!list2.contains(input)) {
					System.out.println("You have entered an invalid selection. Please try again.");
					x = true;
				} else {
					x = false;
				}
				break;
			case 3:	
				if(!checkEmail(input)) {
					System.out.println("You have entered an invalid email. Please try again.");
					x = true;
				} else {
					x = false;
				}
				break;
			case 4:	
				if(!checkCurrency(input)) {
					System.out.println("You have entered an invalid amount. Please try again.");
					x = true;
				} else {
					x = false;
				}
				break;
			case 5: 
				if(!checkName(input)) {
					System.out.println("You have entered an invalid character. Please try again.");
					x = true;
				} else {
					x = false;
				}
				break;
			}	
		} while(x == true);
		
		return input;
	}
	
	private static boolean checkCurrency(String currency) {
		if(currency.matches("[+]?\\d*\\.?\\d?\\d?") && Double.parseDouble(currency) < Double.MAX_VALUE) {
			return true;
		}
		return false;
	}
	
	private static boolean checkEmail(String email) {
		if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			return true;
		}
		return false;
	}
	private static boolean checkName(String name) {
		if(name.matches("[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

}