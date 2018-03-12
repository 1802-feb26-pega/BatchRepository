package com.revature.bankapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bankapp.driver.UserInterface;
import com.revature.bankapp.pojos.User;
import com.revature.bankapp.util.ConnectionFactory;

public class UserDAOImplement implements UserDAO{
	
	private UserInterface userInterface;
	public UserDAOImplement (UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	@Override
	public void addUser(User newUser) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO users(user_id, user_name, pwd) VALUES (seq_users.NEXTVAL, ?, ?)";
			
			String[] key = new String[1]; // for key auto incrementing, need to pass in an array for the second argument of overloaded prepareStatement method
			key[0] = "user_id";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newUser.getUser_name());
			pstmt.setString(2, newUser.getPwd());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if (rowsAffected > 0) {
				while(rs.next()) {
					newUser.setUser_id(rs.getInt(1));
				}
				conn.commit();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//return newUser;
	}

	@Override
	public User getUser(String user_name) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "SELECT * FROM users WHERE user_name = ?";
			
			User user = new User();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				user.setUser_id(rs.getInt(1));
				user.setUser_name(rs.getString(2));
				user.setPwd(rs.getString(3));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getUserName(String user_name) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "SELECT user_name FROM users WHERE user_name = ?";
			
			User user = new User();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			
			ResultSet rs = pstmt.executeQuery();
			
			String username = null;
			
			while (rs.next()) {
				username = rs.getString(1);
			}
			
			return username;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
