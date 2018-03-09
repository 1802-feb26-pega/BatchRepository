package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.User;

public interface UserDAO {
	
	/**
	 * Returns a list of all users.
	 * @return A list of users. List will be empty if no users can be loaded.
	 * Does not return null.
	 */
	public List<User> getAllUsers();
	
	/**
	 * Returns the user with the given id, or null if the user doesn't exist.
	 * @param id the user id.
	 * @return a User object with the corresponding information, or null if it cannot be found.
	 */
	public User getUserByID(int id);
	
	/**
	 * Returns the user with the given username, or null if the user doesn't exist.
	 * @param username the username of the user.
	 * @return a User object with the corresponding information, or null if it cannot be found.
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Returns the user with the given email, or null if the user doesn't exist.
	 * @param username the email of the user.
	 * @return a User object with the corresponding information, or null if it cannot be found.
	 */
	public User getUserByEmail(String email);
	
	/**
	 * Adds a new user to the database.
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * Updates an existing user with updated data. 
	 * 
	 * Updates first name, middle initial, last name, and password.
	 * Does not update the email or the username.
	 * 
	 * User must have a valid user_id.
	 * @param updated - the updated user.
	 * @return int >= 1 if success, 0 if failure, -1 if invalid ID.
	 */
	public int updateUser(User updated);
	
	/**
	 * NOT IMPLEMENTED;
	 * @param id
	 * @return -1
	 */
	public int removeUser(int id);
}
