package com.bank.obj;

import com.bank.dao.BankDatabaseImpl;

public class Teller {
	
	private String username;
	private String password="";
	private BankDatabaseImpl db=new BankDatabaseImpl();
	
	// create an account with a unique email or username
	public boolean createUser(String newUser, String newPass) {
		if(!db.userValidation(newUser)) {
			db.writeNewClient(newUser, newPass);
			username = newUser;
			password = newPass;
			return true;
		}
		else {
			return false;
		}
	}
	
	// log in 
	public boolean logIn(String username, String password) {
		if(db.userValidation(username, password)) {
				this.username=username;
				this.password=password;
				return true;
		}
		return false;
	}
	
	//log out
	public void logOut() {
		this.username=null;
		this.password=null;
	}
	
	//deposit money
	public void deposit(Account account, double amount) {
		db.writeBalance(username, account.getAccountName(), account.getBalance() + amount);
	}
	
	//withdraw money
	public boolean withdraw(Account account, double amount) {
		if(account.getBalance() < 0) {
			return false;
		}
		else {
			db.writeBalance(username, account.getAccountName(), account.getBalance()-amount);
			return true;
		}
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Double getBalance(String accountName) {
		Account account = db.retrieveAccountInfo(username, accountName);
		return account.getBalance();
	}

	public Account getAccount(String valueOf) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean transfer(Double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
