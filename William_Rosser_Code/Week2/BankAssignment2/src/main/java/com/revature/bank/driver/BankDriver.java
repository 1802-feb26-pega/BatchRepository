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
		
		int count = 1;
		for (Account acc : aDao.getAllAccountsForUser(1)) {
			System.out.println(acc);
			acc.setAccountName("Will's Account " + count);
			acc.setBalance(acc.getBalance() * 2);
			aDao.updateAccount(acc);
		}
		
		
		
	}

}
