package com.revature.bank.service;

import com.revature.bank.BankAccount;

public class ValidationService {
        /**
         * will return true if all the fields of the incoming bank account 
         * are valid. Otherwise it will return false.
         * @param
         * @return true if all fields valid. false otherwise
         */
        public static boolean validFields(BankAccount ba) {
                if(!validEmailField(ba)) {
                        return false;
                } else if(ba.getCurrentBalance() < 0) {
                        return false;
                } else if(!validNameField(ba.getFirstName()) ) {
                        return false;
                } else if(!validNameField(ba.getLastName())) {
                        return false;
                } else if(!validPasswordField(ba)) {
                        return false;
                }
                return true;
        }
        
        public static boolean validEmailPassword(BankAccount ba) {
                if(!validEmailField(ba)) {
                        return false;
                } else if(!validPasswordField(ba)) {
                        return false;
                }
                return true;
        }

        public static boolean validEmailField(BankAccount ba) {
                if(ba.getEmailID() == null)
                        return false;
                String email = ba.getEmailID().trim();
                String [] testStr = email.replaceAll("[`~!#$%^&*(+)|}{/?><,:;]", " ").split(" ");
                
                if(email.equals(""))
                        return false;
                
                if(testStr.length > 1)
                        return false;
                
                email = testStr[0];
                testStr = email.split("@");
                
                if(testStr.length > 2) {
                        return false;
                }
                return true;
        }
        
        public static boolean validNameField(String name) {
                if(name == null)
                        return false;
                String [] testStr = name.replaceAll("[!@#$%^&*)({}<>:;'`~|/0123456789-]", " ").split(" ");
                if(testStr.length > 1) {
                        return false;
                }
                return true;
        }
        
        
        public static boolean validPasswordField(BankAccount ba) {
                if(ba.getPassword() == null)
                        return false;
                String pw = ba.getPassword();
                if(pw.contains(" "))
                        return false;
                else if(pw.length() < 4)
                        return false;
                return true;
        }
        
        public static boolean validNumber(String str) {
                if(str == null)
                        return false;
                if(str.matches("[^0-9]"))
                        return false;
                else
                        return true;
                
        }

}
