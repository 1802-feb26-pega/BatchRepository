package com.revature.bank.dao;

//import java.sql.CallableStatement;
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

public class UserDAOImpl implements UserDAO
{
	// Variables
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	private PreparedStatement pstmt;
	//private CallableStatement cstmt;

	@Override
	public List<User> getAllUsers()
	{
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			sql = "SELECT * FROM users";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				User temp = new User();
				
				temp.setUserId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				
				users.add(temp);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserByUserId(int userId)
	{
		User user = new User();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			
			sql = "SELECT * FROM users WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User addUser(User newUser)
	{
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			//AccountDAOImpl accountDao = new AccountDAOImpl();
			//Account temp = new Account();
			
			conn.setAutoCommit(false);
			
			//sql = "{CALL add_user(?, ?, ?, ?)}";
			
			String[] key = new String[1];
			key[0] = "user_id";
			
			/*cstmt = conn.prepareCall(sql);
			
			cstmt.setString(1, newUser.getUserName());
			cstmt.setString(2, newUser.getPassword());
			cstmt.setString(3, newUser.getFirstName());
			cstmt.setString(4, newUser.getLastName());
			//cstmt.executeUpdate();
			
			int changeRows = cstmt.executeUpdate();*/
			//rs = cstmt.getGeneratedKeys();
			
			//sql = "SELECT user_id FROM users WHERE username = ?, u_password = ?, "
					//+ "first_name = ?, last_name = ?;";
			sql = "INSERT INTO users (username, u_password, first_name, last_name) "
					+ "VALUES(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstName());
			pstmt.setString(4, newUser.getLastName());
			int changeRows = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			
			if(changeRows > 0)
			{
				while(rs.next())
				{
					newUser.setUserId(rs.getInt(1));
				}
				
				//temp = accountDao.getNewAccount(newUser.getUserId());
				
				//newUser.getAccounts().add(temp);
				
				conn.commit();
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return newUser;
	}

	@Override
	public int updateUser(int userId, User updatedUser)
	{
		int changeRows = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			sql = "UPDATE users SET username = ?, u_password = ?, "
					+ "first_name = ?, last_name = ? WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatedUser.getUserName());
			pstmt.setString(2, updatedUser.getPassword());
			pstmt.setString(3, updatedUser.getFirstName());
			pstmt.setString(4, updatedUser.getLastName());
			pstmt.setInt(5, userId);
			
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
	public int removeUser(int userId)
	{
		int changeRows = -1;
		
		UserDAOImpl userDao = new UserDAOImpl();
		
		if (userDao.hasAccounts(userId))
		{
			System.out.println("This User has active Accounts and cannot be removed!");
			System.out.println("Please remove your accounts and try again.");
			
			return changeRows;
		}
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection())
		{
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM users WHERE user_id = ?";
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
	
	@Override
	public boolean hasUsers()
	{
		List<User> users = getAllUsers();
		
		if (users.isEmpty())
		{
			return false;
		}
		
		return true;
	}

	@Override
	public boolean hasAccounts(int userId)
	{
		AccountDAOImpl accountDao = new AccountDAOImpl();
		List<Account> accounts = accountDao.getAccountsByUserId(userId);
		
		if (accounts.isEmpty())
		{
			return false;
		}
		
		return true;
	}
}