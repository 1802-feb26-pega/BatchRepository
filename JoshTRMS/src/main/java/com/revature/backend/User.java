package com.revature.backend;

public class User {

	private int user_id;
	private String username;
	private String first_name;
	private String last_name;
	private String email;
	private int role_id;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return user's full name
	 */
	public String getFullName(){
		return first_name + " " + last_name;
	}
	
	
	

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", first_name=" + first_name + ", last_name="
				+ last_name + ", email=" + email + ", role_id=" + role_id + ", password=" + password + "]";
	}

	public User(int user_id, String username, String first_name, String last_name, String email, int role_id,
			String password) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.role_id = role_id;
		this.password = password;
	}

	public User() {
		super();
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
}
