package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.User;

public interface UserAccountDAO {
	public List<Integer> getUserIDByAccountID(int accountId);
	public List<Integer> getAccountIDByUserID(int userId);
	public int addUserAccount(User user, Account account);
}
