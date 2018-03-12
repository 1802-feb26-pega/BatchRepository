package com.revature.bank.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.revature.bank.pojo.Account;

public interface AccountDAO {

	public void updateAccount(Account account) throws SQLException;
	public Collection<Account> getAccountsByUserID(int userID) throws SQLException;
	public Account addAccount(Account newAccount);
}
