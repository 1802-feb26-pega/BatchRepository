package com.revature.dao;

import java.util.List;

import com.revature.pojos.Employee;

public interface employeeDAO {

	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int id);
	public Employee getEmployeeByEmail(String email);
	public Employee addEmployee(Employee newEmp);
}
