package com.trms.service;

import java.util.List;

import com.trms.dao.EmployeeDAO;
import com.trms.dao.ReimbursementFormDAO;
import com.trms.daoimpl.EmployeeDAOImpl;
import com.trms.daoimpl.ReimbursementFormDAOImpl;
import com.trms.pojos.Employee;
import com.trms.pojos.ReimbursementForm;

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


	public List<ReimbursementForm> getForms(Employee employee) {
		ReimbursementFormDAO rfDao = new ReimbursementFormDAOImpl();
		List<ReimbursementForm> reims = rfDao.getReimsByEmployeeId(employee.getEmployeeId());
		return reims;
	}


	public boolean nameExists(String firstname, String lastname) {
		EmployeeDAO employeeDao = new EmployeeDAOImpl();
		boolean exists = employeeDao.employeeExists(firstname, lastname);
		return exists;
	}
}
