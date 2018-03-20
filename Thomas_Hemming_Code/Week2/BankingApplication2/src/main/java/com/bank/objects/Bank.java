package com.bank.objects;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

import com.bank.dao.AccountDAOImpl;
import com.bank.dao.UserDAOImpl;

public class Bank{

	private boolean loggedIn;
	private User currentUser;
	private ArrayList<Account> currentUserAccounts;
	private AccountDAOImpl accDAO;
	private UserDAOImpl usrDAO;
	
	public Bank() {
		currentUserAccounts = new ArrayList<>();
		accDAO = new AccountDAOImpl();
		usrDAO = new UserDAOImpl();
	}
	
	public boolean startBank() {
		return true;		
	}
	
	public boolean stopBank() {	
		return false;
	}

	public boolean login(String email, String password) {
		
		User temp = usrDAO.getUserByEmail(email);
		
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
			
		User temp = usrDAO.getUserByEmail(email);
		
		if(temp.getUserId() != -1) {				
			return false;
		}

		User newUser = new User(firstName, lastName, email, password);	
		usrDAO.addUser(newUser);
		return true;		
	}
	
	public Account createAccount() {
		Account newAccount = new Account(currentUser.getUserId());
		newAccount = accDAO.addAccount(newAccount);
		return newAccount;
	}
	
	public int withdraw(int accountId, double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return -1;
		}
		
		Account account = accDAO.getAccountByIds(accountId, currentUser.getUserId());
		
		if(account.getAccId() == 0){
			return 1; //don't have access to this account
		}
		else if(account.getBalance() < amount) {
			return 2; //insufficient funds
		} else {
			//System.out.println("Before: " + account.getBalance());
			account.setBalance(account.getBalance() - amount);
			//System.out.println("After: " + account.getBalance());
			accDAO.updateAccount(account);
			return 3; //success
		}	
	}
	
	public boolean deposit(int accountId, double amount) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return false;
		}
		
		Account account = accDAO.getAccountByIds(accountId, currentUser.getUserId());
		
		if(account.getAccId() == 0) {
			return false; //don't have access to this account
		}
		
		account.setBalance(account.getBalance() + amount);
		accDAO.updateAccount(account);
		return true; //success
	}
	
	public int transfer(int accountIdFrom, int accountIdTo, double amount) {
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return -1;
		}
		
		Account accountFrom = accDAO.getAccountByIds(accountIdFrom, currentUser.getUserId());
		Account accountTo = accDAO.getAccountByIds(accountIdTo, currentUser.getUserId());
		
		if(accountFrom.getAccId() == 0 || accountTo.getAccId() == 0){
			return 1; //don't have access to this account
		} else if (accountFrom.getAccId() == accountTo.getAccId()) {
			return 4; //can't transfer to same account
		} else if(accountFrom.getBalance() < amount) {
			return 2; //insufficient funds
		}
		
		accountFrom.setBalance(accountFrom.getBalance() - amount);
		accountTo.setBalance(accountTo.getBalance() + amount);
		
		accDAO.updateAccount(accountFrom);
		accDAO.updateAccount(accountTo);

		return 3; //successful
		
	}
	
	public String getBalance(int accountId) {
		
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(accDAO.getAccountByIds(accountId, currentUser.getUserId()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	public String getTotalBalance() {
		if(!loggedIn) {
			System.out.println("Not logged in.");
			return null;
		}
		
		String output = null;
		
		NumberFormatter nf = new NumberFormatter(NumberFormat.getCurrencyInstance(Locale.US));
		
		try {
			output = nf.valueToString(accDAO.getAccountsTotal(currentUser.getUserId()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	


	public boolean isLoggedIn() {
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
		currentUserAccounts = (ArrayList<Account>) accDAO.getAllAccounts(currentUser.getUserId());
		return currentUserAccounts;
	}
}
