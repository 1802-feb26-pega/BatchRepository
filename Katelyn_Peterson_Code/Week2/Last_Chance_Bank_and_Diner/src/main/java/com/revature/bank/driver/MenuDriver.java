package com.revature.bank.driver;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;

public class MenuDriver
{
	// DAO
	static private UserDAOImpl userDao = new UserDAOImpl();
	static private AccountDAOImpl accountDao = new AccountDAOImpl();
	
	// User Variables
	static private Scanner userInput = new Scanner(System.in);
	static private User currUser;
	static private String userName;
	static private String password;
	static private int option = 0;
	static private String firstName;
	static private String lastName;
	static private Double amount;
	static private DecimalFormat newFormat = new DecimalFormat("#.00");
	//newFormat.setRoundingMode(RoundingMode.DOWN);
	static private Boolean valid = false;
	
	// Log In
	static public void logIn(HashMap<String, User> users)
	{
		boolean valid = false;
		
		do
		{
			System.out.println("Username? ");
        	userName = userInput.nextLine();
        	
        	// Validation
        	if(!users.isEmpty())
        	{
	        	for(String key : users.keySet())
	        	{
	        		//System.out.println("Key: " + key);
	        		//System.out.println("userName: " + userName);
	        		if (key.equals(userName))
	        		{
	        			valid = true;
	        			break;
	        		}
	        	}
	        	
	        	if(!valid)
	        	{
	        		System.out.println("That Username does not match our files.");
	        		System.out.println("Type 1 to try again: ");
	        		ValidationDriver.validateInput(userInput, "Choice");
	    	        option = userInput.nextInt();
	    	        userInput.nextLine();
	    	        
	    	        if (option != 1)
	    	        {
	    	        	return;
	    	        }
	        	}
	        	
				// Password
				do
				{
					valid = false;
		        	System.out.println("Password? ");
		        	password = userInput.nextLine();
		        	
		        	// Validation
		        	if(!users.get(userName).getPassword().equals(password))
		        	{
		        		System.out.println("That Password does not match our files.");
		        		System.out.println("Type 1 to try again: ");
		        		ValidationDriver.validateInput(userInput, "Choice");
		    	        option = userInput.nextInt();
		    	        userInput.nextLine();
		    	        
		    	        if (option != 1)
		    	        {
		    	        	return;
		    	        }
		        	}
		        	else
		        	{
		        		valid = true;
		        	}
					
				}while (!valid);
				
				currUser = users.get(userName);
				
				
				// Go To Account Menu
				accountAccess();
        	}
        	else
        	{
        		System.out.println("We do not have any users");
        		System.out.println();
        		return;
        	}
			
		}while (!valid);
		
		//return valid;
	}
	
	// Accounts
	static private void accountAccess()
	{
		boolean valid = false;
		int accountChoice = 0;
		
		do
		{
			for(int x = 0; x < 10; x++)
			{
				System.out.println();
			}
			
			currUser.getAccounts().clear();
			currUser.getAccounts().addAll(accountDao.getAccountsByUserId(currUser.getUserId()));
			
			int[] options = new int[currUser.getAccounts().size()];			
			
			System.out.println("Welcome: " + currUser.getFirstName() + " " + currUser.getLastName());
			System.out.println("Your Current Accounts are: ");
			
			for (int x = 0; x < currUser.getAccounts().size(); x++)
			{
				options[x] = x + 1;
				System.out.println("Choice " + (x + 1) + ": " + currUser.getAccounts().get(x).toString());
			}
			
			System.out.println();
			
			// Select An Account
			System.out.println("Please Select an Account, Create a New One, or Log Out: ");
			System.out.println("Select an Account by typing the Choice Number.");
			System.out.println("To Create a New Account, type " + (options[options.length - 1] + 1));
			System.out.println("To Transfer Money between Accounts, type " + (options[options.length - 1] + 2));
			System.out.println("Or Log Out by typing " + (options[options.length - 1] + 3));
			ValidationDriver.validateInput(userInput, "Choice");
	        option = userInput.nextInt();
	        
	        userInput.nextLine();
	        
	        option = ValidationDriver.choiceVal(option, 1, (options.length + 3));
	        
	        if (option < (options.length + 1))
	        {
	        	accountChoice = option - 1;
	        	
	        	System.out.println();
	    		System.out.println("Would you like to deposit or withdraw?");
	    		System.out.println("Type 1 to deposit and 2 to withdraw.");
	    		System.out.println("Type 3 to exit back to Welcome Screen");
	    		ValidationDriver.validateInput(userInput, "Choice");
	            option = userInput.nextInt();
	            userInput.nextLine();
	            
	            option = ValidationDriver.choiceVal(option, 1, 3);
	            
	            if (option == 1)
	            {
	            	// Credit
	            	accountCred(currUser.getAccounts().get(accountChoice));
	            }
	            else if (option == 2)
	            {
	            	// Debit
	            	accountDeb(currUser.getAccounts().get(accountChoice));
	            }
	            else
	            {
	            	return;
	            }
	        }
	        else if (option == (options.length + 1))
	        {
	        	newAccount();
	        }
	        else if (option == (options.length + 2))
	        {
	        	// Variables
	        	Account[] transferring = new Account[2];
	        	
	        	System.out.println("Select Account to Transfer From: ");
	        	ValidationDriver.validateInput(userInput, "Choice");
		        option = userInput.nextInt();
		        userInput.nextLine();
		        
		        option = ValidationDriver.choiceVal(option, 1, (options.length));
		        
		        accountChoice = option - 1;
		        
		        transferring[0] = currUser.getAccounts().get(accountChoice);
		        
	        	System.out.println("Select Account to Transfer To: ");
	        	ValidationDriver.validateInput(userInput, "Choice");
		        option = userInput.nextInt();
		        userInput.nextLine();
		        
		        option = ValidationDriver.choiceVal(option, 1, (options.length));
		        
		        accountChoice = option - 1;
		        
		        transferring[1] = currUser.getAccounts().get(accountChoice);
		        
		        transfer(transferring);
	        }
	        else
	        {
	        	return;
	        }
		}while(!valid);
	}
	
	// Deposit/Withdraw
	static private void accountCred(Account currAccount)
	{
		// Variables
		newFormat.setRoundingMode(RoundingMode.DOWN);
		boolean notNeg = false;
		
    	do
    	{
        	System.out.println(currAccount.toString());
    		System.out.println("Enter amount to deposit");
        	
        	ValidationDriver.validateInput("Amount", userInput);
	        amount = userInput.nextDouble();
	        userInput.nextLine();
	        
	        notNeg = ValidationDriver.notNega(amount);
        
    	}while(!notNeg);
        
        amount = Double.valueOf(newFormat.format(amount));
        
        currAccount.credit(amount);
        
        accountDao.updateAccount(currAccount.getAccountId(), currAccount);
        
	}
	
	// Withdraw
	static private void accountDeb(Account currAccount)
	{
		// Variables
		newFormat.setRoundingMode(RoundingMode.DOWN);
		boolean notNeg = false;
		boolean notOver = false;

        	do
        	{
	        	do
	        	{
	        		System.out.println(currAccount.toString());
	        		System.out.println("Enter amount to withdraw");
		        	
		        	ValidationDriver.validateInput("Amount", userInput);
			        amount = userInput.nextDouble();
			        userInput.nextLine();
			        
			        notNeg = ValidationDriver.notNega(amount);
		        
	        	}while(!notNeg);
		        
		        amount = Double.valueOf(newFormat.format(amount));
		        
		        try
		        {
		        	currAccount.debit(amount);
		        	accountDao.updateAccount(currAccount.getAccountId(), currAccount);
		        	notOver = true;
		        }
		        catch (IllegalArgumentException ex)
		        {
		        	System.out.println("Withdrawal Amount Exceeds Account Balance!");
		        	System.out.println("Nice try...better luck next time.");
		        }
        	}while(!notOver);
        
	}
	
	// New Account
	static private void newAccount()
	{
    	Account temp = new Account();
    	temp = accountDao.addAccount(currUser.getUserId(), temp);
    	
    	if (temp.getAccountId() != 0)
    	{
    		currUser.getAccounts().add(temp);
    		System.out.println("New Account Created!");
    	}
    	else
    	{
    		System.out.println("Account creation failed.");
    	}
	}
	
	// Transfer Money Between Accounts
	static private void transfer(Account[] transferring)
	{
		// Variables
		newFormat.setRoundingMode(RoundingMode.DOWN);
		boolean notNeg = false;
		boolean notOver = false;
		
		System.out.println("Transferring Account: " + transferring[0].toString());
		System.out.println("Receiving Account: " + transferring[1].toString());
		
		do
		{
			do
	    	{
				
				System.out.println("Enter Amount to Transfer: ");
				
	        	ValidationDriver.validateInput("Amount", userInput);
		        amount = userInput.nextDouble();
		        userInput.nextLine();
		        
		        notNeg = ValidationDriver.notNega(amount);
	    	        
		        try
		        {
		    		transferring[0].debit(amount);
		    		accountDao.updateAccount(transferring[0].getAccountId(), transferring[0]);
		        	notOver = true;
		        }
		        catch (IllegalArgumentException ex)
		        {
		        	System.out.println("Transfer Amount Exceeds Account Balance!");
		        	System.out.println("Nice try...better luck next time.");
		        }
	    	}while(!notOver);
	    	
			transferring[1].credit(amount);
			accountDao.updateAccount(transferring[1].getAccountId(), transferring[1]);
			
		}while(!notNeg);
		
		System.out.println("Transfer Complete.");
		
	}
	
	// Registration
	static public void register(HashMap<String, User> users)
	{
		do
		{
        	// Register - asks for desired username first.  Checks username against keys and asks for a new 
			// username if key is present.  Once username is set, asks for password, first name, and last name
			// and adds to HashMap
			valid = false;
        	System.out.println("Username? ");
        	userName = userInput.nextLine();
        	
        	// Validation
        	if(!users.isEmpty())
        	{
	        	for(String key : users.keySet())
	        	{
	        		//System.out.println("Key: " + key);
	        		//System.out.println("userName: " + userName);
	        		if (key.equals(userName))
	        		{
	        			valid = true;
	        			break;
	        		}
	        	}
	        	
	        	if(valid)
	        	{
	        		System.out.println("That Username is already taken.");
	        	}
        	}
			
		}while (valid);
		
		// Other user data
    	System.out.println("Password? ");
    	password = userInput.nextLine();
    	System.out.println("First Name? ");
    	firstName = userInput.nextLine();
    	System.out.println("Last Name? ");
    	lastName = userInput.nextLine();
    	
    	User temp = new User();
    	temp.setUsername(userName);
    	temp.setPassword(password);
    	temp.setFirstName(firstName);
    	temp.setLastName(lastName);
    	temp = userDao.addUser(temp);
    	
    	if (temp.getUserId() != 0)
    	{
    		users.put(userName, new User(userName, password, firstName, lastName));
        	System.out.println("Congratulations, you are now registered.  Please log in with your "
        			+ "Username and Password.");
        	
        	// Adding account to new User
        	currUser = temp;
        	newAccount();
    	}
    	else
    	{
    		System.out.println("User creation failed.");
    	}
    	
    	System.out.println();
	}
}