package com.revature.banking.dao;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public interface Account_Interface {
	public void readAccount(Client c, Account a);
	public boolean updateBalance(Account a);
	public boolean createAcc(Account a);
}
