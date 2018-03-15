package com.revature.bankapp.pojos;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

import com.revature.bankapp.dao.AccountDAOImpl;
import com.revature.bankapp.dao.UserDAOImpl;

//The Bank class contains the logic necessary for the interactions a user can have with the bank

public class Bank{

	private boolean loggedIn;
	private User currentUser;
	private ArrayList<Account> currentUserAccounts;
	private AccountDAOImpl accountDAO;
	private UserDAOImpl userDAO;
	
	public Bank() {
		currentUserAccounts = new ArrayList<>();
		accountDAO = new AccountDAOImpl();
		userDAO = new UserDAOImpl();
	}
	
	public boolean openBank() {
		return true;
		
	}
	
	public boolean closeBank() {
		
		return false;
	}

	public boolean login(String email, String password) {
		
		User temp = userDAO.getUserByEmail(email);
		
		if(temp.getUserId() == -1) {
			return false;
		}
		
		if(temp.getPassword().equals(password)) {
			currentUser = temp;
			loggedIn = true;
			return true;
		}	
		
		return false;
	}
	
	public boolean logout() {
		loggedIn = false;
		currentUser = null;
		currentUserAccounts = null;
		return true;
	}
	
	public boolean register(String firstName, String lastName, String email, String password) {
			
		User temp = userDAO.getUserByEmail(email);
		
		if(temp.getUserId() != -1) {				
			return false;
		}

		User newUser = new User(firstName, lastName, email, password);	
		userDAO.addUser(newUser);
		return true;		
	}
	
	public Account createAccount() {
		Account newAccount = new Account(currentUser.getUserId());
		newAccount = accountDAO.addAccount(newAccount);
		return newAccount;
	}
	
	public int withdraw(int accountId, double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return -1;
		}
		
		Account account = accountDAO.getAccountByIds(accountId, currentUser.getUserId());
		
		double balance = account.getBalance();
		if(account.getAccId() == 0){
			return 1;
		}
		else if(balance < amount) {
			
			System.out.println("not enough funds");
			return 2;
		}
		else {
			account.setBalance(balance - amount);
			accountDAO.updateAccount(account);
			return 3;
		}	
	}
	
	public boolean deposit(int accountId, double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		Account account = accountDAO.getAccountByIds(accountId, currentUser.getUserId());
		
		if(account.getAccId() == 0) {
			return false;
		}
		
		account.setBalance(account.getBalance() + amount);
		accountDAO.updateAccount(account);
		return true;
	}
	
	public int transfer(int accountIdFrom, int accountIdTo, double amount) {
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return -1;
		}
		
		Account accountFrom = accountDAO.getAccountByIds(accountIdFrom, currentUser.getUserId());
		Account accountTo = accountDAO.getAccountByIds(accountIdTo, currentUser.getUserId());
		
		if(accountFrom.getAccId() == 0 || accountTo.getAccId() == 0 || accountFrom.getAccId() == accountTo.getAccId()){
			return 1;
		}
		else if(accountFrom.getBalance() < amount) {
			return 2;
		}
		
		accountFrom.setBalance(accountFrom.getBalance() - amount);
		accountTo.setBalance(accountTo.getBalance() + amount);
		
		accountDAO.updateAccount(accountFrom);
		accountDAO.updateAccount(accountTo);

		return 3;
		
	}
	
	public String viewBalance(int accountId) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(accountDAO.getAccountByIds(accountId, currentUser.getUserId()).getBalance());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	public String viewTotalBalanceOfAllAccounts() {
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(accountDAO.getAccountsTotal(currentUser.getUserId()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
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

	public ArrayList<Account> getCurrentUserAccounts() {
		currentUserAccounts = (ArrayList<Account>) accountDAO.getAllAccounts(currentUser.getUserId());
		return currentUserAccounts;
	}

	
	
	
	
	
}
