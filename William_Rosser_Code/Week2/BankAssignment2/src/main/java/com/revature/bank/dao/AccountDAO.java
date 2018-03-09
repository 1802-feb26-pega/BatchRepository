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
	
	/**
	 * Gets a single account by its ID number.
	 * @param accountID the ID number for the account.
	 * @return the account, if it exists; otherwise, null.
	 */
	public Account getAccountByAccountID(int accountID);
	
	/**
	 * Returns a list of all the accounts that belong to a user. Returns an empty list if accounts
	 * cannot be loaded.
	 * @param userID	the user's account id.
	 * @return	A list of accounts.
	 */
	public List<Account> getAllAccountsForUser(int userID);
	
	/**
	 * Adds a new account to the database. Returns 0 if it fails.
	 * @param account The account to be added.
	 * @return -1 if invalid account, 0 if database failure, or >0 if success.
	 */
	public int addAccount(Account account);
	
	/**
	 * Updates an account, changing the name and the balance. 
	 * Does not change account number or owner.
	 * @param updated the updated account. Must have a valid account_id number.
	 * @return >=1 if success, 0 if database failure, or -1 if invalid account.
	 */
	public int updateAccount(Account updated);
	
	/**
	 * Not yet implemented.
	 * @param accountID
	 * @return
	 */
	public int removeAccount(int accountID);
}
