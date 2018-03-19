package com.trms.service;

import com.trms.dao.EmployeeDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.pojos.Employee;

public class Service {
	
	public boolean emailExists(String email) {
		EmployeeDAO employeeDao = new EmployeeDAOImpl();
		boolean exists = employeeDao.employeeExists(email);
		return exists;
	}
	
	
	public Employee addEmployee(Employee newEmployee) {
		EmployeeDAO employeeDao = new EmployeeDAOImpl();
		newEmployee = employeeDao.addEmployee(newEmployee);
		return newEmployee;
	}


	public Employee login(String email, String password) {
		EmployeeDAO employeeDao = new EmployeeDAOImpl();
		boolean valid = employeeDao.isValidPassword(email, password);
		if(valid) {
			Employee employee = employeeDao.getEmployeeByEmail(email);
			return employee;
		} else {
			return null;
		}
	}
	

}
