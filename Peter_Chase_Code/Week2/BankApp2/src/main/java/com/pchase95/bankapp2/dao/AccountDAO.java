package com.pchase95.bankapp2.dao;

import java.util.List;

import com.pchase95.bankapp2.pojos.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public Account getAccountById(long accountId);
	public Account getAccountByName(String accountName);
	public Account getAccountByEmail(String accountEmail);
	public boolean addAccount(Account newAccount);
	public boolean updateAccount(long accountId, Account updatedAccount);
	public boolean removeAccount(long accountId);
}
