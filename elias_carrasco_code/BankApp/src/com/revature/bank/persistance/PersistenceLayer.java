package com.revature.bank.persistance;

import java.io.*;
import java.util.*;

import com.revature.bank.BankAccount;
import com.revature.bank.dao.PersistToService;
import com.revature.bank.dao.UserType;

public class PersistenceLayer implements Runnable {
        
        static final String filePath = "./userData.dat";
        
        private static Map<String,String> UserCredentials = new HashMap<String,String>();
        private static Map<String,BankAccount> LiveUserData = new HashMap<String,BankAccount>();
        
        public PersistenceLayer() {
                super();
        }
        
        public void init() {
                fillLiveUserData();
                fillMap();
        }
        
        private void fillLiveUserData() {
                LiveUserData = deserialize();
                
        }
        /*
        public boolean sandboxMap(List<BankAccount> baList) {
                for(BankAccount item : baList) {
                        BankAccount temp;
                        if( LiveUserData.containsKey(item.getEmailID()) )
                                temp = LiveUserData.get(item.getEmailID());
                        LiveUserData.put(item.getEmailID(), item);
                }
                serialize(LiveUserData);
                
                Map<String,BankAccount> test = deserialize();
                LiveUserData.equals(test);
                return true;
        }
        */

        private void fillMap() {
                if(LiveUserData.isEmpty())
                        return;
                for(BankAccount ba : LiveUserData.values()) {
                        UserCredentials.put(ba.getEmailID(), ba.getPassword());
                }
        }
        
        @Override
        public void run() {
                init();
                PersistToService pers = new PersistToService();
                while(true) {
                UserType rdy = pers.whichService();
                switch(rdy) {
                case newEntry:
                        handleNewUser(pers);
                        break;
                case existingEntry :
                        handleExistingUser(pers);
                        break;
                case updateExisting :
                        handleUpdateUserEntry(pers);
                        break;
                case shutdown : 
                        serialize(LiveUserData);
                        return;
                default :
                        try {
                                Thread.sleep(25);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        break;
                }
                }//end of while loop
                
        }

        private void handleExistingUser(PersistToService pers) {
                
                BankAccount ba = pers.processExistingUser();
                String pwValue = UserCredentials.get(ba.getEmailID());
                if(pwValue == null){
                        pers.finalizeExistingUser(ba.getInvalidInfo());
                } else if(pwValue.equals(ba.getPassword())) {
                        BankAccount output = LiveUserData.get(ba.getEmailID());
                        pers.finalizeExistingUser(output);
                } else {
                        pers.finalizeExistingUser(ba.getInvalidInfo());
                }
                
        }

        private void handleNewUser(PersistToService pers) {
                
                BankAccount ba = pers.processNewUser();
                
                if( !LiveUserData.containsKey(ba.getEmailID() ) )  {
                        UserCredentials.put(ba.getEmailID(), ba.getPassword());
                        LiveUserData.put(ba.getEmailID(), ba);
                        pers.finalizeNewUser(ba);
                } else {
                        pers.finalizeNewUser(new BankAccount());
                }
                
        }
        
        private void handleUpdateUserEntry(PersistToService pers) {
                BankAccount ba = pers.processEntryUpdateBalance();
                String pwValue = UserCredentials.get(ba.getEmailID());
                if(pwValue == null){
                        pers.finalizeEntryUpdateBalance(ba.getInvalidInfo());
                } else if(pwValue.equals(ba.getPassword())) {
                        LiveUserData.put(ba.getEmailID(), ba);
                        pers.finalizeEntryUpdateBalance(ba);
                } else {
                        pers.finalizeEntryUpdateBalance(ba.getInvalidInfo());
                }
                
        }

        static void serialize(Map<String,BankAccount> ba) {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(filePath) ) ) {
                        oos.writeObject(ba);
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        static Map<String,BankAccount> deserialize() {
                Map<String,BankAccount> ba = new HashMap<String,BankAccount>();
                try (ObjectInputStream ois = new ObjectInputStream(
                                new FileInputStream(filePath) ) ){
                        Object safe = null;
                        safe = ois.readObject();
                        if(safe instanceof Map<?,?>)
                                ba = (Map<String,BankAccount>) safe;
                } catch (FileNotFoundException e) {
                        System.out.println("File doesn't Exist");
                        //e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        System.out.println("Reached End of file?");
                        //e.printStackTrace();
                }
                return ba;
        }
        
        
        
}
