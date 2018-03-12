package com.bank.obj;

public class Account {
	private String username;
	private String accountName;
	private double balance = 0.0;
	
	public Account() {
		super();
	}
	
	public Account(String username, String accountName, double balance) {
		super();
		this.username = username;
		this.accountName = accountName;
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public boolean equals(Object o) { 
		if (o == this) {
			return true;
		}       
		if (!(o instanceof Account)) {
			return false;
		}
		Account a = (Account) o;
		return (username.equals(a.username) && accountName.equals(a.accountName));
	}

}
