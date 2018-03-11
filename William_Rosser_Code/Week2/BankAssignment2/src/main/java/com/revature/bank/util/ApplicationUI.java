package com.revature.bank.util;

import java.util.List;
import java.util.Scanner;

import com.revature.bank.driver.BankDriver;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;

public class ApplicationUI {
	private Scanner sc;
	private static ApplicationUI aui = null;

	private ApplicationUI() {
		sc = new Scanner(System.in);
		aui = this;
	}

	public static synchronized ApplicationUI getInstance() {
		if (aui == null) return new ApplicationUI();
		return aui;
	}

	public Scanner getScanner() {
		return sc;
	}

	/**
	 * Prompts the user to register for an account. Returns a String array with the following format:
	 *     [0] = User's First name. (I.E. "Joe")
	 *     [1] = User's middle initial (I.E. "J")
	 *     [2] = User's last name ("Smith")
	 *     [3] = User's email address.
	 *     [4] = User's chosen username.
	 *     [5] = User's password.
	 *     
	 * If any input is invalid, or the user chooses to exit, this returns null instead.
	 * @return String array of user input (length 5), or null.
	 */
	public String[] register() {
		System.out.println("Welcome to Willbank! We are glad you chose to bank with us.");
		System.out.println("Would you like to register a new account? (Y/N)");
		if (sc.nextLine().equalsIgnoreCase("n")) {
			System.out.println("You will be taken back to the login screen.\n");
			return null;
		}
		do {
			System.out.print("Please enter your first or personal name:");
			String first = sc.nextLine();
			System.out.println("Please enter your middle name. If you do not have a middle name, leave it blank.");
			System.out.print("Middle name: ");
			String middle = sc.nextLine();
			if (!middle.equals("")) {
				middle = middle.substring(0, 1);
			}
			System.out.print("Please enter your last or family name:");
			String last = sc.nextLine();

			boolean original = true;
			String email = "";
			do {
				System.out.print("Enter your email address: ");
				email = sc.nextLine();
				if (BankDriver.check(email, true)) {
					original = true;
				} else {
					original = false;
					System.out.println("Email address already taken.");
				}
			} while (!original);
			String un = "";
			do {
				System.out.print("Enter a username for your account:");
				un = sc.nextLine();
				if (BankDriver.check(un, false)) {
					original = true;
				} else {
					original = false;
					System.out.println("Username already taken.");
				}
			} while (!original);

			System.out.print("Enter a password for your account:");
			String pw = sc.nextLine();

			System.out.println("\n" + first + " " + middle + ((middle.length() == 1) ? ". " : " ") + last);
			System.out.println(email);
			System.out.println("username = " + un + ", password = " + pw);
			System.out.println("Does this look correct? (Y/N)");
			if (sc.nextLine().equalsIgnoreCase("y")) {
				String info[] = {first, middle, last, email, un, pw};
				return info;
			}
		} while (true);
	}

	/**
	 * Prompts the user to log in via the UI, asking for username & password.
	 * @return A String[] of length 2 containing the username and password, respectively.
	 */
	public String[] userLogIn() {
		String[] creds = new String[2];
		System.out.print("Please enter your username or email address: ");
		creds[0] = sc.nextLine();
		System.out.print("Please enter your password: ");
		creds[1] = sc.nextLine();

		return creds;
	}

	
	/**
	 * Prompts the user via the UI if they want to log out.
	 * @param user The reference to the current user.
	 * @return True if the user wants to log out, or false if they want to stay logged in.
	 */
	public boolean userLogOut(User user) {
		if (sc.hasNextLine()) sc.nextLine();
		System.out.println("Do you want to log out? (Y/N)");
		String temp = sc.nextLine();
		boolean log = temp.equalsIgnoreCase("y");
		if (log) {
			System.out.println("You have been sucessfully logged out.\n");
		} else {
			System.out.println("You have not been logged out, " + user.getFirstName() + ".\n");
		}
		return log;
	}

	public Account createAccount(User user) {
		System.out.println("Register for a new savings account.");
		System.out.println("Please enter a name for your account:");
		String name = "";
		do {
			name = sc.nextLine();
		} while (name.equals(""));
		System.out.println("Are you sure you want to name it \"" + name + "\"? (Y/N)");
		if (sc.nextLine().equalsIgnoreCase("y")) {
			Account a = new Account(user.getUserID(), name);
			System.out.println("Account created.\n");
			return a;
		} else {
			System.out.println("Account creaction canceled.");
			return null;
		}
		
	}
	
	
	/**
	 * Prompts the user to deposit money into their account.
	 * @param user The reference to the current user.
	 * @param currentBalance The current money balance in their account.
	 * @return a double[] of length two. 
	 * 		[0] = the account the user deposits into (-1 if invalid user). 
	 * 		[1] = the amount to deposit.
	 */
	public double[] depositMoney(User user, List<Account> accounts) {
		int accountNum = getAccountFromList(accounts, "deposit into?");
		double[] results = {-1.0, -1.0};
		if (accountNum == -1) {
			return results;
		}
		if (accountNum < accounts.size()) {
			results[0] = accountNum;
			sc.nextLine();
			Account a = accounts.get(accountNum);
			System.out.println("Account " + a.getAccountName() + " (id: " +a.getAccountID() + ")\nBalance: $" + a.getBalance());
			System.out.println("How much would you like to deposit?");
			System.out.print("$");
			double dep = -1;
			while (dep == -1) {
				try {
					String temp = sc.nextLine();
					if (temp.equals("")) return results;
					else dep = Double.parseDouble(temp);
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid dollar amount.");
				}
			}
			results[1] = Math.abs(dep);
			return results;
		} else {
			System.out.println("Deposit Canceled.");
			return results;
		}
	}

	/**
	 * Prompts the user to withdraw money from their account.
	 * @param user The reference to the current user.
	 * @return The amount they wish to withdraw, regardless of their actual balance. 
	 * Returns -1 if the transaction is canceled.
	 */
	public double[] withdrawMoney(User user, List<Account> accounts) {
		int accountNum = getAccountFromList(accounts, "withdraw from?");
		double results[] = {-1.0,-1.0};
		if (accountNum == -1) return results;
		if (accountNum < accounts.size()) {
			results[0] = accountNum;
			sc.nextLine();
			Account a = accounts.get(accountNum);
			System.out.println("Account: " + a.getAccountName() + " (id: " +a.getAccountID() + ")\nBalance: $" + a.getBalance());
			System.out.println("How much would you like to withdraw?");
			System.out.print("Withdraw $");
			while (true) {
				try {
					results[1] = Math.abs(Double.parseDouble(sc.nextLine()));
					return results;
				} catch (NumberFormatException e) {
					System.out.println("Please input a valid dollar amount.");
					System.out.println("You have $" + a.getBalance() + ".");
					System.out.print("Withdraw $");
				}
			}

		}
		return results;
	}

	private int getAccountFromList(List<Account> accounts, String function) {
		if (accounts.isEmpty()) {
			System.out.println("You do not have any accounts with Willbank.");
			return -1;
		}
		int accountNum = -1;
		if (accounts.size() > 1) {
			do {
				System.out.println("Which account would you like to " + function);
				for (int i = 0; i < accounts.size(); i++) {
					Account a = accounts.get(i);
					System.out.println((i+1) +") " + a.getAccountName() + " (id: " + a.getAccountID() + ")\nBalance: $" + a.getBalance() + "\n");
				}
				System.out.println((1 + accounts.size()) + ") Cancel " +function.split("\\W")[0] + ".");
				try {
					accountNum = (int) Math.abs(sc.nextInt()) - 1;
				} catch (NumberFormatException e) {
					System.out.println("Please input the number representing your choice.");
				}
			} while (accountNum == -1);
		} else {
			accountNum = 0;
		}
		return accountNum;
	}


	public boolean viewBalance(User user, List<Account> accounts) {
		if (user.getUserID() <= 0) {
			System.out.println("Invalid user.");
			return false;
		}
		System.out.println(user.getWholeName() + ", you have the following accounts: ");
		for (Account a : accounts) {
			System.out.println("\n" + a.getAccountName() + ", id: " +a.getAccountID());
			System.out.println("$" + a.getBalance());
		}
		System.out.println("");
		return true;
	}

	/**
	 * Prompts the user for their main choice in the application logic.
	 * @param user - the currently logged in user.
	 * @return the user's numeric choice.
	 */
	public int getMainChoice(User user) {
		System.out.println(user.getWholeName() + ", please choose an option:");
		System.out.println("Press 1 to create a new account.");
		System.out.println("Press 2 to deposit money.");
		System.out.println("Press 3 to view your balance.");
		System.out.println("Press 4 to withdraw money from your account.");
		System.out.println("Press 5 to transfer money between accounts.");
		System.out.println("Press 6 to log out of the Willbank system.");
		System.out.println("Press 7 to quit.");
		int choice = 0;
		int numberOfChoices = 7;
		try {
			choice = sc.nextInt();
			if (choice < 1 || choice > numberOfChoices) {
				System.out.println("Please enter a number between 1 and " + numberOfChoices + ".\n");
			}
		} catch (NumberFormatException e) {
			System.out.println("Please enter a number between 1 and  " + numberOfChoices + ".\n");
		}
		return choice;
	}

	public Account[] transferMoney(List<Account> accounts) {
		if (accounts.size() == 0) {
			System.out.println("You have no accounts.\n");
			return null;
		} else if (accounts.size() == 1) {
			System.out.println("You only have one account. You cannot transfer money.");
			return null;
		}
		System.out.println("Transferring money.");
		System.out.println("You have the following accounts:");
		for (int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			System.out.println((i+1) + ") " + a.getAccountName() + ", id: " +a.getAccountID());
			System.out.println("$" + a.getBalance());
		}
		System.out.println("\nWhich account to you want to withdraw from?");
		int accountNum1 = -1;
		do {
			try {
				accountNum1 = (int) Math.abs(sc.nextInt()) - 1;
				if (accountNum1 >= accounts.size()) {
					accountNum1 = -1;
					System.out.println("Please chose a valid choice.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please input the number representing the account you want to withdraw from.");
			}
		} while (accountNum1 == -1);
		System.out.println("\nWhich account to you want to transfer to?");
		int accountNum2 = -1;
		do {
			try {
				accountNum2 = (int) Math.abs(sc.nextInt()) - 1;
				if (accountNum2 >= accounts.size()) {
					accountNum2 = -1;
					System.out.println("Please chose a valid choice.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Please input the number representing the account you want to deposit to.");
			}
		} while (accountNum2 == -1);
		if (accountNum1 == accountNum2) {
			System.out.println("You can't transfer money from one account to itself.");
			return null;
		}
		
		Account from = accounts.get(accountNum1);
		Account to = accounts.get(accountNum2);
		
		System.out.println("Transfer from account " + from.getAccountName() + " (id: " + from.getAccountID() +")");
		System.out.println("Current balance: $" + from.getBalance());
		System.out.println("How much do you want to transfer?");
		System.out.print("$");	
		double transfer = -1;
		do {
			try {
				transfer = Math.abs(sc.nextDouble());
			} catch (NumberFormatException e) {
				System.out.println("Please input a valid dollar amount.");
			}
		} while (transfer == -1);
		if (from.withdraw(transfer)) {
			to.deposit(transfer);
			System.out.println("$" + transfer + " transfered.");
			System.out.println("Account " + from.getAccountName() + " (id: " + from.getAccountID() +")");
			System.out.println("New balance: $" + from.getBalance());
			System.out.println("Account " + to.getAccountName() + " (id: " + to.getAccountID() +")");
			System.out.println("New balance: $" + to.getBalance() + "\n");
			Account[] changedAccounts = new Account[2];
			changedAccounts[0] = from;
			changedAccounts[1] = to;
			return changedAccounts;
		} else {
			System.out.println("You do not have enough funds.\n");
			return null;
		}
	}



}

