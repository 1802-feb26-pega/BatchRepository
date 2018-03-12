package com.revature.bank.dao;

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
	 *
	 */
	@Override
	public void updateAccount(Account account) throws SQLException {

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
	 * 
	 */
	@Override
	public Collection<Account> getAccountsByUserID(int userID) throws SQLException {
		
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
	 * 
	 */
	@Override
	public Account addAccount(Account newAccount) {

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

}
