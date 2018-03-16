package com.revature.trms.dao;

import com.revature.trms.pojos.Employee;

public interface EmployeeDAO
{

	public Employee getUserByEmail(String email);

}
