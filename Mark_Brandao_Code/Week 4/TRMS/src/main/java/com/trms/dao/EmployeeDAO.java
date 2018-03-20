package com.trms.dao;

import java.util.List;

import com.trms.pojos.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByEmail(String email);
	public Employee getEmployeeByName(String firstname, String lastname);
	public Employee addEmployee(Employee newEmployee);
	public Employee updateEmployee(Employee updatedEmployee);
	public Employee getEmployeeSupervisor(int employeeId);
	public boolean employeeExists(String email);
	public boolean employeeExists(String firstname, String lastname);
	public boolean isValidPassword(String email, String password);
}
