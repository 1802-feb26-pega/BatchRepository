package com.revature.bank.persistance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.bank.BankAccount;

public class PersistenceLayerTest {

        @Test
        public void testInit() {
                PersistenceLayer pl = new PersistenceLayer();
                pl.init();
        }
        
        /*
        @Test
        public void testSandbox() {
                PersistenceLayer pl = new PersistenceLayer();
                String emailID = "honeyoatsbar@sillyputty.net";
                int currentBalance = 0;
                String firstName = "Timmy";
                String lastName = "Oats";
                String password = "ILoveLasagna";
                List<BankAccount> baList = new ArrayList<BankAccount>();
                for(int i = 0; i < 25; i++) {
                        BankAccount one = new BankAccount(i + emailID, 
                                currentBalance, firstName, lastName, password);
                        baList.add(one);
                }
                pl.sandboxMap(baList);
        }
        */

}
