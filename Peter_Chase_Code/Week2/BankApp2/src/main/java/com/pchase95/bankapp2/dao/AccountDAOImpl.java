package com.pchase95.bankapp2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.pchase95.bankapp2.pojos.Account;
import com.pchase95.bankapp2.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	public List<Account> getAllAccounts() {
		List<Account> accounts = new LinkedList<>();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM account";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Account act = new Account();
				act.setId(rs.getLong(1));
				act.setName(rs.getString(2));
				act.setEmail(rs.getString(3));
				act.setPassword(rs.getString(4));
				act.setBalance(rs.getDouble(5));
				accounts.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}

	public Account getAccountById(long accountId) {
		Account act = null;
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM account WHERE act_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, accountId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				act = new Account();
				act.setId(rs.getLong(1));
				act.setName(rs.getString(2));
				act.setEmail(rs.getString(3));
				act.setPassword(rs.getString(4));
				act.setBalance(rs.getDouble(5));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return act;
	}

	public boolean addAccount(Account newAccount) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"INSERT INTO account (act_name, act_email, act_password, act_balance) VALUES (?, ?, ?, ?)";
			String[] keys = { "act_id" };
			PreparedStatement pstmt = conn.prepareStatement(sql, keys);
			pstmt.setString(1, newAccount.getName());
			pstmt.setString(2, newAccount.getEmail());
			pstmt.setString(3, newAccount.getPassword());
			pstmt.setDouble(4, newAccount.getBalance());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while (rs.next()) {
					newAccount.setId(rs.getLong(1));
				}
				
				conn.commit();
				return true;
			}
			
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updateAccount(long accountId, Account updatedAccount) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			
			String sql =
				"UPDATE account SET act_name = ?, act_email = ?, act_password = ?, act_balance = ? WHERE act_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedAccount.getName());
			pstmt.setString(2, updatedAccount.getEmail());
			pstmt.setString(3, updatedAccount.getPassword());
			pstmt.setDouble(4, updatedAccount.getBalance());
			pstmt.setLong(5, updatedAccount.getId());
			
			rowsAffected = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	public boolean removeAccount(long accountId) {
		int rowsAffected = 0;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM account WHERE act_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, accountId);
			rowsAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected > 0;
	}

	public boolean accountExists(long accountId) {
		return getAccountById(accountId) != null;
	}

}
