package com.revature.bank.driver;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojos.Account;

public class BankDriver {

	public static void main(String[] args) {
		UserDAO uDao = new UserDAOImpl();
		AccountDAO aDao = new AccountDAOImpl();
		
//		Account temp = new Account(1);
//		temp.setAccountName("Will's Account 2");
//		temp.setBalance(777.0);
//		aDao.addAccount(temp);
		
//		for (Account acc : aDao.getAllAccounts()) {
//			System.out.println(acc);
//		}
		System.out.println(aDao.getAccountByAccountID(2));
		
	}

}
