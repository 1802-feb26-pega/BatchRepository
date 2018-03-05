package com.ex.pojos;

import java.io.Serializable;

public class SecureStudent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 434079412938596492L;
	
	private String name;
	private String username;
	private transient String password;
	static int counter = 0;
	private transient char letter;
	private transient boolean test;
	private transient float fl;
	
	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public float getFl() {
		return fl;
	}

	public void setFl(float fl) {
		this.fl = fl;
	}

	public SecureStudent() {
		counter++;
	}
	
//	@Override
//	public String toString() {
//		return counter + "SecureStudent [name=" + name + ", username=" + username + ", password=" + password + "]";
//	}

	public SecureStudent(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		counter++;
	}

	@Override
	public String toString() {
		return counter + "SecureStudent [name=" + name + ", username=" + username + ", password=" + password + ", letter="
				+ letter + ", test=" + test + ", fl=" + fl + "]";
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
	
	
	
}
