package com.trms.dao;

import java.util.List;

import com.trms.pojos.Employee;

public interface EmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByEmail(String email);
	public Employee addEmployee(Employee newEmployee);
	public int updateEmployee(Employee updatedEmployee);
	public Employee getEmployeeSupervisor(int employeeId);
	public boolean employeeExists(String email);
	public boolean isValidPassword(String email, String password);
}
