package com.ex.service;

import com.reimb.dao.DAO;
import com.reimb.dao.DAOImpl;
import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;

public class Service {
	
	static DAO dao = new DAOImpl();
	//static MessageDAO mdao = new MDAOImp();
	
	public static Employee login(String username, String password) {
		Employee emp = dao.getEmployee(username);
		if(emp == null) return null;
		else if (emp.getPassword().equals(password)) {
			return emp;
		}
		else return null;
	}
	
	public Employee addEmployee(Employee e) {
		return dao.addEmployee(e.getFirstName(), e.getLastName(), e.getEmail(), e.getPassword());
	}
	
	public Reimbursement createReimbursement(Employee e) {
		//TODO: change this to right args
		return dao.addReimbursement();
	}
	
	public Boolean emailExists(String email) {
		Employee u = dao.getEmployee(email);
		if(u == null) return false;
		else return true;
	}
}
