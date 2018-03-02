package com.ex.pogo;

import java.io.Serializable;

public class SecureStudent implements Serializable{
	
	
	private static final long serialVersionUID = 6768222413109592161L;
	
	private String name;
	private String username;
	private transient String password; //transient = wont be serialized - i.e. cannot be reserialized (value will be set to default value)
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
	
	@Override
	public String toString() {
		return null;
	}

}
