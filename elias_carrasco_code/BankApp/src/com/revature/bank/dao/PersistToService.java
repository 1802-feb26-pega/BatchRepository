package com.revature.bank.dao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.revature.bank.BankAccount;

public class PersistToService implements ServicePersistConnector{
        
        private static BlockingQueue<Object> msgqInp = new ArrayBlockingQueue<Object>(1);
        private static BlockingQueue<Object> msgqRet = new ArrayBlockingQueue<Object>(1);
        
        private static UserType who = UserType.otherEntry;
        
        /**********************
         *  New User Methods
         **********************/
        
        /**
         * Sends a request to enter a new BankAccount into the persistence
         * layer when available. 
         * @param newUser
         * @return newUser on success, and "invalid" BankAccount on failure
         */
        public BankAccount requestNewUser(BankAccount newUser) {
                who = UserType.newEntry;
                return requestUser(newUser);
        }
        
        public BankAccount processNewUser() {
                return processUser();
        }
        
        public void finalizeNewUser(BankAccount ba) {
                who = UserType.otherEntry;
                persistSend(ba);
        }
        
        /**********************
         *  Existing User Methods
         **********************/
        
        public BankAccount requestExistingUser(BankAccount user) {
                who = UserType.existingEntry;
                return requestUser(user);
        }
        
        public BankAccount processExistingUser() {
                return processUser();
        }
        
        public void finalizeExistingUser(BankAccount ba) {
                who = UserType.otherEntry;
                persistSend(ba);
        }
        
        /**********************
         *  Updating User Info Methods
         **********************/
        
        public BankAccount requestEntryUpdateBalance(BankAccount user) {
                who = UserType.updateExisting;
                return requestUser(user);
        }
        
        public BankAccount processEntryUpdateBalance() {
                return processUser();
        }
        
        public void finalizeEntryUpdateBalance(BankAccount ba) {
                who = UserType.otherEntry;
                persistSend(ba);
        }
        
        /**********************
         *  Helper Methods
         **********************/
        
        private BankAccount requestUser(BankAccount inp) {
                serviceSend(inp);
                Object safe = serviceGet();
                if(safe instanceof BankAccount)
                        return (BankAccount) safe;
                else
                        return new BankAccount();
        }
        
        private BankAccount processUser() {
                Object safe = persistGet();
                if(safe instanceof BankAccount)
                        return (BankAccount) safe;
                else
                        return new BankAccount();
        }
        
        public boolean cleanAndFinish() {
                if(msgqInp.isEmpty()) {
                        who = UserType.shutdown;
                        return true;
                } else {
                        return false;
                }
        }
        
        
        @Override
        public Object serviceGet() {
                Object obj = null;
                try {
                        obj = msgqRet.take();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return obj;
        }
        @Override
        public void serviceSend(Object obj) {
                
                try {
                        msgqInp.put(obj);  
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return;
        }
        
        @Override
        public Object persistGet() {
                Object obj = null;
                try {
                        obj = msgqInp.take();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                return obj;
        }
        @Override
        public void persistSend(Object obj) {
                try {
                        msgqRet.put(obj);
                        who = UserType.otherEntry;
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                
        }
        
        public UserType whichService() {
                return who;
        }

}


interface ServicePersistConnector {
        Object serviceGet();
        void serviceSend(Object obj);
        
        Object persistGet();
        void persistSend(Object obj);
}