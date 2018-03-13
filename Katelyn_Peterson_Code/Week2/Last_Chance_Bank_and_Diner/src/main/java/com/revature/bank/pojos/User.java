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
	
	// Getters
	/**
	 * This is the method to get a particular User's ID.
	 * @return userId - This is a User's ID.
	 */
	public int getUserId()
	{
		return userId;
	}
	
	/**
	 * This is the method to get a particular User's username.
	 * @return userName - This is a User's username.
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * This is the method to get a particular User's password.
	 * @return password - This is a User's password.
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * This is the method to get a particular User's first name.
	 * @return firstName - This is a User's first name.
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * This is the method to get a particular User's last name.
	 * @return lastName - This is a User's last name.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * This is the method to get a particular User's Accounts.
	 * @return accounts - This is an ArrayList of a User's Accounts.
	 */
	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}
	
	// Setters
	/**
	 * This is the method to set a particular User's ID.
	 * @param userId  This is the new ID for a User.
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	/**
	 * This is the method to set a particular User's username.
	 * @param userName  This is the new username for a User.
	 */
	public void setUsername(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * This is the method to set a particular User's password.
	 * @param password  This is the new password for a User.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * This is the method to set a particular User's first name.
	 * @param firstName  This is the new first name for a User.
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * This is the method to set a particular User's last name.
	 * @param lastName  This is the new last name for a User.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	// Overrides
	/**
	 * This is the method to get a particular User's hash code.
	 * @return result - This is the hash code.
	 */
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
	
	/**
	 * This is the method to check if this User is equal to another User.
	 * @return false - If the two Users are not equal.
	 * @return true - If the two Users are equal.
	 */
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
	
	/**
	 * This is the toString method for User.
	 * @return String - This is all the information concerning a User, except for their password.
	 */
	@Override
	public String toString()
	{
		return "User [userId=" + userId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", accounts=" + accounts + "]";
	}
}
