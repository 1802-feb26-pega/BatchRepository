package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Account;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDAOImpl implements UserDAO{

	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User temp = new User();
				temp.setUserId(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstname(rs.getString(4));
				temp.setLastname(rs.getString(5));
				users.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User getUserById(int userId) {

		User user = new User();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Users WHERE UserId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User getUserByName(String username) {

		User user = new User();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "SELECT * FROM Users WHERE Username = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstname(rs.getString(4));
				user.setLastname(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;

	}

	public User addUser(User newUser) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "INSERT INTO Users(Username, Password, Firstname, Lastname) "
					+ "VALUES (?, ?, ?, ?)";

			String[] key = new String[1];
			key[0] = "UserId";

			PreparedStatement pstmt = conn.prepareStatement(sql, key);
			pstmt.setString(1, newUser.getUsername());
			pstmt.setString(2, newUser.getPassword());
			pstmt.setString(3, newUser.getFirstname());
			pstmt.setString(4, newUser.getLastname());
			int rowsAffected = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rowsAffected > 0) {
				while (rs.next()) {
					newUser.setUserId(rs.getInt(1));
				}

				conn.commit();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newUser;
	}

	public int updateUser(User updatedUser) {

		int rowsAffected = -1;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "UPDATE Users SET Username = ?, Password = ?, "
					+ "Firstname = ?, Lastname = ? WHERE userid = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatedUser.getUsername());
			pstmt.setString(2, updatedUser.getPassword());
			pstmt.setString(3, updatedUser.getFirstname());
			pstmt.setString(4, updatedUser.getLastname());
			pstmt.setInt(5, updatedUser.getUserId());
			rowsAffected = pstmt.executeUpdate();

			conn.commit();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsAffected;
	}

	public int deleteUser(int userId) {

		UserDAO userDao = new UserDAOImpl();

		if (userDao.hasAccount(userId)) {
			System.out.println("You cannot delete this user!");
			System.out.println("Remove all accounts before deleting this user");
			return -1;
		}

		int rowsAffected = -1;

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			conn.setAutoCommit(false);

			String sql = "DELETE FROM Users WHERE UserId = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rowsAffected = pstmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowsAffected;
	}

	public boolean hasAccount(int userId) {

		AccountDAO accountDao = new AccountDAOImpl();
		List<Account> userAccounts = accountDao.getUserAccounts(userId);

		if (userAccounts.isEmpty())
			return false;

		return true;
	}

}
