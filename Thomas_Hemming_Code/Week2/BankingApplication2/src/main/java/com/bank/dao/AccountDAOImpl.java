package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.objects.Account;
import com.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO {
	@Override
	public List<Account> getAllAccounts(int userId) {
		List<Account> accounts = new ArrayList<Account>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM Accounts WHERE userId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Account temp = new Account();
				temp.setAccId(rs.getInt(1));
				temp.setUserId(rs.getInt(2));
				temp.setBalance(rs.getDouble(3));
				accounts.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	@Override
	public Account getAccountByIds(int accountId, int userId) {
		Account account = new Account();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM Accounts WHERE accountId = ? AND userId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			pstmt.setInt(2, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account.setAccId(rs.getInt(1));
				account.setUserId(rs.getInt(2));
				account.setBalance(rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public Account addAccount(Account account) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO Accounts(UserId, Balance) VALUES(?, DEFAULT)";
			
			String[] key = new String[1];
			key[0] = "accountId";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setInt(1, account.getUserId());
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()) {
					account.setUserId(rs.getInt(1));
				}
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public int updateAccount(Account account) {
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "UPDATE Accounts SET balance = ? WHERE accountId = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, account.getBalance());
			pstmt.setInt(2, account.getAccId());
			
			rowsAffected = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public double getAccountsTotal(int userId) {
		double value = 0.0;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "{call get_accounts_total(?, ?)}";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, userId);
			cstmt.registerOutParameter(2, java.sql.Types.DOUBLE);
			
			cstmt.executeQuery();
			
			value = cstmt.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return value;
	}
}
