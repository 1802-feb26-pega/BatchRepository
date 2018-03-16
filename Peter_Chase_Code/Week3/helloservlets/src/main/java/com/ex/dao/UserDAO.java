package com.ex.dao;

import java.util.List;

import com.ex.pojos.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserById(long userId);
	public User getUserByName(String userName);
	public User getUserByEmail(String userEmail);
	public boolean addUser(User newUser);
	public boolean updateUser(long userId, User updatedUser);
	public boolean removeUser(long userId);
}
