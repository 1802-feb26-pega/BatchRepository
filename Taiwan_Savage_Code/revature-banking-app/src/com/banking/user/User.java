package com.banking.user;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3703685744072934213L;
	
	public String username;
	
	private String firstName;
	private String lastName;
	private String password;
	private String serializedFile;
	

	private int accountBalance;
	
	//creates a user account
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		accountBalance = 0;
		
		serializedFile = "src/users/" + username +".txt";
	}
	public String getSerializedFile() {
		return serializedFile;
	}
	
	public void setSerializedFile(String serializedFile) {
		this.serializedFile = serializedFile;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
