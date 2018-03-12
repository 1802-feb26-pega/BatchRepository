package com.revature.banking.pojos;

import java.util.List;
import java.util.Random;

import com.revature.banking.dao.DaoImpl;

public class Account {

	private int type;
	private int id;
	private int balance;
	private List<String> usrs;
	private long accountNumber;
	static DaoImpl dao = new DaoImpl();



	//Set up account constructor
	public Account(int type, int balance, List<String> usrs) {
		super();
		this.type = type;
		this.balance = balance;
		this.accountNumber = account_Gen();
		
		
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
	
	
	//Method that withdraws the balance and checks to see if account
	//would go negative. If negative throws back false and warns user
	public boolean withdraw(int w) {
		int temp = balance - w;
		if(temp > 0) {
			balance -= w;			
			return dao.updateBalance(this);
		}
		return false;
	}
	
	//Method that deposits money into account
	public boolean deposit(int d) {
		// TODO Auto-generated method stub
		balance += d;
		 return dao.updateBalance(this);
	}
	
	//Method that generates an Account number that is 9 digits long
	public static Long account_Gen() {
		Random r = new Random();
		Long Low = 100000000L;
		Long High = 999999999L;
		Long Result = r.nextInt((int)(High-Low)) + Low;
		return Result;
	}	
	
	//Creates an account with users input
	public boolean createAccount(Account a,int amount, int typeof) {
		// TODO Auto-generated method stub
		
		a.type = typeof;
		a.balance = amount;
		a.accountNumber = account_Gen();
		
		if(dao.createAcc(this)) return true;
			return false;
	}
	
	//Return list of accounts that current user has
	public static List<Account> getAcc(){
		return dao.getAccounts();
	}
	
	//Overrides toString method for custom output
	@Override
	public String toString() {
		return  "[Account Number: " + accountNumber + "  ] [Balance: " + balance +" ] [Account Type: " + type + "]";
	}

	//Method that transfers money from one account to another.
	public static boolean transfer(Account debit, Account credit,int m) {
		// TODO Auto-generated method stub
			debit.balance -= m;
			credit.balance += m;
			if(debit.balance >= 0) {
				if( dao.updateBalance(debit) & dao.updateBalance(credit)) return true;
				else return false;
			}
		return false;
	}

}