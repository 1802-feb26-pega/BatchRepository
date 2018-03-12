package com.bank.obj;

public class Account {
	private String username;
	private String firstName;
	private String lastName;
	private String accountName;
	private double balance = 0.0;
	
	public Account() {
		super();
	}
	
	public Account(String username, String firstName, String lastName, String accountName, double balance) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountName = accountName;
		this.balance = balance;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
