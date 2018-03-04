package Bank;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

	private String name;
	
	private ArrayList<User> users;
	
	private ArrayList<Account> accounts;
	
	public Bank(String name)
	{
		this.name = name;
		this.users = new ArrayList<User>();
		this.accounts = new ArrayList<Account>();
	}
	
	//Generate a new UUID for the user
	public String getNewUserUUID()
	{
		String uuid;
		Random rng = new Random();
		int length = 6;
		boolean nonUnique;
		
		//Loop until we get a new unique ID
		do {
			//Generate number
			uuid = "";
			for (int c = 0; c < length; c++)
			{
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//Check to make sure it's unique
			nonUnique = false;
			for(User u : this.users)
			{
				if (uuid.compareTo(u.getUUID()) == 0)
				{
					nonUnique = true;
					break;
				}
			}
			
		} while(nonUnique);
		
		
		
		return uuid;
	}
	
	//Get a new account UUID
	public String getNewAccountUUID() {
		String uuid;
		Random rng = new Random();
		int length = 10;
		boolean nonUnique;
		
		//Loop until we get a new unique ID
		do {
			//Generate number
			uuid = "";
			for (int c = 0; c < length; c++)
			{
				uuid += ((Integer)rng.nextInt(10)).toString();
			}
			
			//Check to make sure it's unique
			nonUnique = false;
			for(Account a : this.accounts)
			{
				if (uuid.compareTo(a.getUUID()) == 0)
				{
					nonUnique = true;
					break;
				}
			}
			
		} while(nonUnique);
		
		
		
		return uuid;
	}
	
	//Create a new account
	public void addAccount(Account anAcct)
	{
		this.accounts.add(anAcct);
	}
	
		
	
	//Create a new user object
	public User addUser(String firstName, String lastName, String pin)
	{
	User newUser = new User(firstName, lastName, pin, this);
	this.users.add(newUser);
	
	
	//Create a savings account for the user
	Account newAccount = new Account("Savings", newUser, this);
	newUser.addAccount(newAccount);
	this.accounts.add(newAccount);
	
	return newUser;
	

	
	}

	//Login System
	public User userLogin(String userID, String pin)
	{
		for (User u : this.users)
		{
			if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin))
			{
				return u;
			}
			
			
		}
		//If we havent found the user or pin is invalid
		return null;
		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	
	
	
	}	
	

