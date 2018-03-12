package com.revature.bank.dao;

import java.util.List;
import java.util.Map;

import com.revature.bank.pojos.Account;

public interface AccountDAO {
	public List<Account> getAllAccounts();
	public Account getAccountById(int accountId);
	public Account addAccount(Account newAccount);
	public int updateAccount(int accountId, Account updatedAccount);
	public int removeAccount(int accountId);
	public boolean hasUser(int accountId);
	public int getAccountTypeNum(String type);
	public String getAccountTypeName(int type);
	public Map<Integer, String> getAccountTypes();
}
