package com.ex.pojos;

public class User {
	private String name;
	private String bio;
	private String email;
	
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", bio=" + bio + ", email=" + email + "]";
	}

	public User(String name, String bio, String email) {
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
