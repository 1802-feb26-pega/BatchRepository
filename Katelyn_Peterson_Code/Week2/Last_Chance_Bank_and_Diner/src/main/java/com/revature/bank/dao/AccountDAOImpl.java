package com.revature.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.util.ConnectionFactory;

public class AccountDAOImpl implements AccountDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;

	@Override
	public List<Account> getAllAccounts()
	{
		List<Account> accounts = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Account temp = new Account();
				
				temp.setAccountId(rs.getInt(1));
				temp.setBalance(rs.getDouble(3));
				
				accounts.add(temp);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<Account> getAccountsByUserId(int userId)
	{
		List<Account> accounts = new ArrayList<Account>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			sql = "SELECT * FROM accounts WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Account temp = new Account();
				temp.setAccountId(rs.getInt(1));
				temp.setBalance(rs.getDouble(3));
				accounts.add(temp);
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account getAccountByAccountId(int accountId)
	{
		Account account = new Account();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			sql = "SELECT * FROM accounts WHERE account_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				account.setAccountId(accountId);
				account.setBalance(rs.getDouble(3));
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public Account addAccount(int userId, Account newAccount)
	{
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			conn.setAutoCommit(false);
			
			sql = "{CALL add_account(?)}";
			
			/*String[] key = new String[1];
			key[0] = "account_id";*/
			
			cstmt = conn.prepareCall(sql);
			
			cstmt.setInt(1, userId);
			//cstmt.executeUpdate();
			
			int changeRows = cstmt.executeUpdate();
			//rs = cstmt.getGeneratedKeys();
			
			sql = "SELECT MAX(account_id) FROM accounts WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			if(changeRows > 0)
			{
				while(rs.next())
				{
					newAccount.setAccountId(rs.getInt(1));
				}
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return newAccount;
	}
	
	/*@Override
	public Account getNewAccount(int userId)
	{
		Account account = new Account();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			sql = "SELECT account_id, balance FROM accounts WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				account.setAccountId(rs.getInt(1));
				account.setBalance(rs.getDouble(2));
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public int updateAccount(int accountId, Account updatedAccount)
	{
		int changeRows = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			sql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, updatedAccount.getBalance());
			pstmt.setInt(2, updatedAccount.getAccountId());
			changeRows = pstmt.executeUpdate();
			
			if(changeRows > 0)
			{
				
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return changeRows;
	}

	@Override
	public int removeAccountByAccountId(int accountId)
	{
		
		int changeRows = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM accounts WHERE account_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, accountId);
			changeRows = pstmt.executeUpdate();
			
			if(changeRows > 0)
			{
				
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return changeRows;
	}

	@Override
	public int removeAccountsByUserId(int userId)
	{
		int changeRows = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM accounts WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			changeRows = pstmt.executeUpdate();
			
			if(changeRows > 0)
			{
				
				conn.commit();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return changeRows;
	}
	
}
