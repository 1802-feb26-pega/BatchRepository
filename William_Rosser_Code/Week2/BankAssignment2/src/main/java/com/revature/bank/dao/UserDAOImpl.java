package com.revature.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.bank.pojos.User;
import com.revature.bank.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl uDAO = null;
	
	private UserDAOImpl() {
		uDAO = this;
	}
	
	public static synchronized UserDAOImpl getInstance() {
		if (uDAO == null) uDAO = new UserDAOImpl();
		return uDAO;
	}
	
	private User resultsToUser(ResultSet rs, boolean callNext) throws SQLException {
		if (!callNext || rs.next()) {
			User temp = new User(rs.getInt(1));
			temp.setFirstName(rs.getString(2));
			temp.setLastName(rs.getString(4));
			temp.setMiddleInitial(rs.getString(3));
			temp.setUsername(rs.getString(5));
			temp.setPassword(rs.getString(6));
			temp.setEmail(rs.getString(7));
			return temp;
		}
		return null;
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				User temp = resultsToUser(rs, false);
				if (temp != null) users.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}


	public User getUserByID(int id) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users WHERE ? = user_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			return resultsToUser(rs, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByUsername(String username) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users WHERE ? = username";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			return resultsToUser(rs, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByEmail(String email) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM users WHERE ? = email";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			return resultsToUser(rs, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int addUser(User user) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO USERS(FIRST_NAME, MIDDLE_INITIAL,"
					+ " LAST_NAME, USERNAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getMiddleInitial());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getUsername());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getEmail());
			int val = pstmt.executeUpdate();
			if (val > 0) {
				//System.out.println("Committing.");
				conn.commit();
				return val;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username or email already exists.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int updateUser(User updated) {
		int id = updated.getUserID();
		if (id <= 0) return -1;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			conn.setAutoCommit(false);
			String sql = "UPDATE users SET first_name = ?, middle_initial = ?, last_name = ?, password = ? WHERE ? = user_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updated.getFirstName());
			pstmt.setString(2, updated.getMiddleInitial());
			pstmt.setString(3, updated.getLastName());
			pstmt.setString(4, updated.getPassword());
			pstmt.setInt(5, id);
			int val = pstmt.executeUpdate();
			if (val > 0) {
				System.out.println("Committed");
				conn.commit();
				return val;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int removeUser(int id) {
		return -1;
	}

}
