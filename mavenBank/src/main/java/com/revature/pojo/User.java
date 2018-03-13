package com.revature.pojo;

public class User {
	
	private int userId;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	public User() {}
	
	public User(int id, String user, String pwd, String firstname, String lastname) {
		this.userId = id;
		this.username = user;
		this.password = pwd;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User(String user, String pwd, String firstname, String lastname) {
		this.username = user;
		this.password = pwd;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public User(String user, String pwd) {
		this.username = user;
		this.password = pwd;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "User Info [ " + userId + ", " + username + ", " + password + ", " 
				+ firstname + ", " + lastname + "]";
	}
	
	

}
