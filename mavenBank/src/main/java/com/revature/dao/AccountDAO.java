package com.revature.dao;

import java.util.List;

import com.revature.pojo.Account;

public interface AccountDAO {
	
	public List<Account> getAllAccounts();
	public List<Account> getUserAccounts(int userId);
	public Account getAccountById(int accountId);
	public Account addAccount(Account newAccount);
	public int updateAccount(Account updatedAccount);
	public int deleteAccount(int accountId);

}
