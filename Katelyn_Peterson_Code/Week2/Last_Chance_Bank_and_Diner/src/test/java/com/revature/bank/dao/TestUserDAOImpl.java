package com.revature.bank.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.revature.bank.pojos.User;

public class TestUserDAOImpl
{
	static private UserDAOImpl userDao = new UserDAOImpl();

	@Test
	public void testGetAllUsers()
	{
		boolean find = false;
		
		/*List<User> users = new ArrayList<>();
		User temp = new User("Tin", "1234", "Tin", "Moria");
		temp.setUserId(1);
		users.add(temp);*/
		
		List<User> testUsers = userDao.getAllUsers();
		
		for (User x : testUsers)
		{
			if (x.getFirstName().matches("Tin"))
			{
				find = true;
			}
		}
		
		assertTrue(find);
		
		//assertEquals(users, testUsers);
	}
	
	@Test
	public void testGetUserByUserId()
	{
		User temp = new User("Tin", "1234", "Tin", "Moria");
		temp.setUserId(1);
		
		User testUser = userDao.getUserByUserId(1);
		
		assertEquals(temp, testUser);
	}
	
	@Ignore
	public void testAddUser()
	{
		boolean testAdd = false;
		User tester = new User("Lisa", "1567", "Tina", "Morin");
		
		tester = userDao.addUser(tester);
		
		if (tester == userDao.getUserByUserId(tester.getUserId()))
		{
			testAdd = true;
		}
		
		assertTrue(testAdd);
	}

}
