package com.ex.pojos;

// This class is not used now, but will be used in the future.
public class Account {
	private int accNumber;
	private String type;
	private String userName;
	private double balance;
	
	public Account() {}
	
	public Account(int accNumber, String type, String userName, double balance) {
		super();
		this.accNumber = accNumber;
		this.type = type;
		this.userName = userName;
		this.balance = balance;
	}

}
