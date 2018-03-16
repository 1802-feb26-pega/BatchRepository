package com.revature.bank.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserAccountDAO;
import com.revature.bank.dao.UserAccountDAOImpl;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;

public class Login {
	public User login(String un, String pwd) {
		User user = new User();
		boolean validUN = verifyUsername(un);
		if(validUN == false) {
			return null;
		}
		boolean validPWD = verifyPassword(un, pwd);
		if(validPWD == false) {
			return null;
		} else {
			UserDAO userDao = new UserDAOImpl();
			user = userDao.getUserByUsername(un);
			return user;
		}
	}
	
	public boolean verifyUsername(String un) {	
		UserDAO userDao = new UserDAOImpl();
		User u = userDao.getUserByUsername(un);
		if(u.getUserId() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean verifyPassword(String un, String pwd) {	
		UserDAO userDao = new UserDAOImpl();
		User u = userDao.getUserByUsername(un);
		if(u.getPassword().equals(pwd)) {
			return true;
		}
		return false;
	}
	
	public User addUser(User user) {
		UserDAO userDao = new UserDAOImpl();
		userDao.addUser(user);
		return user;
	}
	
	public List<Account> getAccountsByUser(User user){
		List<Account> accounts = new ArrayList<Account>();
		UserAccountDAO uaDao = new UserAccountDAOImpl();
		AccountDAO accountDao = new AccountDAOImpl();
		List<Integer> accountIDs = uaDao.getAccountIDByUserID(user.getUserId());
		for(int accountid : accountIDs) {
			Account temp = accountDao.getAccountById(accountid);
			accounts.add(temp);
		}
		return accounts;
	}
	
}
