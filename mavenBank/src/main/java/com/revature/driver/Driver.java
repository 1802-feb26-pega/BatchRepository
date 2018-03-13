package com.revature.driver;

import java.util.List;

import com.revature.UI.UserApp;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.pojo.User;

public class Driver {
	
	static List<User> users;

	public static void main(String[] args) {
		
		UserDAO userDao = new UserDAOImpl();
		
		users = userDao.getAllUsers();
		
		System.out.println("Hello! Welcome to the great bank of America!\n");
		
		UserApp.userOptions();
		
	}
	
}
