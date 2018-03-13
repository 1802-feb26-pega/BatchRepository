package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Account;
import com.revature.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public List<Account> getAllAccounts() {

		List<Account> accounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Account";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Account temp = new Account();
				temp.setAccountId(rs.getInt(1));
				temp.setBalance(rs.getInt(2));
				temp.setUserId(rs.getInt(3));
				accounts.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return accounts;
	}

	@Override
	public List<Account> getUserAccounts(int userId) {

		List<Account> userAccounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Account WHERE UserId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Account temp = new Account();
				temp.setAccountId(rs.getInt(1));
				temp.setBalance(rs.getInt(2));
				temp.setUserId(rs.getInt(3));
				userAccounts.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userAccounts;
	}

	@Override
	public Account getAccountById(int accountId) {

		Account account = new Account();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Account WHERE AccountId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				account.setAccountId(rs.getInt(1));
				account.setBalance(rs.getInt(2));
				account.setUserId(rs.getInt(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public Account addAccount(Account newAccount) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO Account(Balance, UserId) VALUES (?,?)";

			String[] key = new String[1];
			key[0] = "AccountId";

			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, newAccount.getBalance());
			pstmt.setInt(2, newAccount.getUserId());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rowsAffected > 0) {
				while (rs.next()) {
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
	public int updateAccount(Account updatedAccount) {

		int rowsAffected = -1;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);
			
			String sql = "CALL update_account(?, ?)";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, updatedAccount.getBalance());
			cstmt.setInt(2, updatedAccount.getAccountId());
			
			rowsAffected = cstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsAffected;
	}

	@Override
	public int deleteAccount(int accountId) {

		int rowsAffected = -1;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "DELETE FROM Account WHERE AccountId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			rowsAffected = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsAffected;
	}
}
