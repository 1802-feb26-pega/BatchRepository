package com.revature.bank1;

import java.io.Serializable;

/*
 * User is a POJO used to keep track of user data, such as
 * user names, passwords, and account balance.  
 */

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5908192065357093242L;
	private final String userName;
	private String password;
	private float balance;
	
	
	public User(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
	}
	
	public User(String userName, String password, float balance) {
		
		this(userName, password);
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "User : " + userName + "  [password : " + password + ", balance : " + balance + "]";
	}

}//User
