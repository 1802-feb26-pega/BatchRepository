package com.reimb.dao;

import java.sql.Date;
import java.util.ArrayList;

import com.reimb.pojos.Employee;
import com.reimb.pojos.Reimbursement;

public interface DAO {

	public Employee getEmployee(String username);
	public Employee getEmployeeById(int id);
	public Employee addEmployee(String fn, String ln, String email, String pass, int dept, 
			Date DOB, String address, String city, String state);
	public Reimbursement addReimbursement(int id);
	public ArrayList<Reimbursement> getReimbursementsById(int id);
	public void updateReimbursement(int id, double amt);
}
