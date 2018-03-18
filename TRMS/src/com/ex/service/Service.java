package com.ex.service;

import com.reimb.dao.DAO;
import com.reimb.dao.DAOImpl;

public class Service {
	
	static DAO dao = new DAOImpl();
	
	public static User login(String username, String password) {
		User user = dao.getUser(username);
		if(user == null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
	
	public User addUser(User u) {
		return dao.addUser(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword());
	}
	
	public Account createAccount(User u) {
		return dao.createAccount(u, 0);
	}
	
	public Boolean emailExists(String email) {
		User u = dao.getUser(email);
		if(u == null) return false;
		else return true;
	}
}
