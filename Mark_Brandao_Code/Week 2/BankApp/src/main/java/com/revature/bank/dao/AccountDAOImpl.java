package com.revature.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.bank.pojos.Account;
import com.revature.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAllAccounts() {
		AccountDAO accountDao = new AccountDAOImpl();
		List<Account> accounts = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM accounts";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Account temp = new Account();
				temp.setAccountId(rs.getInt(1));
				temp.setBalance(rs.getDouble(2));
				temp.setAccountType(accountDao.getAccountTypeName(rs.getInt(3)));
				accounts.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account getAccountById(int accountId) {
		AccountDAO accountDao = new AccountDAOImpl();
		Account account = new Account();
		account.setAccountId(accountId);
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM accounts WHERE accountid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,accountId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account.setBalance(rs.getDouble(2));
				account.setAccountType(accountDao.getAccountTypeName(rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account addAccount(Account newAccount) {
		AccountDAO accountDao = new AccountDAOImpl();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			int typeval = accountDao.getAccountTypeNum(newAccount.getAccountType());
			String sql = "INSERT INTO accounts (balance, accounttype) VALUES (?, ?)";
			
			String[] key = new String[1];
			key[0] = "accountid";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setDouble(1, newAccount.getBalance());
			pstmt.setInt(2, typeval);
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()){
					newAccount.setAccountId(rs.getInt(1));
				}	
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAccount;
	}

	@Override
	public int updateAccount(int accountId, Account updatedAccount) {
		AccountDAO accountDao = new AccountDAOImpl();
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "UPDATE accounts " +
						 "SET balance = ?, accounttype = ?" +
						 "WHERE accountid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, updatedAccount.getBalance());
			pstmt.setInt(2, accountDao.getAccountTypeNum(updatedAccount.getAccountType()));
			pstmt.setInt(3, updatedAccount.getAccountId());
			
			rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}

	@Override
	public int removeAccount(int accountId) {
		if(hasUser(accountId)) { 
			System.out.println("Account cannot be deleted!");
			System.out.println("Remove users owning this account and try again.");
			return -1;
		}
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM accounts WHERE accountid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			rowsAffected = pstmt.executeUpdate();
			
			if(rowsAffected > 0) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}

	@Override
	public boolean hasUser(int accountId) {
		UserAccountDAO uadao = new UserAccountDAOImpl();
		List<Integer> accountUsers =  uadao.getUserIDByAccountID(accountId);
		
		if(accountUsers.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public int getAccountTypeNum(String type) {
		int rval = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM accounttypes WHERE title = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				rval = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rval;
	}

	@Override
	public String getAccountTypeName(int type) {
		String rval = "";
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM accounttypes WHERE accounttype = ?";
	
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				rval = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rval;
	}

	public Map<Integer, String> getAccountTypes(){ 
		Map<Integer, String> accs = new HashMap<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM accounttypes";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				accs.put(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accs;
	}
}
