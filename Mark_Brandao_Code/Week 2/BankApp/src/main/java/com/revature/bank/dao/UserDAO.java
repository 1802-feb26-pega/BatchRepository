package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserById(int userId);
	public User getUserByUsername(String username);
	public User addUser(User newUser);
	public int updateUser(int userId, User updatedUser);
	public int removeUser(int userId);
	public boolean hasAccount(int userId);
}
