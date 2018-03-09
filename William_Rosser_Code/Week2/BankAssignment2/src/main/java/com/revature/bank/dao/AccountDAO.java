package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;

public interface AccountDAO {
	/**
	 * Returns a list of all accounts. List will be empty if information cannot be loaded.
	 * 
	 * @return A list of accounts.
	 */
	public List<Account> getAllAccounts();
	public Account getAccountByAccountID(int accountID);
	public List<Account> getAllAccountsForUsers(int userID);
	public int addAccount(int userID, Account account);
	public int updateAccount(int accountID, Account updated);
	public int removeAccount(int accountID);
}
