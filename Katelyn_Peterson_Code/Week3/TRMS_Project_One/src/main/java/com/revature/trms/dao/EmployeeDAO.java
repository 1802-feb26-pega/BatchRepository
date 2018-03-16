package com.revature.trms.dao;

import com.revature.trms.pojos.Employee;

public interface EmployeeDAO
{

	Employee getUserByEmail(String email);

}
