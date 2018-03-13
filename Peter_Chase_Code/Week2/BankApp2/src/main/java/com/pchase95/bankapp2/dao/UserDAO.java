package com.pchase95.bankapp2.dao;

import java.util.List;

import com.pchase95.bankapp2.pojos.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserById(long userId);
	public User getUserByName(String userName);
	public User getUserByEmail(String userEmail);
	public boolean addUser(User newUser);
	public boolean updateUser(long userId, User updatedUser);
	public boolean removeUser(long userId);
}
