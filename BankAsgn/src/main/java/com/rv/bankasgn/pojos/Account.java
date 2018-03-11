package com.rv.bankasgn.pojos;

public class Account {

    private double balance;
    private int accountId,
                userId;

    public Account() {
        this.balance = 0;
    }

    public Account(double balance, int accountId, int userId) {

        this.balance = balance;
        this.accountId = accountId;
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
