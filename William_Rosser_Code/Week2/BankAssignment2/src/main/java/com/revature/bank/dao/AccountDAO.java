package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public Account getAccountByAccountID(int accountID);
	public List<Account> getAllAccountsForUsers(int userID);
	public int addAccount(int userID);
	public int updateAccount(int accountID, Account updated);
}
