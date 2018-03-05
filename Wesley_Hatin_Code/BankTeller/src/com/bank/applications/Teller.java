package com.bank.applications;

import java.util.Scanner;

public class Teller {
	
	private String username;
	private String firstName;
	private String lastName;
	private String password="";
	
	// create an account with a unique email or username
	public void createAccount() {
		//TODO: ask for help with the writing to a file
		//TODO: Write username, firstName, lastName, password
	}
	
	// log in 
	public boolean logIn(String username, String password) {
		//TODO:check if username/password are in database
		if(username.equals("alpha")) {
			this.username=username;
			this.password=password;
			return true;
		}
		else {
			return false;
		}
	}
	
	//log out
	public void logOut() {
		this.username=null;
		this.password=null;
	}
	
	//deposit money
	public void deposit(int amount) {
	}
	
	//withdraw money
	public boolean withdraw(int amount) {
		return false;
	}
	
	//view balance
	public float getBalance() {
		return 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return (firstName+" "+lastName);
	}

	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}


}
