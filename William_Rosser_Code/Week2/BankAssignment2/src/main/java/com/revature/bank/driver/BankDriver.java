package com.revature.bank.driver;

import java.util.List;
import java.util.Scanner;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;
import com.revature.bank.util.ApplicationUI;

public class BankDriver {
	private static UserDAO uDao= UserDAOImpl.getInstance();
	private static AccountDAO aDao = AccountDAOImpl.getInstance();
	private static ApplicationUI aui = ApplicationUI.getInstance();
	private static User user = null;
	
	public static void main(String[] args) {
		Scanner sc = aui.getScanner();
		boolean quit = false;
		while (!quit) {
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
						User temp = null;
						do {
							String[] credentials = aui.userLogIn();

							if (credentials[0].contains("@"))
								temp = uDao.getUserByEmail(credentials[0]);
							else
								temp = uDao.getUserByUsername(credentials[0]);

							if (temp == null) {
								System.out.println("Incorrect user name or email.");
							} else if (temp.getPassword().equals(credentials[1])) {
								user = temp;
								logged = true;
								System.out.println("\nWelcome to Willbank, " + user.getWholeName() + ".");
							} else {
								System.out.println("Incorrect password.");
								temp = null;
							}
						} while (temp == null);
					} else if (choice == 2) { //register
						String[] temp = aui.register();
						if (temp != null) {
							user = new User(temp[0],temp[1],temp[2],temp[3],temp[4],temp[5]);
							uDao.addUser(user);
							logged = true;
							System.out.println("Registered.");
						}
					}
				}
			}


			int choice = aui.getMainChoice(user);
			if (choice == 1) {
				Account a = aui.createAccount(user);
				if (a != null) {
					aDao.addAccount(a);		
				}
				
			} else if (choice == 2) {
				//Deposit
				List<Account> userAccounts = aDao.getAllAccountsForUser(user);
				double[] depositInfo = aui.depositMoney(user, userAccounts);
				if (depositInfo[0] >= 0 && depositInfo[1] > 0) {
					Account a = userAccounts.get((int) depositInfo[0]);
					a.deposit(depositInfo[1]);
					System.out.println(String.format("Successfully added $%.2f", depositInfo[1]) + " to account" + a.getAccountName() + ".");
					System.out.println(String.format("Your Balance is now $%.2f.\n", a.getBalance()));
					aDao.updateAccount(a);
				} else {
					System.out.println("No change was made to your account.\n");
				}
			} else if (choice == 3) {
				// View Balance
				List<Account> accounts = aDao.getAllAccountsForUser(user);
				aui.viewBalance(user, accounts);
			}  else if (choice == 4) {
				//Withdraw Money
				List<Account> accounts = aDao.getAllAccountsForUser(user);
				double[] withdrawInfo = aui.withdrawMoney(user, accounts);
				if (withdrawInfo[0] < 0) {
					System.out.println("Withdrawl cancled.");
					continue;
				} 
				Account a = accounts.get((int) withdrawInfo[0]);
				if(a.withdraw(withdrawInfo[1])) {
					System.out.println("You've withdrawn $" + withdrawInfo[1]);
					System.out.println("Your new balance is $" + a.getBalance());
					aDao.updateAccount(a);
				}
			} else if (choice == 5) {
				List<Account> userAccounts = aDao.getAllAccountsForUser(user);
				Account[] changed = aui.transferMoney(userAccounts);
				if (changed != null) {
					aDao.updateAccount(changed[0]);
					aDao.updateAccount(changed[1]);
				}
			} else if (choice == 6) {
				//Log out
				boolean log = aui.userLogOut(user);
				if (log) {		
					user = null;
				}
			} else if (choice >= 7) {
				//print thank you message.
				System.out.println("Thank you for using Willbank.");
				quit = true;
				sc.close();
			}	
		}
	}

	public static boolean check(String test, boolean email) {
		if (email) {
			return uDao.getUserByEmail(test) == null;
		} else {
			return uDao.getUserByUsername(test) == null;
		}
	}

}

