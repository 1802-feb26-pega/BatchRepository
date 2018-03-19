package com.revature.trms.dao;

import java.util.List;

import com.revature.trms.pojos.Employee;

public interface EmployeeDAO {

	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByEmail(String email);
	public Employee addEmployee(Employee newEmp);
	public Employee getSupervisorByName(String fName, String lName);
	public Employee updateEmployee(Employee updatedEmp);
}
