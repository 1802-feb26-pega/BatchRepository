package com.rv.bankasgn.service;

import com.rv.bankasgn.access.AccountAccess;
import com.rv.bankasgn.access.AccountAccessImplRDB;
import com.rv.bankasgn.pojos.Account;
import com.rv.bankasgn.pojos.User;

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

    public Account getOneAccountById(int id) {
        return aa.getAccountById(id);
    }

    public void updateAccount(Account a) {
        aa.updateAccount(a);
    }

    public Account saveAccount(Account u){
        aa.saveAccount(u);
        return u;
    }

    public Account createAccount(User u) {
        Account newAcc = new Account();
        newAcc.setUserId(u.getUserId());
        saveAccount(newAcc);
        return newAcc;
    }

    public Account depositWithdraw(Account a, double amount, boolean isDeposit) {

        if(isDeposit) {
            a.setBalance(a.getBalance() + amount);
            updateAccount(a);
            return a;
        } else {
            if(amount <= a.getBalance()) {
                a.setBalance(a.getBalance() - amount);
                updateAccount(a);
            } else {
                return null;
            }
            return a;
        }
    }

    public boolean transfer(Account to, Account from, double amount) {
        if(amount <= from.getBalance()) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
        } else {
            return false;
        }

        updateAccount(to);
        updateAccount(from);
        return true;

    }
}
