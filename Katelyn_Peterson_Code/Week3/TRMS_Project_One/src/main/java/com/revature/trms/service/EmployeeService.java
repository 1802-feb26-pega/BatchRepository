package com.revature.trms.service;

import com.revature.trms.dao.EmployeeDAO;
import com.revature.trms.dao.EmployeeDAOImpl;
import com.revature.trms.pojos.Employee;

public class EmployeeService
{
	private static EmployeeDAO empDao = new EmployeeDAOImpl();
	
	public Employee login(String email, String password)
	{
		Employee emp = empDao.getUserByEmail(email);
		//System.out.println("Service: " + emp);
		
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
