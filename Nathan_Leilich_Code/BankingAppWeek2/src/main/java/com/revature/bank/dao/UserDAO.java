package com.revature.bank.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.revature.bank.pojo.User;

public interface UserDAO {

	public void updateUser(User user) throws SQLException;
	public Collection<String> getAllUserNames() throws SQLException;
	public User getUser(String userName, String password) throws SQLException;
	public User addUser(User newUser);
}
