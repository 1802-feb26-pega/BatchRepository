package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.User;

/**
* <h1> UserDAO </h1> 
* The UserDAO Interface lists all the methods used by UserDAOImpl.
* 
* @author Katelyn Peterson
* @version 1.0
* @since 03-09-2018 
*
*/
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