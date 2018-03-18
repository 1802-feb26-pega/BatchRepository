package com.trms.service;

import com.trms.dao.EmployeeDao;
import com.trms.dao.EmployeeDaoImpl;
import com.trms.pojos.*;

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

	public static User addUser(User u, int type) {
		switch(type) {
		case 0: eDao.addEmployee((Employee) u); break;
		case 1: eDao.addDirectSupervisor((DirectSupervisor) u); break;
		case 2: eDao.addDepHead((DepartmentHead) u); break;
		case 3: eDao.addBenCo((BenCo) u); break;
		}
		return u;
	}
	
	public static boolean validateUsername(String username) {
		User u = eDao.getEmployee(username);
		System.out.println(username);
		System.out.println(u);
		return u == null;
	}
}
