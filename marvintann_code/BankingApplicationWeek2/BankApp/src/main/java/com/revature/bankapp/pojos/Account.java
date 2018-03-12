package com.revature.bankapp.pojos;

import java.util.ArrayList;

import com.revature.bankapp.dao.AccountDAOImplement;

public class Account {
	
	private int account_id;
	private int user_id;
	private double balance;
	
	public Account() {}
	
	public Account(int account_id, int user_id, int balance) {
		super();
		this.account_id = account_id;
		this.user_id = user_id;
		this.balance = balance;
	}
	
	public void update_account () {
		
	}
	
	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double d) {
		this.balance = d;
	}

	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", user_id=" + user_id + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		double result = 1;
		result = prime * result + account_id;
		result = prime * result + balance;
		result = prime * result + user_id;
		return (int) result;
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
		if (account_id != other.account_id)
			return false;
		if (balance != other.balance)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}
	
	

}
