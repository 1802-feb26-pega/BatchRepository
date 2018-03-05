package com.revature.bank;

import java.io.Serializable;

public class BankAccount implements Serializable{
        
        /**
         * 
         */
        private static final long serialVersionUID = 8374424155141654782L;
        private String emailID;
        private int currentBalance;
        private String firstName;
        private String lastName;
        
        private String password;
        
        public BankAccount() {
                super();
                this.emailID = "";
                this.currentBalance = 0;
                this.firstName = "";
                this.lastName = "";
                this.password = "";
        }
        
        public BankAccount(String emailID, String firstName, String lastName,
                        String password) {
                super();
                this.emailID = emailID;
                this.currentBalance = 0;
                this.firstName = firstName;
                this.lastName = lastName;
                this.password = password;
        }

        public BankAccount(String emailID, String password) {
                super();
                this.emailID = emailID;
                this.password = password;
        }

        public BankAccount(String firstName, String lastName, String password) {
                super();
                this.firstName = firstName;
                this.lastName = lastName;
                this.password = password;
        }

        public BankAccount(String emailID, int currentBalance,
                        String firstName, String lastName, String password) {
                super();
                this.emailID = emailID;
                this.currentBalance = currentBalance;
                this.firstName = firstName;
                this.lastName = lastName;
                this.password = password;
        }
        


        public String getEmailID() {
                return emailID;
        }

        public void setEmailID(String emailID) {
                this.emailID = emailID;
        }

        public int getCurrentBalance() {
                return currentBalance;
        }

        public void setCurrentBalance(int currentBalance) {
                this.currentBalance = currentBalance;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }
        
       /**
         * Will return a BankAccount object with an empty string for the 
         * emailID, firstName, lastName, and password.
         * currentBalance is 0 for the returned object
         * @param 
         * @return invalid BankAccount object
         */
        public BankAccount getInvalidInfo() {
                return new BankAccount();
        }
        
        /**
         * Checks this BankAccount against an invalid one. 
         * @return true if email or password are an empty string;
         * false otherwise
         */
        public boolean CheckInvalidState() {
                if(this.emailID.equals(""))
                        return true;
                else if(this.password.equals(""))
                        return true;
                return false;
        }
        
        public BankAccount copy() {
                BankAccount cp = new BankAccount(this.emailID,this.currentBalance,
                                this.firstName,this.lastName,this.password);
                return cp;
                
        }
        
}
