package com.revature.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM accounts";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int uid = rs.getInt(1);
				int accountID = rs.getInt(2);
				double balance = rs.getDouble(3);
				String name = rs.getString(4);
				Account temp = new Account(uid, accountID, balance, name);
				if (temp != null) accounts.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public Account getAccountByAccountID(int accountID) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAllAccountsForUsers(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addAccount(int userID, Account account) {
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

	public int updateAccount(int accountID, Account updated) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeAccount(int accountID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
