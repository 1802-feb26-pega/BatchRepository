package com.revature.pojo;

public class Account {
	
	private int accountId;
	private int balance;
	private int userId;
	
	public Account() {}
	
	public Account(int accountId, int balance, int userId) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
	}
	
	public Account(int balance, int userId) {
		this.balance = balance;
		this.userId = userId;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}	

}
