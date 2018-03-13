package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;

/**
* <h1> AccountDAO </h1> 
* The AccountDAO Interface lists all the methods used by AccountDAOImpl.
* 
* @author Katelyn Peterson
* @version 1.0
* @since 03-09-2018 
*
*/
public interface AccountDAO
{
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByUserId(int userId);
	public Account getAccountByAccountId(int accountId);
	public Account addAccount(int userId, Account newAccount);
	//public Account getNewAccount(int userId);
	public int updateAccount(int accountId, Account updatedAccount);
	public int removeAccountByAccountId(int accountId);
	public int removeAccountsByUserId(int userId);
}
