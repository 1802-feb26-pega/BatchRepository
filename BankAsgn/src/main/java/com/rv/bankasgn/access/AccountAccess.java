package com.rv.bankasgn.access;

import com.rv.bankasgn.pojos.Account;

import java.util.List;

public interface AccountAccess {
    public List<Account> getAllAccounts();
    //public List<Account> getAccountsByUserEmail(String email);
    //public List<Account> getAccountsById(int accountId);
    public List<Account> getAccountsByUserId(int id);
    public Account getAccountById(int id);
    public void saveAccount(Account a);
    public void updateAccount(Account a);
    public void writeAll();
}
