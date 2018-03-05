package com.revature.bank;

//Name: Katelyn Peterson
//Date: Mar. 3rd, 2018

public class User
{
	// User will store username, password, and account.
	// Variables
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Account account;
	
	// Constructor
	public User(String userName, String password, String firstName, String lastName, Account account)
	{
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.account = account;
	}
	
	// Getters and Setters
	public String getUserName()
	{
		return userName;
	}

	public void setUsername(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Account getAccount()
	{
		return account;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
