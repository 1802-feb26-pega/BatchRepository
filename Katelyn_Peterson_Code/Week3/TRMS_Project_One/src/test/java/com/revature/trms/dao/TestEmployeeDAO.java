package com.revature.trms.dao;

import static org.junit.Assert.assertTrue;

import com.revature.trms.pojos.Employee;

public class TestEmployeeDAO
{
	static private EmployeeDAOImpl empDao = new EmployeeDAOImpl();

	public void testGetAllUsers()
	{
		boolean find = false;
		
		/*List<User> users = new ArrayList<>();
		User temp = new User("Tin", "1234", "Tin", "Moria");
		temp.setUserId(1);
		users.add(temp);*/
		
		Employee testUser = empDao.getUserByEmail("parker@sru.gov");
		
		if(testUser.getFirstName().matches("Greg"))
		{
			find = true;
		}
		
		assertTrue(find);
		
		//assertEquals(users, testUsers);
	}

}
