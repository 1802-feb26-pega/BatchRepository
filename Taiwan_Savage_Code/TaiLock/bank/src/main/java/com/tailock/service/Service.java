package com.tailock.service;

import java.util.List;

import com.tailock.dao.EmployeeDAO;
import com.tailock.dao.EmployeeDAOImpl;
import com.tailock.dao.RequestDao;
import com.tailock.pojos.Employee;
import com.tailock.pojos.Request;

public class Service {

	static EmployeeDAO empdao = new EmployeeDAOImpl();
	static RequestDao reqdao = new RequestDao();
	
	//for employee login
	public Employee login(String email, String password) {
		Employee employee = empdao.getEmployee(email);
		if (employee == null) {
			System.out.println("No user");
			return null;
		}
		else if (employee.getPassword().equals(password)) {
			return employee;
		}
		else return null;
	}
	
	//get max Id so that you can generate the next
	public int getMaxEmployeeId() {
		int max = empdao.getMaxEmpId();
		return max;
	}
	
	//make a username from email
	public String makeEmpUsername(Employee emp) {
		String email = emp.getEmail();
		int index = email.indexOf('@');
		String uname = email.substring(0,index);
		return uname;
	}
	
	//register an employee
	public Employee register(String fn, String ln, String email, String pw) {
		Employee emp = new Employee(fn, ln, email, pw);
		
		emp.setEmployeeId(getMaxEmployeeId() + 1);
		emp.setUsername(makeEmpUsername(emp));
		
		EmployeeDAOImpl edaoi = new EmployeeDAOImpl();
		edaoi.insertEmployee(emp);
		
		return emp;
	}
	
	public List<Request> getUserRequests(Employee emp) {
		return reqdao.getAllUserRequests(emp);
	}
	
}

