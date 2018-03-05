package com.revature.bank.run;

import com.revature.bank.persistance.PersistenceLayer;
import com.revature.bank.service.ServiceLayer;
import com.revature.bank.ui.ClientUI;

public class MainRun {
        public static void main(String[] args) {
                ClientUI clUI = new ClientUI();
                ServiceLayer sl = new ServiceLayer();
                PersistenceLayer pl = new PersistenceLayer();
                Thread threadClient, threadService, threadPersist;
                threadClient = new Thread(clUI);
                threadService = new Thread(sl);
                threadPersist = new Thread(pl);
                threadClient.start();
                threadService.start();
                threadPersist.start();
                try {
                        threadClient.join();
                        threadService.join();
                        threadPersist.join();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                
        }

}
