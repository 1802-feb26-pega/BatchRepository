package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.User;

public interface UserDAO
{
	public List<User> getAllUsers();
	public User getUserByUserId(int userId);
	public User addUser(User newUser);
	public int updateUser(int userId, User updatedUser);
	public int removeUser(int userId);
	public boolean hasUsers();
	public boolean hasAccounts(int userId);
}