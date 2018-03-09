package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserByID(int id);
	public User getUserByUsername(String username);
	public User getUserByEmail(String email);
	public int addUser(User user);
	public int updateUser(int id, User updated);
	public int removeUser(int id);
}
