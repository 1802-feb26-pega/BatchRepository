package com.revature.bankapp.driver;

import java.util.Scanner;

import com.revature.bankapp.pojos.Account;
import com.revature.bankapp.pojos.Bank;
import com.revature.bankapp.util.UserInputValidation;

//The BankDriver class utilizes a main method for running the bank app. It houses all of the user interaction code.

public class BankDriver {

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
			
			selection1 = UserInputValidation.validateInput(1, scan);
			
			if(selection1.equals("1")){
				String email = null;
				String password;
				System.out.println("Please enter your email: ");
				
				email = UserInputValidation.validateInput(2, scan);
				
				System.out.println("Please enter you password:");
				
				password = scan.next();
				
				if(!myBank.login(email, password)) {
					System.out.println("Incorrect email or password");
				}
				
				while(myBank.getIsLoggedIn()) {
					System.out.println("Hello " + myBank.getCurrentUser().getFirstName());
					System.out.println("You have access to the following accounts: ");
					
					for(Account acc : myBank.getCurrentUserAccounts()) {
						System.out.println(acc);
					}
					
					System.out.println("Select from from the following options");
					System.out.println("1 - Create an Account");
					System.out.println("2 - Deposit");
					System.out.println("3 - Withdraw");
					System.out.println("4 - Transfer");
					System.out.println("5 - View Total Balance of All Accounts");
					System.out.println("6 - Logout");
					
					String selection2 = UserInputValidation.validateInput(1, scan);
					
					switch(selection2) {
						case "1":	System.out.println("Your new account has been created:");
									System.out.println(myBank.createAccount());
									break;
						case "2": 	System.out.println("Enter the account id of the account you wish to deposit into:");
									String accountId = UserInputValidation.validateInput(5, scan);
									System.out.println("Enter the amount to Deposit:");
									String depositAmount = UserInputValidation.validateInput(3, scan);
									if(!myBank.deposit(Integer.parseInt(accountId), Double.valueOf(depositAmount))) {
										System.out.println("You have entered an account number you don't have access to.");
									} else {
										System.out.println("Deposit completed.");
									}
									break;
						case "3": 	System.out.println("Enter the account id of the account you wish to withdraw from:");
									accountId = UserInputValidation.validateInput(5, scan);
									System.out.println("Enter the amount to Withdraw:");
									String withdrawAmount = UserInputValidation.validateInput(3, scan);
									switch(myBank.withdraw(Integer.parseInt(accountId), Double.valueOf(withdrawAmount))) {
									case 1: System.out.println("You have entered an account number you don't have access to.");
											break;
									case 2: System.out.println("You have insufficiant funds.");
											break;
									case 3: System.out.println("Withdraw completed");
											break;
								}
									break;
						case "4":	System.out.println("Enter the account id of the account you wish to withdraw from:");
									accountId = UserInputValidation.validateInput(5, scan);
									System.out.println("Enter the account id of the account you wish to deposit into:");
									String accountId2 = UserInputValidation.validateInput(5, scan);
									System.out.println("Enter the amount to Withdraw:");
									String transferAmount = UserInputValidation.validateInput(3, scan);
									switch(myBank.transfer(Integer.parseInt(accountId), Integer.parseInt(accountId2), Double.valueOf(transferAmount))) {
										case 1: System.out.println("You have entered an account number you don't have access to.");
												break;
										case 2: System.out.println("You have insufficiant funds.");
												break;
										case 3: System.out.println("Transfer completed");
												break;
									}
									break;
						case "5": 	System.out.println(myBank.viewTotalBalanceOfAllAccounts());
									break;
						case "6": 	myBank.logout();
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
				firstName = UserInputValidation.validateInput(4, scan);
				System.out.println("Please enter your last name:");
				lastName = UserInputValidation.validateInput(4, scan);
				System.out.println("Please enter your email:");
				email = UserInputValidation.validateInput(2, scan);
				
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
		
		scan.close();
		
	}
	
	
}
