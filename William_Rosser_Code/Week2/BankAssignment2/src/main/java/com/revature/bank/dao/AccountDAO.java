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
	
	/**
	 * Adds a new account to the database. Returns 0 if it fails.
	 * @param account The account to be added.
	 * @return -1 if invalid account, 0 if database failure, or >0 if success.
	 */
	public int addAccount(Account account);
	
	public int updateAccount(Account updated);
	public int removeAccount(int accountID);
}
