package com.ex.pojos;

public class User {
	String name;
	String bio;
	String email;
	
	public User() { }
	
	public User(String name, String bio, String email) {
		super();
		this.name = name;
		this.bio = bio;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
