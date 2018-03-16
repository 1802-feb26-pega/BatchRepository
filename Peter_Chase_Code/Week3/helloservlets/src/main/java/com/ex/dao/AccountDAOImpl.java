package com.ex.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.ex.pojos.Account;
import com.ex.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM baccount";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getLong(1));
				account.setOwnerId(rs.getLong(2));
				account.setName(rs.getString(3));
				account.setPin(rs.getString(4));
				account.setBalance(rs.getDouble(5));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	@Override
	public List<Account> getUserAccounts(long userId) {
		List<Account> userAccounts = new LinkedList<>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM baccount WHERE buser_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1,  userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getLong(1));
				account.setOwnerId(rs.getLong(2));
				account.setName(rs.getString(3));
				account.setPin(rs.getString(4));
				account.setBalance(rs.getDouble(5));
				userAccounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return userAccounts;
	}

	@Override
	public Account getAccountById(long accountId) {
		Account account = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM baccount WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, accountId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				account = new Account();
				account.setId(rs.getLong(1));
				account.setOwnerId(rs.getLong(2));
				account.setName(rs.getString(3));
				account.setPin(rs.getString(4));
				account.setBalance(rs.getDouble(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public boolean addAccount(Account newAccount) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"INSERT INTO baccount (buser_id, account_name, account_pin, account_balance) VALUES (?, ?, ?, ?)";
			String[] keys = { "account_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setLong(1, newAccount.getOwnerId());
			pstmt.setString(2, newAccount.getName());
			pstmt.setString(3, newAccount.getPin());
			pstmt.setDouble(4, newAccount.getBalance());
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newAccount.setId(rs.getLong(1));
				}	
			}
			
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean updateAccount(long accountId, Account updatedAccount) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"UPDATE baccount SET buser_id = ?, account_name = ?, account_pin = ?, account_balance = ? WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, updatedAccount.getOwnerId());
			pstmt.setString(2, updatedAccount.getName());
			pstmt.setString(3, updatedAccount.getPin());
			pstmt.setDouble(4, updatedAccount.getBalance());
			pstmt.setLong(5, accountId);
			
			rowsAffected = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	@Override
	public boolean removeAccount(long accountId) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM baccount WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, accountId);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}
	
	@Override
	public double totalBalance() {
		double value = 0.0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "{ call total_money(?) }";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.registerOutParameter(1, java.sql.Types.DOUBLE);
			cstmt.executeQuery();
			value = cstmt.getDouble(1);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

}
