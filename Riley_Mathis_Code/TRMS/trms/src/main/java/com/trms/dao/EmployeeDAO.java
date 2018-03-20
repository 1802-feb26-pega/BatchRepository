package com.trms.dao;

import java.util.ArrayList;

import com.trms.pojos.Employee;
import com.trms.pojos.Event;
import com.trms.pojos.Reimbursement;

public interface EmployeeDAO {
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByUsername(String username);
	public ArrayList<Event> getEventsByEmployee(Employee employee);
	public ArrayList<Reimbursement> getReimbursementsByPending();
	public ArrayList<Reimbursement> getReimbursementsByEmployee(Employee employee);
}
