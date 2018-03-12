package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.bank.obj.Account;
import com.bank.util.ConnectionFactory;

public class BankDatabaseImpl implements BankDatabase{	
	
	//adds a new client entry to the client table
	public void writeNewUser(String username, String firstName, String lastName, String password) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstat = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
			pstat.setString(1, username);
			pstat.setString(2, firstName);
			pstat.setString(3, lastName);
			pstat.setString(4, password);
			pstat.executeUpdate();
			
			pstat.close();
			conn.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//adds a new account to the account table attached to a client
	public void writeNewAccount(String username, String accountName) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			PreparedStatement pstat = conn.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?)");
			
			pstat.setString(1, accountName);
			pstat.setString(2, username);
			pstat.setDouble(3, 0.0);
			pstat.executeUpdate();
			
			pstat.close();
			conn.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//checks a username against all usernames in the database, returns true if that username already exists
	public boolean userValidation(String givenUser){
		int result = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			CallableStatement cstat = conn.prepareCall("{CALL user_check(?,?)}");
			cstat.setString(1, givenUser);
			cstat.registerOutParameter(2, java.sql.Types.INTEGER);
			
			cstat.executeUpdate();
			result = cstat.getInt(2);
			
			cstat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		if(result==1) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//checks if a given username is linked with a given password
	public boolean passwordValidation(String givenUser, String givenPass) {
		int result = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			CallableStatement cstat = conn.prepareCall("{CALL password_check(?,?,?)}");
			cstat.setString(1, givenUser);
			cstat.setString(2, givenPass);
			cstat.registerOutParameter(3, java.sql.Types.INTEGER);
			
			cstat.executeUpdate();
			result = cstat.getInt(3);
			
			cstat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		if(result==1) {
			return true;
		}
		else {
			return false;
		}
	}

	//checks if a given username has a given account under it, returns
	public boolean accountValidation(String givenUser, String givenAccount) {
		int result = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			CallableStatement cstat = conn.prepareCall("{CALL account_check(?,?,?)}");
			cstat.setString(1, givenUser);
			cstat.setString(2, givenAccount);
			cstat.registerOutParameter(3, java.sql.Types.INTEGER);
			
			cstat.executeUpdate();
			result = cstat.getInt(3);
			
			cstat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		if(result==1) {
			return true;
		}
		else {
			return false;
		}
	}	

	//returns a String array containing the first and last name of the given user
	public String[] retrieveName(String givenUser) {
		String[] name = new String[2];
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			PreparedStatement pstat = conn.prepareStatement("SELECT firstname, lastname FROM users WHERE user = ?");
			pstat.setString(1, givenUser);
			
			ResultSet rs = pstat.executeQuery();
			name[0]=rs.getString(1);
			name[1]=rs.getString(2);
			
			pstat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return name;
	}
	
	//returns a list of accounts that correspond with the given user
	public List <Account> retrieveAccounts(String givenUser) {
		List <Account> accountList = new ArrayList <Account>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE user = '"); 
			sql.append(givenUser);
			sql.append("'");
			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery(sql.substring(0));
			while(rs.next()) {
				Account newAccount = new Account();
				newAccount.setAccountName(rs.getString(1));
				newAccount.setUsername(rs.getString(2));
				newAccount.setBalance(rs.getDouble(3));
				accountList.add(newAccount);
				
			}
			
			stat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return accountList;
	}
	
	//updates the balance of the account connected with the given user
	public void writeBalance(String username, Account account, double newBal) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			PreparedStatement pstat = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE user = ? AND accountname = ?");
			pstat.setDouble(1, newBal);
			pstat.setString(2, username);
			pstat.setString(3, account.getAccountName());
			
			pstat.executeQuery();
			
			pstat.close();
			conn.close();
			
		}catch(SQLException s) {
			s.printStackTrace();
		}
	}
}
