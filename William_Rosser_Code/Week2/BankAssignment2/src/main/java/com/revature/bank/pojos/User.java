package com.revature.bank.pojos;

public class User {
	private final int userID;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String email;
	private String username;
	private String password;
	
	public User(int userID, String firstName, String middleInitial, String lastName, String email, String username,
			String password) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public User(int userID) {
		super();
		this.userID = userID;
	}
	
	/**
	 * Represents a new, temporary User object not yet in the database.
	 */
	public User() { 
		super();
		this.userID = -1;
	}
	
	/**
	 * Represents a new, temporary User object not yet in the database.
	 */
	public User(String firstName, String middleInitial, String lastName, String email, String username,
			String password) {
		this();
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userID;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userID != other.userID)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", firstName=" + firstName + ", middleInitial=" + middleInitial
				+ ", lastName=" + lastName + ", email=" + email + ", username=" + username + ", password=" + password
				+ "]";
	}
	
	
	
	
}
