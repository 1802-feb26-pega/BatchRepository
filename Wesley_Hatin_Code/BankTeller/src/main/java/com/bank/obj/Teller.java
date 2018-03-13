package com.bank.obj;

import java.util.List;

import com.bank.dao.BankDatabaseImpl;

public class Teller {
	
	private String username;
	private String password="";
	private String firstname;
	private String lastname;
	private BankDatabaseImpl db=new BankDatabaseImpl();
	
	// create a new user entry with a unique username, returns true if successful
	public boolean createUser(String newUser, String newPass, String newFirst, String newLast) {
		if(!db.userValidation(newUser)) {
			db.writeNewUser(newUser, newPass, newFirst, newLast);
			username = newUser;
			password = newPass;
			firstname = newFirst;
			lastname = newLast;
			return true;
		}
		else {
			return false;
		}
	}
	
	// create a new account under a username, returns true if successful
	public boolean createAccount(String username, String newAccount, boolean firstAccount) {
		if(firstAccount) {
			db.writeNewAccount(username, newAccount);
			return false;
		}
		else {
			if(!db.accountValidation(username, newAccount)) {
				db.writeNewAccount(username, newAccount);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	// log in 
	public boolean logIn(String username, String password) {
		if(db.passwordValidation(username, password)) {
				this.username=username;
				this.password=password;
				String[] names = db.retrieveName(username);
				this.firstname=names[0];
				this.lastname=names[1];
				return true;
		}
		return false;
	}
	
	//log out
	public void logOut() {
		this.username=null;
		this.password=null;
		this.firstname=null;
		this.lastname=null;
	}
	
	//deposit money
	public void deposit(Account account, double amount) {
		db.writeBalance(username, account, account.getBalance() + amount);
	}
	
	//withdraw money
	public boolean withdraw(Account account, double amount) {
		if(account.getBalance() < amount) {
			return false;
		}
		else {
			db.writeBalance(username, account, account.getBalance() - amount);
			return true;
		}
	}

	public boolean transfer(Account fromAccount, Account toAccount, Double amount) {
		if(fromAccount.getBalance()>amount) {
			db.writeBalance(username, fromAccount, fromAccount.getBalance() - amount);
			db.writeBalance(username, toAccount, toAccount.getBalance() + amount);
			return true;
		}
		else {
			return false;
		}
		
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	public List<Account> getAllAccounts() {
		return db.retrieveAccounts(username);
	}
	
	public Double getBalance(Account accountName) {
		List <Account> accountList = db.retrieveAccounts(username);
		
		for(Account account : accountList) {
			if(account.equals(accountName)) {
				return account.getBalance();
			}
		}
		
		return null;
		
	}

}
