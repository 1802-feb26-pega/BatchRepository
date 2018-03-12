package com.bank.objects;

import java.util.ArrayList;

public class User {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public User() {}
	
	public User(String firstName, String lastName, String email, String password) {
		super();
		this.userId = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.firstName + " - " + this.lastName + " - " + this.email + " - " + this.password + "\n";
	}	
}

