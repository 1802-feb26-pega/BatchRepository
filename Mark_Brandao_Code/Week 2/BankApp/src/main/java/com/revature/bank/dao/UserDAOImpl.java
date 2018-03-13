package com.revature.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.User;
import com.revature.bank.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				User temp = new User();
				temp.setUsername(rs.getString(5));
				temp.setFirstname(rs.getString(2));
				temp.setLastname(rs.getString(3));
				temp.setPassword(rs.getString(4));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(int userId) {
		User user = new User();
		user.setUserId(userId);
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM users WHERE userid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user.setUsername(rs.getString(5));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setFirstname(rs.getString(2));
				user.setLastname(rs.getString(3));
				user.setPassword(rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	@Override
	public User addUser(User newUser) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "INSERT INTO users (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
			
			String[] key = new String[1];
			key[0] = "userid";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getFirstname());
			pstmt.setString(3, newUser.getLastname());
			pstmt.setString(4, newUser.getPassword());
			
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				while(rs.next()){
					newUser.setUserId(rs.getInt(1));
				}	
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newUser;
	}

	@Override
	public int updateUser(int userId, User updatedUser) {
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			
			String sql = "UPDATE users " +
						 "SET username = ?, firstname = ?, lastname = ?, password = ?" +
						 "WHERE userid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(5, updatedUser.getUsername());
			pstmt.setString(2, updatedUser.getFirstname());
			pstmt.setString(3, updatedUser.getLastname());
			pstmt.setString(4, updatedUser.getPassword());
			pstmt.setInt(1, updatedUser.getUserId());
			
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
	public int removeUser(int userId) {
		if(hasAccount(userId)) { 
			System.out.println("User cannot be deleted!");
			System.out.println("Remove their accounts and try again.");
			return -1;
		}
		
		int rowsAffected = -1;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM users WHERE userid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
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
	public boolean hasAccount(int userId) {
		UserAccountDAO uadao = new UserAccountDAOImpl();
		List<Integer> userAccounts =  uadao.getAccountIDByUserID(userId);
		
		if(userAccounts.isEmpty()) {
			return false;
		}
		
		return true;
	}

	@Override
	public double getTotalBalance(User user) {
		double total = 0D;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "{CALL total_balance(?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, user.getUserId());
			cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);
			cstmt.executeUpdate();
			total = cstmt.getDouble(2);
		} catch (SQLException e) {
			// do nothing
		}
		return total;
	}

}
