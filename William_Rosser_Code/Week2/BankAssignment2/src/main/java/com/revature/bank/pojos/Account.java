package com.revature.bank.pojos;

public class Account {
	private final int userID;
	private final int accountID;
	private double balance;
	private String accountName;
	
	public Account(int userID, int accountID, double balance, String accountName) {
		super();
		this.userID = userID;
		this.accountID = accountID;
		this.balance = balance;
		this.accountName = accountName;
	}

	public Account(int userID, double balance, String accountName) {
		this(userID, -1, balance, accountName);
	}
	
	public Account(int userID, String accountName) {
		this(userID, 0.0, accountName);
	}

	public Account(int userID) {
		this(userID, "Savings Account");
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean deposit(double amount) {
		if (amount < 0) return false;
		this.balance += amount;
		return true;
	}
	
	public boolean withdraw(double amount) {
		if ((this.balance - amount) < 0 || amount <= 0) return false;
		this.balance -= amount;
		return true;
	}
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getUserID() {
		return userID;
	}

	public int getAccountID() {
		return accountID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountID != other.accountID)
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [userID=" + userID + ", accountID=" + accountID + ", balance=" + balance + ", accountName="
				+ accountName + "]";
	}
	
}
