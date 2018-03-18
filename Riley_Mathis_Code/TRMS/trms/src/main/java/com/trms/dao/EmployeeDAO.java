package com.trms.dao;

import com.trms.pojos.Employee;

public interface EmployeeDAO {
	public Employee getEmployeeById(int employeeId);
	public Employee getEmployeeByUsername(String username);
}
