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

/**
* <h1> AccountDAOImpl </h1> 
* The AccountDAOImpl class includes a String, a ResultSet, a Statement, a Prepared Statement, and a 
* Callable Statement.  It also implements the AccountDAO Interface.
* 
* This class handles all Account related database calls for the program.
* 
* @author Katelyn Peterson
* @version 1.0
* @since 03-09-2018 
*
*/
public class AccountDAOImpl implements AccountDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	private CallableStatement cstmt;
	
	/**
	 * This is the method to find all Accounts in the database. 
	 * 
	 * @return accounts - This is a List of all accounts in the database.
	 */
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
	
	/**
	 * This is the method to find all Accounts belonging to a particular User. 
	 * 
	 * @param userId  This is the user ID of the User being searched for.
	 * @return accounts - This is a List of all accounts in the database belonging to a particular User.
	 */
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
	
	/**
	 * This is the method to find an Account in the database via their Account ID. 
	 * 
	 * @param accountId  This is the account ID of the Account being searched for.
	 * @return account - This is the Account belonging to the account ID.
	 */
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
	
	/**
	 * This is the method to add a new Account to the database. 
	 * 
	 * @param newAccount  This is the user ID for the User creating a new Account.
	 * @param newAccount  This is the information for the Account being added, minus their Account ID.
	 * @return newAccount - This is information for the Account being added, plus their Account ID.
	 */
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
	
	/**
	 * This is the method to update an Account's information in the database. 
	 * 
	 * @param accountId  This is the ID of the Account being updated.
	 * @param updatedAccount  This is all the information for the Account being updated.
	 * @return changeRows - This is confirmation that the Account has either been updated or that the operation has failed.
	 */
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
	
	/**
	 * This is one method to remove an Account from the database. 
	 * 
	 * @param accountId  This is ID of the Account being removed.
	 * @return changeRows - This confirms that either the Account has been removed or that the operation failed.
	 */
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
	
	/**
	 * This is the method to remove all of a User's Accounts from the database. 
	 * 
	 * @param userId  This is ID of the User whose Accounts are being removed.
	 * @return changeRows - This confirms that either the Accounts have been removed or that the operation failed.
	 */
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
