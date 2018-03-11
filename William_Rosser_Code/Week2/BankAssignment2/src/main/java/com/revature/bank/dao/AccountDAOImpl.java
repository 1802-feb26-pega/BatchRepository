package com.revature.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;
import com.revature.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {
	
	private static AccountDAOImpl aDAO = null;
	
	private AccountDAOImpl() {
		aDAO = this;
	}

	public static synchronized AccountDAOImpl getInstance() {
		if (aDAO == null) return new AccountDAOImpl();
		return aDAO;
	}
	
	private Account resultsToAccount(ResultSet rs, boolean callNext) throws SQLException {
		if(!callNext || rs.next()) {
			return new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4));
		}
		return null;
	}
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM accounts";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Account temp = resultsToAccount(rs, false);
				if (temp != null) accounts.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public Account getAccountByAccountID(int accountID) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM accounts WHERE ? = account_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountID);
			ResultSet rs = pstmt.executeQuery();
			return resultsToAccount(rs, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Account> getAllAccountsForUser(int userID) {
		ArrayList<Account> accounts = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM accounts WHERE ? = user_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Account temp = resultsToAccount(rs, false);
				if (temp != null) accounts.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public List<Account> getAllAccountsForUser(User user) {
		return getAllAccountsForUser(user.getUserID());
	}

	public int addAccount(Account account) {
		int userID = account.getUserID();
		if (userID <= 0) return -1;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO accounts(user_id, balance, account_name) VALUES (?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			pstmt.setDouble(2, account.getBalance());
			pstmt.setString(3, account.getAccountName());
			int val = pstmt.executeUpdate();
			if (val > 0) {
				conn.commit();
				return val;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updateAccount(Account updated) {
		int id = updated.getAccountID();
		if(id <= 0) return -1;	
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
//			String sql = "UPDATE accounts SET balance = " + updated.getBalance() 
//			+ ", account_name = '" + updated.getAccountName() + "' where account_id = " + updated.getAccountID();
//			System.out.println(sql);
//			Statement stmt = conn.createStatement();
//			int val = stmt.executeUpdate(sql);
			
			String sql = "UPDATE accounts SET balance = ?, account_name = ? WHERE account_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, updated.getBalance());
			pstmt.setString(2, updated.getAccountName());
			pstmt.setInt(3, id);
			int val = pstmt.executeUpdate();
			if (val>0) {
				//System.out.println("Committed");
				conn.commit();
				return val;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return 0;
	}

	@Override
	public int removeAccount(int accountID) {
		return -1;
	}

}
