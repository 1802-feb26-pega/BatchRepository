package com.ex.pojos;

import java.io.Serializable;

public class SecureStudent implements Serializable{
	
	private static final long serialVersionUID = -5899531588187378184L;
	
	private String name;
	private String username;
	private transient String password;
	static int counter;
	private transient char ch;
	private transient float fl;
	private transient boolean test;
	
	
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

	public static int getCounter() {
		return counter;
	}
	
	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public float getFl() {
		return fl;
	}

	public void setFl(float fl) {
		this.fl = fl;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return counter + ": SecureStudent [name=" + name + ", username=" + username + ", password=" + password + "]";
	}
	
	
}
