package com.bank.driver;

import java.util.List;
import java.util.Scanner;

import com.bank.obj.Account;
import com.bank.obj.Teller;

public class BankDriver {

	public static void main(String[] args) {
		Teller t = new Teller();
		String option="";
		Scanner menu = new Scanner(System.in);
		
		System.out.println("Welcome to your automated bank terminal!");
		System.out.println("If you already have an account, press 1.");
		System.out.println("If you do not have an account, please press 2.");
		
		do {
			option = String.valueOf(menu.nextLine());
			System.out.println("");
			
			if(!option.equals("1")) {
				if(!option.equals("2")) {
					System.out.println("Sorry, that is not a valid response. Please press 1 or 2.");
					System.out.println("");
					option = "";
				}
			}
		} while (option=="");
		
		//Log-in Menu
		switch(option) {
		
		//Returning user
		case "1":
			String user, pass;
			
			do {
				System.out.println("Enter your username: ");
				user = String.valueOf(menu.nextLine());
				System.out.println("");
				
				System.out.println("Enter your password: ");
				pass = String.valueOf(menu.nextLine());
				System.out.println("");
				
				if (t.logIn(user, pass)) {
					System.out.println("Password accepted: Welcome, " + t.getFirstName() + t.getLastName());
				} 
				else {
					System.out.println("Sorry, that's not a valid username/password combination.");
					System.out.println("Try again.");
					System.out.println("");
					pass = "";
				}
			} while (pass=="");
			break;
			
		//Register new user
		case "2":
			String newUser, newPass, newFirst, newLast, newAccount;
			
			do {
				System.out.println("Enter a username: ");
				newUser = String.valueOf(menu.nextLine());
				System.out.println("");
				
				do {
					System.out.println("Enter a password (minimum of 8 characters): ");
					newPass = String.valueOf(menu.nextLine());
					if(newPass.length()<8) {
						System.out.println("That password is too short.");
						System.out.println("");
					}
				} while (newPass.length()<8);
				System.out.println("");
				
				System.out.println("Enter your first name: ");
				newFirst = String.valueOf(menu.nextLine());
				System.out.println("");
				
				System.out.println("Enter your last name: ");
				newLast = String.valueOf(menu.nextLine());
				System.out.println("");
				
				if(t.createUser(newUser, newFirst, newLast, newPass)) {
					System.out.println("Enter a name for your account: ");
					newAccount = String.valueOf(menu.nextLine());
					System.out.println("");
					
					t.createAccount(newUser, newAccount, true);
					System.out.println("Congratulations! The account " + newAccount + " is now registered " +
							"under the username " + newUser);
					System.out.println("");
				}
				else {
					System.out.println("Sorry, that username is taken. Please choose another username.");
					System.out.println("");
					newUser="";
				}
			} while (newUser=="");
			break;
		}
		
		
		
		//Main Menu
		while(t.getUsername()!=null) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(t.getFirstName() + ", what action would you like to take now?");
			System.out.println("If you would like to make a deposit, press 1.");
			System.out.println("If you would like to make a withdrawl, press 2.");
			System.out.println("If you would like to check your balance, press 3.");
			System.out.println("If you would like to transfer money between accounts, press 4.");
			System.out.println("If you would like to create another account under this username, press 5.");
			System.out.println("If you would like to log out, press 0.");
			option=String.valueOf(menu.nextLine());
			System.out.println("");
			
			String answer="";
			String accountChoice="";
			Double amount;
			List <Account> accountList = t.getAllAccounts();
			int counter = 1;
			
			switch(option) {
			
			//Deposit
			case "1":
				do {
					System.out.println("Which account would you like to deposit to?");
					System.out.print("Your options are: ");
					for(Account account : accountList) {
						System.out.println(Integer.toString(counter) + ": " + account.getAccountName());
						counter++;
					}
					accountChoice = menu.nextLine(); 
					
				} while (accountChoice == "");
				
				System.out.println("How much would you like to deposit?");
				amount = Double.valueOf(menu.nextLine());
				System.out.println("Are you sure you want to deposit?");
				System.out.println("Type Y or N: ");
				
				do {
					answer = menu.nextLine();
					if ((answer.toLowerCase()).equals("y")) {
						t.deposit(accountList.get(Integer.valueOf(accountChoice)-1), amount);
						System.out.println("");
					}
					else if((answer.toLowerCase()).equals("n")){
						System.out.println("Returning to menu...\n");
					} else {
						System.out.println("That was not a valid entry. Please type Y or N: ");
						answer="";

					} 
				}while (answer=="");
				
				System.out.println("Your new balance is $" + String.format("%.2f", t.getBalance(accountChoice)));
				System.out.println("");
				break;
				
			//Withdrawal
			case "2":
				do {
					System.out.println("Which account would you like to withdraw from?");
					System.out.print("Your options are: ");
					for(Account account : accountList) {
						System.out.println(Integer.toString(counter) + ": " + account.getAccountName());
						counter++;
					}
					accountChoice = menu.nextLine(); 
				} while (accountChoice == "");
				
				System.out.println("How much would you like to withdraw?");
				amount = Double.valueOf(menu.nextLine());
				System.out.println("Are you sure you want to withdraw?");
				System.out.println("Type Y or N: ");
				
				do {
					answer = menu.nextLine();
					if ((answer.toLowerCase()).equals("y")) {
						if(t.withdraw((accountList.get(Integer.valueOf(accountChoice)-1)), amount)) {
							
							System.out.println("");
							System.out.println("Your new balance is $" + String.format("%.2f", t.getBalance(accountChoice)));
						}
						else {
							System.out.println("");
							System.out.println("You do not have enough funds to make that withdrawl.");
							System.out.println("We're nice though, so we won't fee you for it.");
						}
						System.out.println("");
					}
					else if((answer.toLowerCase()).equals("n")){
						System.out.println("Returning to menu...\n");
					} else {
						System.out.println("That was not a valid entry. Please type Y or N: ");
						answer="";

					} 
				}while (answer=="");
				break;
				
			//Balance
			case "3":
				do {
					System.out.println("Which account balance would you like to see?");
					System.out.print("Your options are: ");
					for(Account account : accountList) {
						System.out.println(Integer.toString(counter) + ": " + account.getAccountName());
						counter++;
					}
					accountChoice = menu.nextLine(); 
				} while (accountChoice == "");
				
				System.out.println("Your current balance is $" + String.format("%.2f", 
						t.getBalance((accountList.get(Integer.valueOf(accountChoice)-1)).getAccountName())));
				System.out.println("");
				break;
				
			//Transfer
			case "4":
				Account toAccount, fromAccount;
				if(accountList.size()>1) {
					do {
					System.out.println("Which account would you like to transfer from?");
					System.out.print("Your options are: ");
					for(Account account : accountList) {
						System.out.println(Integer.toString(counter) + ": " + account.getAccountName());
						counter++;
					}
					accountChoice = menu.nextLine(); 
					} while (accountChoice == "");
					fromAccount = accountList.get(Integer.valueOf(accountChoice)-1);
					
					counter = 0;
					do {
						System.out.println("Which account would you like to withdraw to?");
						System.out.print("Your options are: ");
						for(Account account : accountList) {
							if(!fromAccount.equals(account)) {
								System.out.println(Integer.toString(counter) + ": " + account.getAccountName());
								counter++;
							}
						}
						accountChoice = menu.nextLine(); 
					} while (accountChoice == "");
					toAccount = accountList.get(Integer.valueOf(accountChoice)-1);
					
					System.out.println("How much would you like to transfer?");
					amount = Double.valueOf(menu.nextLine());
					System.out.println("Are you sure you want to transfer from " +
					fromAccount.getAccountName() + " to " + toAccount.getAccountName() + "?");
					System.out.println("Type Y or N: ");
					
					do {
						answer = menu.nextLine();
						if ((answer.toLowerCase()).equals("y")) {
							if(t.transfer(fromAccount, toAccount, amount)) {
								
								System.out.println("");
								System.out.println("The new balance of " + 
										toAccount + "is $" + String.format("%.2f", t.getBalance(toAccount.getAccountName())));
							}
							else {
								System.out.println("");
								System.out.println("You do not have enough funds to make that transfer.");
								System.out.println("We're nice though, so we won't fee you for it.");
							}
							System.out.println("");
						}
						else if((answer.toLowerCase()).equals("n")){
							System.out.println("Returning to menu...\n");
						} else {
							System.out.println("That was not a valid entry. Please type Y or N: ");
							answer="";
	
						} 
					}while (answer=="");
				}
				else {
					System.out.println("Sorry, you only have one account and cannot transfer funds.");
				}
				
				break;
				
			//Add account
			case "5":
				//t.createAccount(username, newAccount, false);
				break;
				
			//Log-out
			case "0":
				System.out.println("Are you sure you want to log out?");
				System.out.println("Type Y or N: ");
				do {
					answer = menu.nextLine();
					if ((answer.toLowerCase()).equals("y")) {
						t.logOut();
					}
					else if((answer.toLowerCase()).equals("n")){
						System.out.println("Returning to menu...\n");
					} 
					else {
						System.out.println("That was not a valid entry. Please type Y or N: ");

					} 
				}while (answer=="");
				break;
			default:
				System.out.println("That was not a valid input.");
			}
			if(!option.equals("0")) {
				System.out.println("Please re-enter your password, or enter E to exit: ");
				String repeatResponse = String.valueOf(menu.nextLine());
				while(!repeatResponse.toLowerCase().equals((String)"e")&&!repeatResponse.equals(String.valueOf(t.getPassword()))) {
					System.out.println("Sorry, that was not the correct password.");
					System.out.println("Please re-enter your password or enter E to exit: ");
					System.out.println("");
					repeatResponse = String.valueOf(menu.nextLine());
				}
				if(repeatResponse.toLowerCase().equals((String)"e")) {
					t.logOut();
				}
				else {
					System.out.println("Returning to menu...\n");
				}
			}
			
		}
		
		//Exit screen
		System.out.println("Thank you for using your automated bank terminal.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Have a nice day!");
		menu.close();
	}

}
