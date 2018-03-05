package com.revature.bank.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.revature.bank.BankAccount;

class ServiceToUITest {

        @Test
        void testRequestNewUser() {
                ServiceToUI sui = new ServiceToUI();
                BankAccount person = new BankAccount("unique", 250, "Person", "Lastname","password");
                sui.finalizeNewUser(person);
                assertEquals(person, sui.requestNewUser(new BankAccount("Blob","Lastblob","password123")), "Not Same");
        }

        @Test
        void testOutOfOrderRequestNewUser() {
                ServiceToUI sui = new ServiceToUI();
                BankAccount person = new BankAccount("unique", 250, "Person", "Lastname","password");
                
                Runnable sampleServiceThread = () -> {
                        int i = 0;
                        while(i < 30) {
                                System.out.println("Service: Getting Item from queue");
                                BankAccount ret = sui.processNewUser();
                                System.out.println("Service: Sending Item to queue ");
                                sui.finalizeNewUser(ret);
                                i++;
                        }
                };
                Runnable sampleClientThread = () -> {
                        int i = 0;
                        while(i < 30) {
                                BankAccount resp;
                                person.setCurrentBalance(i);
                                resp = sui.requestNewUser(person);
                                System.out.println("Client: requestNewUser " + 
                                resp.getFirstName() + resp.getCurrentBalance());
                                i++;
                        }
                };
                Thread t1 = new Thread(sampleServiceThread);
                Thread t2 = new Thread(sampleClientThread);
                t1.start();
                t2.start();
                
                try {
                        t1.join();
                        t2.join();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                        fail("Failed to execute threads");
                }
        }

}
