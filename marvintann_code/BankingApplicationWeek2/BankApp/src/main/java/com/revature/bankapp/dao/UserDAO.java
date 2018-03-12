package com.revature.bankapp.dao;

import com.revature.bankapp.pojos.User;

public interface UserDAO {
	
	public void addUser(User newUser);
	public User getUser(String user_name);
	public String getUserName(String user_name);
	
}
