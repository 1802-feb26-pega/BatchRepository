package com.revature.trms.service;

import java.util.List;

import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.dao.ReimbursementRequestDAOImpl;
import com.revature.trms.pojos.Employee;
import com.revature.trms.pojos.ReimbursementRequest;

public class Service {

	static EmployeeDAOImpl empDao = new EmployeeDAOImpl();
	static ReimbursementRequestDAOImpl rrDao = new ReimbursementRequestDAOImpl();
	
	public Employee login(String email, String password) {
		Employee emp = empDao.getEmployeeByEmail(email);
		if(emp == null) {
			return null;
		}
		else if(emp.getPassword().equals(password)) {
			return emp;
		}
		else {
			return null;
		}
	}
	
	public boolean emailExists(String email) {
		Employee emp = empDao.getEmployeeByEmail(email);
		if(emp == null) return false;
		else return true;
	}
	
	public boolean supervisorExists(String fName, String lName) {
		Employee emp = empDao.getSupervisorByName(fName, lName);
		if(emp == null) return false;
		else return true;
	}
	
	public int getSupervisorId(String fName, String lName) {
		Employee supervisor = empDao.getSupervisorByName(fName, lName);
		return supervisor.getEmpId();
	}
	
	public Employee addEmployee(Employee newEmp) {
		return empDao.addEmployee(newEmp);
	}
	
	public ReimbursementRequest addReimbursmentRequest(ReimbursementRequest request) {
		return rrDao.addReimbursementRequest(request);
	}
	
	public Employee updateEmployee(Employee updatedEmp) {
		return empDao.updateEmployee(updatedEmp);
	}
	
	public List<ReimbursementRequest> getAllPendingRequests(Employee emp){
		return rrDao.getAllPendingRequests(emp);
	}
}
