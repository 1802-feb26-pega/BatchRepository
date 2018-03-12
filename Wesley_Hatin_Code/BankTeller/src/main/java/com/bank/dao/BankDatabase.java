package com.bank.dao;

import com.bank.obj.Account;

public interface BankDatabase {
	//adds a new client entry to the client table
	public void writeNewClient(String username, String password);
	
	//adds a new account to the account table attached to a client
	public void writeNewAccount(String username, String firstName, String lastName, String accountName);
	
	//checks a username against all usernames in the database, returns true if one already exists
	public boolean userValidation(String givenUser);
	
	public boolean passwordValidation(String givenUser, String givenPass);
	
	//returns a String array containing the entry that corresponds with the given username
	public Account retrieveAccountInfo(String givenUser, String accountName);
	
	//edits the balance of the entry with the given username to equal the new balance
	public void writeBalance(String username, String string, double newBal);
}
