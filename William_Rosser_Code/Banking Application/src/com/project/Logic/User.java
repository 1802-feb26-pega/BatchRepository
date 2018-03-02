package com.project.Logic;

import java.io.Serializable;

public class User implements Serializable {
	private static final String serializeFile = "bin/data/lastUser.txt";
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String emailAddress;
	private String username;
	private String password;
	private float balance;
	
	public User(String firstName, String middleInitial, String lastName, String emailAddress, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.username = username;
		this.password = password;
	}
	
	public User(String firstName, String middleInitial, String lastName, String emailAddress, String username,
			String password, float balance) {
		this(firstName, middleInitial,lastName,emailAddress,username,password);
		this.balance = balance;
	}

	public User() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getWholeName() {
		return firstName + " " + middleInitial + " " + lastName;
	}
	
	@Override
	public String toString() {
		return firstName + "::" + middleInitial + "::" + lastName
				+ "::" + emailAddress + "::" + username + "::" + password + "::" + balance;
	}
	
	public static User instantiateUser(String[] args) {
		if (args.length != 7) return null;
		//TODO: Check for float errors
		return new User(args[0],args[1],args[2],args[3],args[4],args[5],Float.parseFloat(args[6]));
	}
	
	
	
	

}
