package com.revature.bankapp.pojos;

public class Account {

	private int accId;
	private int userId;
	private double balance;
	
	public Account() {}
	
	public Account(int userId) {
		super();
		this.accId = -1;
		this.userId = userId;
		this.balance = 0.0;
	}

	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
	
}
