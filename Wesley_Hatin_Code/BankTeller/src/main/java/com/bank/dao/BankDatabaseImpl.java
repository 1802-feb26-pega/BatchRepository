package com.bank.dao;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import com.bank.obj.Account;
import com.bank.util.ConnectionFactory;

public class BankDatabaseImpl implements BankDatabase{	
	
	//adds a new client entry to the client table
	public void writeNewClient(String username, String password) {
		
		
	}
	
	//adds a new account to the account table attached to a client
	public void writeNewAccount(String username, String firstName, String lastName, String accountName) {
		
	}
	
	//checks a username against all usernames in the database, returns true if one already exists
	public boolean userValidation(String givenUser){
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			CallableStatement cstat = conn.prepareCall(sql)
		}
		if() {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//returns a String array containing the entry that corresponds with the given username
	public Account retrieveAccountInfo(String givenUser, String accountName) {
		Account account = null;
		return account;
	}
	
	//edits the balance of the entry with the given username to equal the new balance
	public void writeBalance(String username, String string, double newBal) {
		
	}


	public boolean passwordValidation(String givenUser, String givenPass) {
		// TODO Auto-generated method stub
		return false;
	}
}
