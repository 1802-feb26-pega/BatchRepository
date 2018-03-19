package com.revature.trms.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.trms.pojos.Employee;

public class TestEmployeeDAO
{
	static private EmployeeDAOImpl empDao = new EmployeeDAOImpl();
	
	@Test
	public void testGetAllUsers()
	{
		boolean find = false;
		
		Employee testUser = empDao.getUserByEmail("parker@sru.gov");
		
		if(testUser.getFirstName().matches("Greg"))
		{
			find = true;
		}
		
		assertTrue(find);
	}

}
