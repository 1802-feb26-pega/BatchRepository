package com.ex.pojos;

import java.io.Serializable;

public class SecureStudent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String username;
	
	private transient String password;
	static int counter;
	
	public SecureStudent() {
		counter++;
	}
	
	public SecureStudent(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
		
		counter++;
	}

	@Override
	public String toString() {
		return "SecureStudent [name=" + name + ", username=" + username + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	

}
