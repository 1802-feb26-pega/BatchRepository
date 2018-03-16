package com.revature.trms.service;

import com.revature.trms.dao.EmployeeDAO;
import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.pojos.Employee;

public class EmployeeService
{
	static EmployeeDAO empdao = new EmployeeDAOImpl();
	
	public Employee login(String email, String password)
	{
		Employee emp = empdao.getUserByEmail(email);
		
		if (emp == null)
		{
			return null;
		}
		else if(emp.getPassword().equals(password))
		{
			return emp;
		}
		else
		{
			return null;
		}
	}
}
