package com.revature.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.bankapp.driver.UserInterface;
import com.revature.bankapp.pojos.Account;
import com.revature.bankapp.util.ConnectionFactory;

public class AccountDAOImplement implements AccountDAO {
	
	private UserInterface userInterface;
	public AccountDAOImplement (UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	@Override
	public void addAccount(Account newAccount) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO accounts(account_id, user_id, balance) VALUES (seq_accounts.NEXTVAL, ?, ?)";

			String[] key = new String[1]; // for key auto incrementing, need to pass in an array for the second argument of overloaded prepareStatement method
			key[0] = "account_id";

			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, newAccount.getUser_id());
			pstmt.setDouble(2, newAccount.getBalance());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rowsAffected > 0) {
				while(rs.next()) {
					newAccount.setAccount_id(rs.getInt(1));
				}
				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccount(int account_id, double newbalance) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);
			
			String sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, newbalance);
			pstmt.setInt(2, account_id);
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account getAccount(int account_id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);
			
			Account account = new Account();
			
			String sql = "SELECT * FROM accounts WHERE account_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, account_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				account.setAccount_id(rs.getInt(1));
				account.setUser_id(rs.getInt(2));
				account.setBalance(rs.getDouble(3));
			}

			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Account> getAllAcounts(int user_id) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);
			
			ArrayList<Account> arr = new ArrayList<Account>();
			
			
			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Account ac = new Account();
				ac.setAccount_id(rs.getInt(1));
				ac.setUser_id(2);
				ac.setBalance(rs.getDouble(3));
				
				arr.add(ac);
			}

			return arr;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}