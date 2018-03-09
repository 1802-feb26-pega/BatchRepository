package com.revature.bank.driver;

import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.User;

public class BankDriver {

	public static void main(String[] args) {
		UserDAO uDao = new UserDAOImpl();
		for (User u : uDao.getAllUsers()) {
			System.out.println(u);
		}
		System.out.println(uDao.getUserByEmail("will.e179@gmail.com"));
		System.out.println(uDao.getUserByEmail("egr@gmail.com"));
		System.out.println(uDao.getUserByUsername("bookzzz"));
		
		
		User bob = uDao.getUserByUsername("bob765");
		System.out.println(bob);
		bob.setFirstName("John");
		bob.setMiddleInitial("J");
		bob.setLastName("Jamerson");
		bob.setPassword("P@$$w0rd");
		uDao.updateUser(bob);
		System.out.println(uDao.getUserByUsername("bob765"));
		
	}

}
