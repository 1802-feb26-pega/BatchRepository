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
	public boolean withdraw(int w) {
		int temp = balance - w;
		if(temp > 0) {
			balance -= w;			
			return DaoImpl.updateBalance(this);
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

	public boolean deposit(int d) {
		// TODO Auto-generated method stub
		balance += d;
		 return DaoImpl.updateBalance(this);
	}


	private static Long account_Gen() {
		Random r = new Random();
		Long Low = 100000000L;
		Long High = 999999999L;
		Long Result = r.nextInt((int)(High-Low)) + Low;
		return Result;
	}
	
	public boolean createAccount(Account a,int amount, int typeof) {
		// TODO Auto-generated method stub
		
		a.type = typeof;
		a.balance = amount;
		a.accountNumber = account_Gen();
		
		if(DaoImpl.createAcc(this)) return true;
			return false;
	}
	public static List<Account> getAcc(){
		return DaoImpl.getAccounts();
	}
	@Override
	public String toString() {
		return  "[Account Number: " + accountNumber + "  ] [Balance: " + balance +" ] [Account Type: " + type + "]";
	}

	public static boolean transfer(Account debit, Account credit,int m) {
		// TODO Auto-generated method stub
			debit.balance -= m;
			credit.balance += m;
			if(debit.balance >= 0) {
				if( DaoImpl.updateBalance(debit) & DaoImpl.updateBalance(credit)) return true;
				else return false;
			}
		return false;
	}

}