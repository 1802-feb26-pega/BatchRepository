package com.ex.pojos;

import java.io.Serializable;

public class SecureStudent implements Serializable{
	
	private static final long serialVersionUID = -654123135498491658L;
	
	private String name;
	private String username;
	private transient String password;//transient prevents serializaion
	static int counter = 0;
	
	public SecureStudent() {
		counter++;
	}
	
	public SecureStudent(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		counter++;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		SecureStudent.counter = counter;
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

	@Override
	public String toString() {
		return counter + ". SecureStudent [name=" + name + ", username=" + username + ", password=" + password + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	

}
