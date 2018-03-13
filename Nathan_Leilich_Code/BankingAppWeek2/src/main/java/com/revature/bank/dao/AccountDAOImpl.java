package com.revature.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import com.revature.bank.pojo.Account;
import com.revature.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO{

	/*
	 * Updates a single entry in the bank_account table using an Account
	 * object.  
	 * Throws an SQLException if the account was not updated.
	 */
	@Override
	public void updateAccount(Account account) throws SQLException {

		System.out.println("processing...\n_______________________");
		int rowsAffected = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "UPDATE bank_account SET balance = ? WHERE a_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, account.getBalance());
			pstmt.setInt(2, account.getAccountID());
			rowsAffected = pstmt.executeUpdate();
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(rowsAffected != 1) {
			
			throw new SQLException("account was not updated");
		}//if
		
	}//updateAccount()
	

	/*
	 * Returns all the accounts belonging to a specific user as
	 * a Collection of accounts using bank_user.u_id.
	 * Throws an SQLExcption if there are no Accounts are associated
	 * with the input userID.
	 */
	@Override
	public Collection<Account> getAccountsByUserID(int userID) throws SQLException {
		
		System.out.println("processing...\n_______________________");
		HashSet<Account> accounts = new HashSet<Account>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM bank_account WHERE u_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Account temp = new Account(rs.getInt(1), rs.getInt(2), 
											rs.getString(3), rs.getDouble(4));
				accounts.add(temp);
			}//while
			
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(accounts.size() == 0) {
			
			throw new SQLException("user has no accounts");
		}//if
		return accounts;
	}//getAccountsByUserID()

	
	/*
	 * Inserts a new row entry into the bank_account table using
	 * data provided by the input account object.  Once inserted,
	 * the account object is updated with generated keys fetched
	 * from the database and returned.
	 */
	@Override
	public Account addAccount(Account newAccount) {

		System.out.println("processing...\n_______________________");
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "INSERT INTO bank_account(u_id, accountname, balance) VALUES(?, ?, ?)";
			String[] key = new String[1];
			key[0] = "a_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, newAccount.getUserID());
			pstmt.setString(2, newAccount.getAccountName());
			pstmt.setDouble(3, newAccount.getBalance());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				
				while(rs.next()) {
					
					newAccount.setAccountID(rs.getInt(1));
				}//while
			}//if
			
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		return newAccount;
	}//addAccount()
	
	
	/*
	 * deletes an entry from the bank_account table that has
	 * an entered accountID
	 */
	public void deleteAccount(int accountID) throws SQLException {
		
		System.out.println("processing...\n_______________________");
		int rowsAffected = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "CALL delete_account(?)";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, accountID);
			rowsAffected = cstmt.executeUpdate();
			
		}//try
		catch(SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(rowsAffected == 0) {
			
			throw new SQLException("account with accountId of " + accountID + " exists");
		}//if
	}//deleteAccount()
}//class
