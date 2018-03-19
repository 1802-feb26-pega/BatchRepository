package com.reimb.dao;

import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;

public interface DAO {

	public Employee getEmployee(String username);
	public Employee getEmployeeById(int id);
	public Employee addEmployee(String fn, String ln, String email, String pass);
	public Reimbursement addReimbursement();
	public Reimbursement getReimbursement(int id);
	public void updateReimbursement(int id, double amt);
}
