package com.revature.bank.service;

import com.revature.bank.BankAccount;
import com.revature.bank.dao.PersistToService;
import com.revature.bank.dao.ServiceToUI;
import com.revature.bank.dao.UserType;

public class ServiceLayer implements Runnable{


        @Override
        public void run() {
                ServiceToUI serv = new ServiceToUI();
                PersistToService pers = new PersistToService();
                while(true) {
                        UserType rdy = serv.whichService();
                        switch(rdy) {
                        case newEntry :
                                handleNewUser(serv,pers);
                                break;
                        case existingEntry :
                                handleExistingUser(serv,pers);
                                break;
                        case updateExisting :
                                handleUpdateExistingUser(serv,pers);
                                break;
                        case shutdown :
                                try {
                                        while(pers.cleanAndFinish() == false)
                                                Thread.sleep(25);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                                
                                return;
                        default : 
                                try {
                                        Thread.sleep(25);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                                break;
                        }
                }
        }

        private void handleExistingUser(ServiceToUI serv,
                        PersistToService pers) {
                BankAccount incReq = serv.processExistingUser();
                if(ValidationService.validEmailPassword(incReq)) {
                        BankAccount temp = pers.requestExistingUser(incReq);
                        serv.finalizeNewUser(temp);
                } else {
                        serv.finalizeNewUser(incReq.getInvalidInfo());
                }
                return;
        }

        private void handleNewUser(ServiceToUI serv,
                        PersistToService pers) {
                BankAccount incReq = serv.processNewUser();
                if(ValidationService.validFields(incReq)) {
                        BankAccount temp = pers.requestNewUser(incReq);
                        serv.finalizeNewUser(temp);
                } else {
                        serv.finalizeNewUser(incReq.getInvalidInfo());
                }
                return;
        }
        
        private void handleUpdateExistingUser(ServiceToUI serv,
                        PersistToService pers) {
                
                BankAccount curr = serv.processEntryCurrentBalance();
                BankAccount proposed = serv.processEntryProposedBalance();
                
                if(ValidationService.validFields(curr) && ValidationService.validFields(proposed)) {
                        int currBal, futureBal;
                        currBal = curr.getCurrentBalance();
                        futureBal = proposed.getCurrentBalance();
                        //no change in balance or balance would go negative bypass changes in persistence layer
                        if( (currBal == futureBal) || (futureBal < 0)){
                                serv.finalizeEntryUpdate(curr); 
                        } else {
                                BankAccount ret = pers.requestEntryUpdateBalance(proposed);
                                serv.finalizeEntryUpdate(ret);
                        }
                } else {
                        serv.finalizeEntryUpdate(curr.getInvalidInfo());
                }
                return;
                
        }
        

}
