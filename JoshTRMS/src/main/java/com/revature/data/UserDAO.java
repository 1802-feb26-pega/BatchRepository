package com.revature.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.backend.User;
import com.revature.backend.UserRole;

public class UserDAO {

	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	//Method that gets all the users
	public List<User> getAll() throws SQLException, ClassNotFoundException {
		List<User> results = new ArrayList<User>();
		String sql = "SELECT * FROM USERS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, results);
		return results;
	}
	//Method that maps the rows for User DAO
	private void mapRows(ResultSet rs, List<User> results) throws SQLException {

		while (rs.next()) {
			// get values from rows
			int id = rs.getInt("u_id");
			String username = rs.getString("USERNAME");
			String firstName = rs.getString("FIRSTNAME");
			String lastName = rs.getString("LASTNAME");
			String email = rs.getString("EMAIL");
			UserRole role = new UserRole();
			role.setId(rs.getInt("TRMS_USER_ROLE_ID"));

			// create user object using those values
			User obj = new User(id, username, firstName, lastName, email, role);

			// add objects to list and print to console
			results.add(obj);
			System.out.println(obj);
		}
	}
	//Method that gets the user's login info
	public User getUserLoginInfo(String username) throws SQLException {
		String sql = "SELECT *" + " FROM TRMS_USERS" + " WHERE TRMS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			UserRole role;
			if (rs.getInt("USER_ROLE_ID") == 2) {
				role = new UserRole(2, "Employee");
			} else {
				role = new UserRole(1, "Direct Supervisor");
			}
			User user = new User(rs.getInt("TRMS_USERS_ID"), rs.getString("TRMS_USERNAME"),
					rs.getString("USER_FIRST_NAME"), rs.getString("USER_LAST_NAME"), rs.getString("USER_EMAIL"), role);
			System.out.println("Get user login infor for user ID: " + user.getUser_id());
			System.out.println(user);
			return user;
		}
		return null;
	}
	//Method for setting a user
	User setUser(ResultSet rs, boolean isAuthor) throws SQLException {
		int id;
		String username, lastName, firstName, email;
		if (isAuthor) {
			System.out.println("Do we get to setUser() is author?");
			id = rs.getInt("TRMS_USERS_ID");
			System.out.println("============ Do we get ID?" + id);
			username = rs.getString("TRMS_USERNAME");
			System.out.println("============ Do we get username?" + username);
			firstName = rs.getString("USER_FIRST_NAME");
			System.out.println("============ Do we get firstName?" + firstName);
			lastName = rs.getString("USER_LAST_NAME");
			System.out.println("============ Do we get lastName?" + lastName);
			email = rs.getString("USER_EMAIL");
			System.out.println("============ Do we get email?" + email);
		} else {
			System.out.println("Do we get to setUser() not author?");
			id = rs.getInt("TRMS_USERS_ID");
			username = rs.getString("TRMS_USERNAME");
			lastName = rs.getString("USER_LAST_NAME");
			firstName = rs.getString("USER_FIRST_NAME");
			email = rs.getString("USER_EMAIL");
		}
		User user = new User(id, username, firstName, lastName, email, null);
		System.out.println("setUser(): user: " + user);
		return user;
	}
	//Method that returns the User by ID
	public String getById(int id) throws SQLException {
		String firstName = null;
		String lastName = null;

		String sql = "SELECT USER_FIRST_NAME, USER_LAST_NAME" + " FROM TRMS_USERS" + " WHERE TRMS_USERS_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			firstName = rs.getString("USER_FIRST_NAME");
			lastName = rs.getString("USER_LAST_NAME");
			System.out.println("User Full Name: " + firstName + " " + lastName + " for User ID: " + id);
			return firstName + " " + lastName;
		}
		return null;
	}
	//Method that gets the User, and returns a User object
	public User getUser(String username) {
		User user = null;
		String sql = "SELECT *" + " FROM TRMS_USERS" + " JOIN TRMS_USER_ROLES"
				+ " ON TRMS_USERS.USER_ROLE_ID = TRMS_USER_ROLES.ERS_USER_ROLE_ID" + " WHERE TRMS_USERNAME = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = constructUser(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	//Method that creates a User object
	private User constructUser(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int id = rs.getInt("TRMS_USERS_ID");
			String username = rs.getString("TRMS_USERNAME");
			String lastName = rs.getString("USER_LAST_NAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String email = rs.getString("USER_EMAIL");
			UserRole role = new UserRole(rs.getInt("USER_ROLE_ID"), rs.getString("USER_ROLE"));
			return new User(id, username, firstName, lastName, email, role);
		}
		return null;
	}
	//Method for getting the User's password, returns password
	public String getPassword(String username) throws SQLException {
		String password = null;
		String sql = "SELECT TRMS_USERNAME, TRMS_PASSWORD" + " FROM TRMS_USERS" + " WHERE TRMS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		if (rs.next())
			password = rs.getString("TRMS_PASSWORD");
		return password;
	}

}