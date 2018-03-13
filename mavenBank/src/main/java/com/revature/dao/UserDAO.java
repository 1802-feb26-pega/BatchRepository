package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDAO {
	
	public List<User> getAllUsers();
	public User getUserById(int userId);
	public User getUserByName(String username);
	public User addUser(User newUser);
	public int updateUser(User updatedUser);
	public int deleteUser(int userId);
	public boolean hasAccount(int userId);

}
