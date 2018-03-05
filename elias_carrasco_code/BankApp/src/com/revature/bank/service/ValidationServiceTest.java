package com.revature.bank.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import com.revature.bank.BankAccount;

public class ValidationServiceTest {

        @Test
        public void testValidFieldsEmptyString() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                assertFalse(ValidationService.validFields(timmyGS));
        }
        
        @Test
        public void testValidFieldsValidBankAccount() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                assertFalse(ValidationService.validFields(timmyGS));
        }
        
        /************************
         * E-Mail Tests
         ************************/

        @Test
        public void testValidEmailFieldEmptyString() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                assertFalse(ValidationService.validEmailField(timmyGS));
        }
        
        @Test
        public void testValidEmailFieldSpecialCharacters() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("timmyI$C**L@somewhere.org");
                assertFalse(ValidationService.validEmailField(timmyGS));
        }
        
        @Test
        public void testValidEmailFieldExtraSpaces() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("timmy Is Cool @s o mew here.org");
                assertFalse(ValidationService.validEmailField(timmyGS));
        }
        
        @Test
        public void testValidEmailFieldTooManyATSymbols() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("c@lling@llTimmys@somewhere.org");
                assertFalse(ValidationService.validEmailField(timmyGS));
        }
        
        @Test
        public void testValidEmailField() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                assertTrue(ValidationService.validEmailField(timmyGS), "Failed with a valid e-mail");
        }
        
        /************************
         * Name Field Tests
         ************************/
        
        @Test
        public void testValidNameFieldLotsOfSymbols() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                String name = "Timmy*&^126  n78y*@*&(";
                assertFalse(ValidationService.validNameField(name), "Failed garbage exists in string");                
        }
        
        @Test
        public void testValidNameFieldWhiteSpace() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                String name = "   Timmy    ";
                assertFalse(ValidationService.validNameField(name), "Failed garbage exists in string");                
        }
        
        
        @Test
        public void testValidNameFieldValid() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                String name = "Timmy";
                assertTrue(ValidationService.validNameField(name), "Failed, we passed in a valid string but got false");                
        }
        
        /************************
         * Password Tests
         ************************/
        
        @Test
        public void testValidPasswordField() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                timmyGS.setPassword("REALLYBAD123");
                assertTrue(ValidationService.validPasswordField(timmyGS), "Failed, we passed in a valid string but got false");                
        }
        
        @Test
        public void testValidPasswordFieldWhiteSpace() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                timmyGS.setPassword(" oh noes this shouldn't work");
                assertFalse(ValidationService.validPasswordField(timmyGS), "Failed, we passed in a valid string but got false");                
        }
        
        @Test
        public void testValidPasswordFieldTooShort() {
                new ServiceLayer();
                BankAccount timmyGS = new BankAccount();
                timmyGS.setEmailID("TimmyGS27347@somewherelocal.net");
                timmyGS.setPassword("123");
                assertFalse(ValidationService.validPasswordField(timmyGS), "Failed, we passed in a valid string but got false");                
        }

}
