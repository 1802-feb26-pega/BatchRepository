package com.revature.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserAccountDAO;
import com.revature.bank.dao.UserAccountDAOImpl;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;
import com.revature.bank.util.ConsoleConnectionFactory;

public class MainMenu {

	public void mainMenu(User user) {
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		MainMenu mm = new MainMenu();
		System.out.println("\nTo create an account, enter 1.");
		System.out.println("To see available accounts, enter 2.");
		System.out.println("To transfer funds between accounts, enter 3.");
		System.out.println("To view your total balance across accounts, enter 4.");
		System.out.println("To log out, enter 5.");
		int option = 0;
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input; try again.\n");
			mm.mainMenu(user);
		}
		
		if(option == 1) {
			mm.createAccount(user);
		} else if(option == 2) {
			mm.listAccounts(user);
		} else if(option == 3) {
			mm.transferFunds(user);
		} else if(option == 4) {
			mm.sumTotal(user);
		} else if(option == 5) {
			System.out.println("\nLogged out.");
		}
		
		else {
			System.out.println("Invalid input; try again.\n");
			mm.mainMenu(user);
		}
	}

	private void sumTotal(User user) {
		MainMenu mm = new MainMenu();
		UserDAO userDao = new UserDAOImpl();
		double total = userDao.getTotalBalance(user);
		System.out.println("Your total balance is: $" + total);
		mm.mainMenu(user);
	}

	private void createAccount(User user) {
		MainMenu mm = new MainMenu();
		int option = 0;
		System.out.println("\nPlease specify what type of account you would like.");
		AccountDAO accountDao = new AccountDAOImpl();
		Map<Integer, String> accountTypes = accountDao.getAccountTypes();
		for (Integer key: accountTypes.keySet()) {
			System.out.println("For an account of type " + accountTypes.get(key) + ", enter " + key + ".");
		}
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
		String value = accountTypes.get(option);
		if (value != null) {
			Account newAccount = new Account();
			newAccount.setAccountType(accountTypes.get(option));
			newAccount.setBalance(0.0);
			newAccount = accountDao.addAccount(newAccount);
			
			UserAccountDAO uaDao = new UserAccountDAOImpl();
			uaDao.addUserAccount(user, newAccount);
			System.out.println("Your account has been created.\n");
			mm.mainMenu(user);
		} else {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
	}

	private void listAccounts(User user) {
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		MainMenu mm = new MainMenu();
		int option = 0;
		UserAccountDAO uaDao = new UserAccountDAOImpl();
		List<Integer> accountids = uaDao.getAccountIDByUserID(user.getUserId());
		AccountDAO accountDao = new AccountDAOImpl();
		List<Account> accounts = new ArrayList<>();
		int counter = 1;
		System.out.print("\n");
		for(Integer accountid : accountids) {
			Account temp = new Account();
			temp = accountDao.getAccountById(accountid);
			accounts.add(temp);
			System.out.println("To access your " + temp.getAccountType() + "-" + temp.getAccountId() +
							   " account, enter " + counter++ + ".");
		}
		
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
		
		mm.manipulateAccount(user, accounts.get(option-1));
	}

	private void manipulateAccount(User user, Account account) {
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		MainMenu mm = new MainMenu();
		AccountDAO accountDao = new AccountDAOImpl();
		int option = 0;
		System.out.println("\nTo view this account's balance, enter 1.");
		System.out.println("To deposit money, enter 2.");
		System.out.println("To withdraw money, enter 3.");
		System.out.println("To authorize another user to access this account, enter 4.");
		System.out.println("To go back to the main menu, enter 5.");
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.manipulateAccount(user, account);
		}
		
		if(option == 1) {
			System.out.println("The balance on account " + account.getAccountType() + "-" + account.getAccountId() +
								" is:  $" + account.getBalance());
			mm.manipulateAccount(user, account);
		} else if(option == 2) {
			System.out.println("How much money (in dollars) would you like to deposit?");
			double deposit = 0D;
			try {
				deposit = Double.parseDouble(scan.nextLine());
				if(deposit >= 0) {
					account.setBalance(account.getBalance() + deposit);
					System.out.println("Transaction successful.");
					accountDao.updateAccount(account.getAccountId(), account);
					mm.mainMenu(user);
				}
			} catch(NumberFormatException ime) {
				System.out.println("Invalid input.\n");
				mm.manipulateAccount(user, account);
			}
		} else if(option == 3) {
			System.out.println("How much money (in dollars) would you like to withdraw?");
			double withdrawal = 0D;
			boolean success = false;
			try {
				withdrawal = Double.parseDouble(scan.nextLine());
				if(withdrawal >= 0) {
					success = account.setBalance(account.getBalance() - withdrawal);
				}
			} catch(NumberFormatException ime) {
				System.out.println("Invalid input.\n");
				mm.manipulateAccount(user, account);
			}
			
			if(success == true) {
				System.out.println("Transaction successful.");
				accountDao.updateAccount(account.getAccountId(), account);
				mm.mainMenu(user);
			} else {
				System.out.println("Transaction unsuccessful.");
				mm.mainMenu(user);
			}
			
		} else if(option == 4) {
			mm.authorizeUser(user, account);
			mm.manipulateAccount(user, account);
		} else if(option == 5) {
			mm.mainMenu(user);
		} else {
			System.out.println("Invalid input.\n");
			mm.manipulateAccount(user, account);
		}
		
	}

	private void transferFunds(User user) {
		System.out.println("From which account would you like to transfer funds?");
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		MainMenu mm = new MainMenu();
		int option = 0;
		UserAccountDAO uaDao = new UserAccountDAOImpl();
		List<Integer> accountids = uaDao.getAccountIDByUserID(user.getUserId());
		AccountDAO accountDao = new AccountDAOImpl();
		List<Account> accounts = new ArrayList<>();
		int counter = 1;
		System.out.print("\n");
		for(Integer accountid : accountids) {
			Account temp = new Account();
			temp = accountDao.getAccountById(accountid);
			accounts.add(temp);
			System.out.println("To select your " + temp.getAccountType() + "-" + temp.getAccountId() +
							   " account, enter " + counter++ + ".");
		}
		
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
		Account fromAccount = accounts.get(option-1);
		System.out.println("To which account would you like to transfer funds?");
		List<Account> dummyList = accounts;
		dummyList.remove(option-1);
		counter = 1;
		for(Account dummyAccount : dummyList) {
			System.out.println("To select your " + dummyAccount.getAccountType() + "-" + dummyAccount.getAccountType() +
								" account, enter " + counter++ + ".");
		}
		
		try{
			option = Integer.parseInt(scan.nextLine());
		} catch (NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
		
		Account toAccount = accounts.get(option-1);
		System.out.println("How much money (in dollars) would you like to transfer?");
		double transfer = -1D;
		boolean success = false;
		try {
			transfer = Double.parseDouble(scan.nextLine());
			if(transfer >= 0) {
				success = fromAccount.setBalance(fromAccount.getBalance() - transfer);
			}
		} catch(NumberFormatException ime) {
			System.out.println("Invalid input.\n");
			mm.mainMenu(user);
		}
		
		if(success == true) {
			toAccount.setBalance(toAccount.getBalance() + transfer);
			accountDao.updateAccount(fromAccount.getAccountId(), fromAccount);
			accountDao.updateAccount(toAccount.getAccountId(), toAccount);
			System.out.println("Transaction successful.");
			mm.mainMenu(user);
		} else {
			System.out.println("Transaction unsuccessful.");
			mm.mainMenu(user);
		}
		
	}
	
	private void authorizeUser(User user, Account account) {
		MainMenu mm = new MainMenu();
		Scanner scan = ConsoleConnectionFactory.getInstance().getConnection();
		System.out.println("Specify the username of the person you would like to authorize.");
		boolean exists = false;
		String un = scan.nextLine();
		exists = mm.verifyUsername(un);
		if(exists == false) {
			System.out.println("There is not another user with that username.");
			mm.mainMenu(user);
		} else {
			UserDAO userDao = new UserDAOImpl();
			UserAccountDAO uaDao = new UserAccountDAOImpl();
			User newAuth = new User();
			newAuth = userDao.getUserByUsername(un);
			int successval = uaDao.addUserAccount(newAuth, account);
			if(successval > 0) {
				System.out.println("Authorization has been granted.\n");
			}
		}
		
	}

	private boolean verifyUsername(String un) {	
		UserDAO userDao = new UserDAOImpl();
		User login = userDao.getUserByUsername(un);
		if(login.getUserId() > 0) {
			return true;
		}
		return false;
	}

}
