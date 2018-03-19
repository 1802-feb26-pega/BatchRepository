package com.revature.trms.dao;

import java.util.Collection;

import com.revature.trms.pojos.Employee;

public interface EmployeeDAO
{
	// Get Employee for LogIn
	public Employee getUserByEmail(String email);
	
	// Get Employee's Direct Supervisor
	public Employee getEmployeeSupervisor(Integer empId);
	
	// Get Employee By Employee ID for Department Head
	public Employee getEmployeeByEmployeeId(Integer empId);
	
	// Get Direct Supervisor's Employees
	public Collection<Employee> getDSEmployees(Integer empId);
	
	// Get Department Head's Employees
	public Collection<Employee> getDHEmployees(Integer empId);

}
