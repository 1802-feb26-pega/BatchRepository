package com.rv.bankasgn.service;

import com.rv.bankasgn.access.AccountAccess;
import com.rv.bankasgn.access.AccountAccessImplRDB;
import com.rv.bankasgn.pojos.Account;

import java.util.List;

public class AccountService {
    
    private AccountAccess aa;

    public AccountService() {
        this.aa = new AccountAccessImplRDB();
    }

    public AccountService(AccountAccess u) {
        this.aa = u;
    }

    public List<Account> getAccountsByUser(int id) {
        return aa.getAccountsByUserId(id);
    }

    public void updateAccount(Account a) {
        aa.updateAccount(a);
    }

    public Account saveAccount(Account u){
        aa.saveAccount(u);
        return u;
    }

    public Account registerAccount(Account u){
        return null;
    }

}
