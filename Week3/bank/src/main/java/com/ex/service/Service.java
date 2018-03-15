package com.ex.service;

import com.bank.dao.DAO;
import com.bank.dao.DAOImpl;
import com.bank.pojos.User;

public class Service {
	
	static DAO dao = new DAOImpl();
	
	public User login(String username, String password) {
		User user = dao.getUser(username);
		if(user== null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		}
		else return null;
	}
	
	public User addUser(User u) {
		return dao.addUser(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword());
	}

}
