package com.revature.projectI;

import java.util.List;

public class Account {
	private String type;
	private int balance;
	private List<String> usrs;
	private long accountNumber;
	Client customer = new Client();
	static DataPersistency db = new DataPersistency();
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int getBalance() {
		return balance;
	}

	public void checkBal() {
		System.out.println( "You have " + balance + " remaining");
	}
	public void withdraw(int w,Client c) {
		balance -= w;
		System.out.println("New Balannce is: " + balance);
		db.updateBalance(this,c);
	}
	public void access() {
		System.out.println("The follwing people have access to this account: ");
		for(String x: usrs)
			System.out.println(x);
	}
	public void addUser() {
		//Add users here
	}
	private void removeUsr() {
		//Remove users
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
