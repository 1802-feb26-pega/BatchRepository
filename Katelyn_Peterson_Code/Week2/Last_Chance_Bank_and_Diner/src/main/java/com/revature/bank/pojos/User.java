package com.revature.bank.pojos;

import java.util.ArrayList;

//Name: Katelyn Peterson
//Date: Mar. 9th, 2018

// To Do List: add function to add a new account.

/**
 * <h1> User </h1> 
 * The User class includes the userID, userName, password, firstName, lastName, and a list of accounts.
 * 
 * @author Katelyn Peterson
 * @version 2.0
 * @since 03-09-2018 
 *
 */
public class User
{
	// User will store user id, username, password, name (first and last), and an ArrayList of accounts.
	// Variables
	private int userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;
	
	// Constructor
	/**
	 * This is the default constructor for the User class.
	 */
	public User()
	{
		this.userId = 0;
		this.accounts = new ArrayList<>();
	}
	
	/**
	 * This is the full constructor for the User class.
	 * @param userName  This is the username of a User.
	 * @param password  This is the password of a User.
	 * @param firstName  This is the first name of a User.
	 * @param lastName  This is the last name of a User.
	 */
	public User(String userName, String password, String firstName, String lastName)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accounts = new ArrayList<>();
	}
	
	// Getters and Setters
	public int getUserId()
	{
		return userId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public void setUsername(String userName)
	{
		this.userName = userName;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (accounts == null)
		{
			if (other.accounts != null)
				return false;
		}
		else if (!accounts.equals(other.accounts))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		}
		else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		}
		else if (!lastName.equals(other.lastName))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null)
		{
			if (other.userName != null)
				return false;
		}
		else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", accounts=" + accounts + "]";
	}
}
