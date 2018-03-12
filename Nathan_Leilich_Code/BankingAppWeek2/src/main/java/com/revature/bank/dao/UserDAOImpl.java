package com.revature.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import com.revature.bank.pojo.User;
import com.revature.bank.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO{

	@Override
	public void updateUser(User user) throws SQLException{

		int rowsAffected = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "UPDATE bank_user SET user_password = ? WHERE u_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getUserID());
			rowsAffected = pstmt.executeUpdate();
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(rowsAffected != 1) {
			
			throw new SQLException("user was not updated");
		}//if
	}

	@Override
	public Collection<String> getAllUserNames() throws SQLException {

		HashSet<String> userNames = new HashSet<String>();
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT username FROM bank_user";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				userNames.add(rs.getString(1));
			}//while
			
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(userNames.size() == 0) {
			
			throw new SQLException("no users");
		}//if
		
		return userNames;
	}

	@Override
	public User getUser(String userName, String password) throws SQLException{
		
		User user = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT * FROM bank_user WHERE username = ? AND user_password = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(user == null) {
			
			throw new SQLException("user does not exist");
		}
		
		return user;
	}

	@Override
	public User addUser(User newUser) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "INSERT INTO bank_user(username, user_password) VALUES(?, ?)";
			String[] key = new String[1];
			key[0] = "u_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newUser.getUserName());
			pstmt.setString(2, newUser.getPassword());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rowsAffected > 0) {
				
				while(rs.next()) {
					
					newUser.setUserID(rs.getInt(1));
				}//while
			}//if
		}//try
		catch (SQLException e) {
			
			e.printStackTrace();
		}//catch
		
		return newUser;
	}
	
	

}
