package com.revature.bank.dao;

import java.sql.CallableStatement;
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

	
	/*
	 * Updates a single entry in the bank_user table using a User
	 * object.  
	 * Throws an SQLException if the user was not updated.
	 */
	@Override
	public void updateUser(User user) throws SQLException{
		
		System.out.println("processing...\n_______________________");
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

	
	/*
	 * Returns a collection object containing all entries in 
	 * the username column of the bank_user table.  
	 * If there are no entries in the bank_User table, an
	 * exception is thrown instead
	 */
	@Override
	public Collection<String> getAllUserNames() throws SQLException {

		System.out.println("processing...\n_______________________");
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

	
	/*
	 * Returns a new User object containing the input username
	 * and password.
	 * If no entry exists in the bank_user table with the input
	 * uername and password, an exception is thrown instead
	 */
	@Override
	public User getUser(String userName, String password) throws SQLException{
		
		System.out.println("processing...\n_______________________");
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

	
	/*
	 * Inserts a new row entry into the bank_user table using
	 * data provided by the input User object.  Once inserted,
	 * the user object is updated with generated keys fetched
	 * from the database and returned.
	 */
	@Override
	public User addUser(User newUser) {

		System.out.println("processing...\n_______________________");
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
	
	
	/*
	 * deletes an entry from the bank_user table that has
	 * an entered userID
	 */
	public void deleteUser(int userID) throws SQLException {
		
		System.out.println("processing...\n_______________________");
		int rowsAffected = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "CALL delete_user(?)";
			
			CallableStatement cstmt = conn.prepareCall(sql);
			cstmt.setInt(1, userID);
			rowsAffected = cstmt.executeUpdate();
			
		}//try
		catch(SQLException e) {
			
			e.printStackTrace();
		}//catch
		if(rowsAffected == 0) {
			
			throw new SQLException("user with userId of " + userID + " exists");
		}//if
	}//deleteUser()

}//class
