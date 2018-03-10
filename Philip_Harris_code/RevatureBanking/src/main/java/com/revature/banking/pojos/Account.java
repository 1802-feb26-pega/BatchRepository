package com.revature.banking.pojos;

import java.util.List;
import java.util.Random;

import com.revature.banking.dao.Account_Dao;
import com.revature.banking.dao.Client_Dao;

public class Account {

	private int type;
	private int id;
	private int balance;
	private List<String> usrs;
	private long accountNumber;




	public Account(int type, int balance, List<String> usrs) {
		super();
		this.type = type;
		this.balance = balance;
		this.usrs = usrs;
		this.accountNumber = account_Gen();
		
		
	}


	

	
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}






	public long getAccountNumber() {
		return accountNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getUsrs() {
		return usrs;
	}
	public void setUsrs(List<String> usrs) {
		this.usrs = usrs;
	}
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBalance() {
		return balance;

	}
	public boolean withdraw(int w,Client c) {
		int temp = balance - w;
		if(temp > 0) {
			balance -= w;			
			db.updateBalance(this,c);
		}
		return false;
	}
	public void access() {
		System.out.println("The follwing people have access to this account: ");//MOVE TO MAIN ONCE IMPLMENTED
		for(String x: usrs)
			System.out.println(x);
	}
	public void addUser() {
		//Add users here
	}
	private void removeUsr() {
		//Remove users
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public void deposit(int d, Client customer) {
		// TODO Auto-generated method stub
		balance += d;
		db.updateBalance(this,customer);
	}


	private static Long account_Gen() {
		Random r = new Random();
		Long Low = 100000000L;
		Long High = 999999999L;
		Long Result = r.nextInt((int)(High-Low)) + Low;
		return Result;
	}

}