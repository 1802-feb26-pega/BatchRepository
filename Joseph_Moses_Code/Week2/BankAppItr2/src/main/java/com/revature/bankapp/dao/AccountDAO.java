package com.revature.bankapp.dao;

import java.util.List;

import com.revature.bankapp.pojos.Account;

public interface AccountDAO {
	
	public List<Account> getAllAccounts(int userId);
	public Account getAccountByIds(int accountId, int userId);
	public Account addAccount(Account account);
	public int updateAccount(Account account);
	public double getAccountsTotal(int userId);

}
