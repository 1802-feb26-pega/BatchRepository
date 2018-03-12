package com.revature.bank.pojo;

public class Account {
	
	private int accountID;
	private int userID;
	private String accountName;
	private double balance;
	
////////////////////
////Constructors////
////////////////////
	
	public Account(String name, int userID) {
		
		this.accountName = name;
		this.userID = userID;
		this.balance = 0.0;
	}//constructor
	
	public Account(String name, int userID, double startingBalance) {
		
		this.accountName = name;
		this.userID = userID;
		this.balance = startingBalance;
	}//constructor
	
	public Account(int id, int userID, String name, double startingBalance) {
		
		this.accountID = id;
		this.userID = userID;
		this.accountName = name;
		this.balance = startingBalance;
	}//constructor

//////////////////////////////////
////getters, setters, toString////
//////////////////////////////////
	
	
	public int getAccountID() {
		
		return accountID;
	}

	public void setAccountID(int accountID) {
		
		this.accountID = accountID;
	}
	
	public int getUserID() {
		return userID;
	}

	public String getAccountName() {
		
		return accountName;
	}

	public double getBalance() {
		
		return balance;
	}

	public void setBalance(double balance) {
		
		this.balance = balance;
	}

	/*
	 * returns string with accountName and balance.  
	 * accountID is omitted
	 */
	@Override
	public String toString() {
		
		return accountName + " balance : " + balance;
	}

}//class
