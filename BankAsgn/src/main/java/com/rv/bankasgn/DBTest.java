package com.rv.bankasgn;

import com.rv.bankasgn.access.AccountAccess;
import com.rv.bankasgn.access.AccountAccessImplRDB;
import com.rv.bankasgn.access.UserAccess;
import com.rv.bankasgn.access.UserAccessImplRDB;
import com.rv.bankasgn.pojos.Account;
import com.rv.bankasgn.pojos.User;

public class DBTest {
    public static void main(String... args) {
        UserAccess ua = new UserAccessImplRDB();
        AccountAccess aa = new AccountAccessImplRDB();

        Account test = aa.getAccountById(1);

        System.out.println(test);
    }
}
