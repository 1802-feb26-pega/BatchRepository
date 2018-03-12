package com.bank.dao;

import java.util.List;
import com.bank.objects.User;

public interface UserDAO {
	public List<User> getAllUsers();
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public User addUser(User newUser);
	public int updateUser(int id, User updatedUser);
}
