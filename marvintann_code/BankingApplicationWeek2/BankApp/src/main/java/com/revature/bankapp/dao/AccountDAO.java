package com.revature.bankapp.dao;

import java.util.ArrayList;

import com.revature.bankapp.pojos.Account;

public interface AccountDAO {
	
	public void addAccount(Account newAccount);
	public Account getAccount(int account_id);
	public void updateAccount(int account_id, double newbalance);
	public ArrayList<Account> getAllAcounts(int user_id);
}