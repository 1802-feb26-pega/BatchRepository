package com.revature.bank.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bank.pojos.User;
import com.revature.bank.util.ConnectionFactory;

public class UserDAOImplTest {
	
	private static UserDAOImpl uDao;
	
	@BeforeClass
	public static void getUDAO() {
		uDao = UserDAOImpl.getInstance();
	}
	
	@Before
	public void addTestUser() {
		uDao.addUser(getTestUser());
	}
	
	@After 
	public void cleanUp() {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM users WHERE username = 'junitUSER'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			sql = "DELETE FROM users WHERE username = 'junitUSER2'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllUsers() {
		List<User> all = uDao.getAllUsers();
		assertTrue(all.size() > 0);
		User test = null;
		for (User u:all) {
			if (u.getUsername().equals("junitUSER")) {
				test = u;
			}
		}
		assertTrue(test != null);	
	}
	
	@Test
	public void testGetUserByID() {
		List<User> all = uDao.getAllUsers();
		User first = all.get(0);
		User second = uDao.getUserByID(first.getUserID());
		assertEquals(first, second);
	}
	
	@Test
	public void testGetUserByUsername() {
		User test1 = uDao.getUserByUsername("junitUSER");
		assertTrue(test1 != null && test1.getFirstName().equals("JUNIT"));
		User test2 = uDao.getUserByUsername("A NONEXISTENT USERNAME");
		assertTrue(test2 == null);
	}
	
	@Test
	public void testGetUserByEmail() {
		User test1 = uDao.getUserByEmail("J@JUNIT");
		assertTrue(test1 != null && test1.getFirstName().equals("JUNIT"));
		User test2 = uDao.getUserByEmail("XXX@X.COM");
		assertTrue(test2 == null);
	}
	
	@Test
	public void testAddUser() {
		User secondTest = getTestUser();
		assertEquals(0, uDao.addUser(secondTest));
		secondTest.setEmail("J2@JUNIT");
		assertEquals(0, uDao.addUser(secondTest));
		secondTest.setUsername("junitUSER2");
		assertEquals(1, uDao.addUser(secondTest));
		
	}
	
	@Test
	public void testUpdateUser() {
		User test = uDao.getUserByUsername("junitUSER");
		User badTest = new User();
		User oddTest = new User(77777);
		test.setFirstName("TEST");
		assertEquals(-1, uDao.updateUser(badTest));
		assertEquals(0, uDao.updateUser(oddTest));
		assertEquals(1, uDao.updateUser(test));
		User result = uDao.getUserByUsername("junitUSER");
		assertEquals(test, result);
	}

	private User getTestUser() {
		return new User("JUNIT", "J", "JUNIT", "J@JUNIT","junitUSER","junit");
	}
}
