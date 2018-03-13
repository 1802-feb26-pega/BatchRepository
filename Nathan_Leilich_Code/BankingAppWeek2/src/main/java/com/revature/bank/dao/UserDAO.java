package com.revature.bank.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.revature.bank.pojo.User;

/*
 * Outlines the methods this project will use to
 * access User data from the bank database
 */
public interface UserDAO {

	public void updateUser(User user) throws SQLException;
	public Collection<String> getAllUserNames() throws SQLException;
	public User getUser(String userName, String password) throws SQLException;
	public User addUser(User newUser);
	public void deleteUser(int accountID) throws SQLException;
}
