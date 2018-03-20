package com.tailock.test;

import com.tailock.dao.EmployeeDAO;
import com.tailock.dao.EmployeeDAOImpl;

public class MainTest {

	public static void main(String[] args) {
		EmployeeDAO empdao = new EmployeeDAOImpl();
//		User u = dao.getUserById(21);
//		//Account a = dao.createAccount(u, 1985.2);
//		ArrayList<Account> accounts = dao.getAccountsByUser(u);
//		for(Account a : accounts) {
//		System.out.println(a.toString());
//		}
//		User user = dao.getUser("test");
//		System.out.println(user.toString());
		
		empdao.updateBalance(2, 5);
	}

}
