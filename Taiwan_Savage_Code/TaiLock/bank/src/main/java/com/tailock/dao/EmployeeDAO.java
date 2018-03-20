package com.tailock.dao;

import com.tailock.pojos.Employee;

public interface EmployeeDAO {

	Employee getEmployee(String username);

	int getMaxEmpId();

}
