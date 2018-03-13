package com.revature.UI;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.pojo.Account;
import com.revature.pojo.User;


public class BankApp {

	Scanner in = new Scanner(System.in);

	AccountDAO accountDao = new AccountDAOImpl();

	public void bankAccount(User user) {	

		// Obtain all accounts associated with the user
		List<Account> accounts = accountDao.getUserAccounts(user.getUserId());
		Account account = accounts.get(0);

		System.out.println("Welcome back " + user.getFirstname());

		boolean loop = true;

		do {
			System.out.println("------------------------------------------------");
			System.out.println("Press 1: Deposit");
			System.out.println("Press 2: Withdraw");
			System.out.println("Press 3: View balance");
			System.out.println("Press 4: Transfer money to another account");
			System.out.println("Press 5: Switch accounts");
			System.out.println("Press 6: Create new account");
			System.out.println("Press 0: Log out");

			int option = in.nextInt();

			switch(option) {

			case 0:
				loop = false;
				break;

			case 1:
				deposit(account);
				break;

			case 2:
				withdraw(account);
				break;

			case 3:
				System.out.println("Your balance is $" + account.getBalance());
				break;

			case 4:
				if (accounts.size() < 2) {
					System.out.println("Create another account before transfering");
					break;
				}
				transfer(account, accounts);
				break;

			case 5:
				if (accounts.size() < 2) {
					System.out.println("Create another account before switching accounts");
					break;
				}
				account = switchAccounts(accounts);
				break;

			case 6:
				createNewAccount(user.getUserId());
				accounts = accountDao.getUserAccounts(user.getUserId());
				break;

			default:
				System.out.println("Error! Invalid input");
				break;		
			} 	
			
		} while (loop);
	}

	public void deposit(Account account) {

		System.out.println("How much money do you want to deposit?");

		int amount = in.nextInt();
		int prev = account.getBalance();
		account.setBalance((prev + amount));
		
		accountDao.updateAccount(account);
	}

	public void withdraw(Account account) {

		System.out.println("How much money do you want to withdraw?");

		int amount = in.nextInt();
		int prev = account.getBalance();
		int after = prev - amount;

		if (after < 0) {
			System.out.println("Sorry you do not have enough money in your account.");
			System.out.println("Try again with a lower amount.");
		} else {
			account.setBalance(after);
			accountDao.updateAccount(account);
		}
	}

	public void transfer(Account account, List<Account> accounts) {

		System.out.println("Select account to transfer to:\n");

		Account otherAccount = switchAccounts(accounts);

		if (account.getAccountId() == (otherAccount.getAccountId())) {
			System.out.println("Error! Cannot transfer money to same account.");
			
		} else {
			System.out.println("How much money do you want to tranfer?");

			int amount = in.nextInt();
			int prev = account.getBalance();
			int after = prev - amount;

			if (after < 0) {
				System.out.println("Sorry you do not have enough money in your account.");
				System.out.println("Try again with a lower amount");
				
			} else {
				account.setBalance(after);
				accountDao.updateAccount(account);
				
				prev = otherAccount.getBalance();
				after = amount + prev;
				otherAccount.setBalance(after);
				accountDao.updateAccount(otherAccount);
			}
		}

	}

	public Account switchAccounts(List<Account> accounts) {

		Account account = accounts.get(0);

		int size = accounts.size();
		
		System.out.println("You have " + size + " seperate accounts");
		System.out.println("Current account has a balance of $" + accounts.get(0).getBalance());
		System.out.println("Press 1: Switch to next account");
		System.out.println("Press 2: Select account");

		int index = 1;
		boolean keepLooping = true;

		do {
			
			int option = in.nextInt();

			switch (option) {
			case 1:
				// Loop through accounts, reset position when it reaches the end
				if (size == index) 
					index = 0;
				
				account = accounts.get(index);
				System.out.println("This account has $" + accounts.get(index).getBalance());
				index++;
				break;

			case 2:
				keepLooping = false;
				break;

			default:
				System.out.println("Error! Invalid input");

			}

		} while (keepLooping);

		return account;
	}

	public void createNewAccount(int userId) {

		System.out.println("Creating a new account");
		Account account = new Account(0, userId);
		accountDao.addAccount(account);
		System.out.println("New account has been created");
	}
}