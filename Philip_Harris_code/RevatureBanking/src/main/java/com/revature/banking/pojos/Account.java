package com.revature.banking.pojos;

import java.util.List;
import java.util.Random;

import com.revature.banking.dao.DaoImpl;
import com.revature.banking.util.AccountServiceLayer;

public class Account {

	private int type;
	private int id;
	private int balance;
	private long accountNumber;
	private String typeString;




	//Set up account constructor
	public Account(int type, int balance, List<String> usrs) {
		super();
		this.type = type;
		this.balance = balance;
		this.accountNumber = AccountServiceLayer.account_Gen();


	}

	//Default Constructor
	public Account() {
		// TODO Auto-generated constructor stub
	}

	//Getters and Setters
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
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return balance;

	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}	
	
	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	//Custom output
	//[Account #] [Balance] [Type of account]
	public String toString() {
		return  "[Account Number: " + accountNumber + "  ] [Balance: " + balance +" ] [Account Type: " + typeString + "]";
	}
	
	//Custom output
	//Shows only the account numbers
	public String showAccountNumber() {
		return "[Account Number: " + accountNumber + "  ] ";
	}

}