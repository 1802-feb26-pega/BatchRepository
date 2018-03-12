package com.revature.bank.pojos;

import java.util.List;

public class Account {
	private int accountId;
	private double balance;
	private String accountType;
	private List<User> users;
	
	public Account() { }
	
	public Account(int accountId, double balance, String accountType, List<User> users) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.accountType = accountType;
		this.users = users;
	}
	public double getBalance() {
		return balance;
	}
	public boolean setBalance(double balance) {
		if(balance < 0) {
			return false;
		} else {
			this.balance = balance;
			return true;
		}
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", accountType=" + accountType + "]";
	}
}
