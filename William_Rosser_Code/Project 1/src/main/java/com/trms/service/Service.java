package com.trms.service;

import com.trms.dao.EmployeeDao;
import com.trms.dao.EmployeeDaoImpl;
import com.trms.pojos.User;

public class Service {
	
	static EmployeeDao eDao = EmployeeDaoImpl.getInstance();

	public static User login(String username, String password) {
		System.out.println(username + " service username");
		User user = eDao.getEmployee(username);
		if (user == null) return null;
		else if (user.getPassword().equals(password)) {
			return user;
		} else return null;
	}
	
	public User addUser(User u) {
		return eDao.addUser(u);
	}
}
