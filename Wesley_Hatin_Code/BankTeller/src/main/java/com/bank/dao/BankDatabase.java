package com.bank.dao;

import java.util.List;

import com.bank.obj.Account;

public interface BankDatabase {
	//adds a new client entry to the client table
	public void writeNewUser(String username, String firstName, String lastName, String password);
	
	//adds a new account to the account table attached to a client
	public void writeNewAccount(String username, String accountName);
	
	//checks a username against all usernames in the database, returns 1 if that username already exists
	public boolean userValidation(String givenUser);
	
	//checks if a given username is linked with a given password, returns 1 if true
	public boolean passwordValidation(String givenUser, String givenPass);
	
	//returns a String array containing the first and last name of the given user
	public String[] retrieveName(String givenUser);
	
	//returns a list of accounts that correspond with the given user
	public List<Account> retrieveAccounts(String givenUser);
	
	//updates the balance of the account connected with the given user
	public void writeBalance(String username, Account account, double newBal);
}
