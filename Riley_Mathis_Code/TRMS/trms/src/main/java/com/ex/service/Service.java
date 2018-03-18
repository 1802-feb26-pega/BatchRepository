package com.ex.service;

import java.util.ArrayList;

import com.trms.dao.EmployeeDAO;
import com.trms.dao.EmployeeDAOImpl;
import com.trms.pojos.Event;
import com.trms.pojos.Employee;

public class Service {
	
	static EmployeeDAO empDao = new EmployeeDAOImpl();
	
	public Employee login(String username, String password) {
		Employee employee = empDao.getEmployeeByUsername(username);
		if(employee== null) return null;
		else if (employee.getPassword().equals(password)) {
			return employee;
		}
		else return null;
	}
	
//	public User addUser(User u) {
//		return dao.addUser(u.getFirstname(), u.getLastname(), u.getEmail(), u.getPassword());
//	}
	
	public boolean usernameExists(String username) {
		Employee employee = empDao.getEmployeeByUsername(username);
		if(employee == null) return false;
		else return true;
	}
	
//	public ArrayList<Account> getAccounts (User u){
//		return dao.getAccountsByUser(u);
//	}
}
