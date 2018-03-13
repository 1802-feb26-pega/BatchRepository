package com.revature.util;

import com.revature.pojo.User;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;

public class Verification {
	
	UserDAO userDao = new UserDAOImpl();

	/*
	 *  Search database to verify if username exists. Return true if username is unique and 
	 *  false is username is not unique.
	 */
	public boolean uniqueUser(User user) {
		
		User check = userDao.getUserByName(user.getUsername());
		
		if (check.getUsername() == null)
			return true;
		else
			return false;
	}
	
	/*
	 *  Search database to verify the input. Return true if username is in the database. False 
	 *  if database returns null
	 */
	public boolean verifyUser(User user) {
		
		User check = userDao.getUserByName(user.getUsername());
		
		if (check.getUsername() == null)
			return false;
		else 
			return true;
	}
	
	/*
	 * Like verifyUser except it verifies that the password is correct. Returns true if password
	 * matches, false if password does not match
	 */
	public boolean verifyPassword(User user) {
		
		User check = userDao.getUserByName(user.getUsername());
		
		if (check != null) {
			if (check.getPassword().equals(user.getPassword())) 
				return true;
			 else 
				return false;
		}
		
		return false;
	}
}
