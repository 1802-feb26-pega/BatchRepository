package com.revature.bankapp.dao;

import java.util.List;

import com.revature.bankapp.pojos.User;

public interface UserDAO {

	public List<User> getAllUsers();
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public User addUser(User newUser);
	public int updateUser(int id, User updatedUser);
}
