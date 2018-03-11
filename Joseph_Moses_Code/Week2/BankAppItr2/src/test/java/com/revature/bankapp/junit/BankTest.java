package com.revature.bankapp.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.bankapp.pojos.Bank;

public class BankTest {

	Bank testBank;
	
	@Before
	public void setUp() throws Exception {
		testBank = new Bank();
	}
	
	@After
	public void tearDown() throws Exception{
		testBank = null;
	}
	
	@Test
	public void testLoginCorrectEmailPassword() {
		String email = "joseph.k.moses6@gmail.com";
		String password = "password";
		
		assertTrue(testBank.login(email, password));
	}
	
	@Test
	public void testLoginincorrectEmail() {
		String email = "joseph.k.moses6@incorrect.com";
		String password = "password";
		
		assertFalse(testBank.login(email, password));
	}
	
	@Test
	public void testLoginincorrectPassword() {
		String email = "joseph.k.moses6@gmail.com";
		String password = "wrong";
		
		assertFalse(testBank.login(email, password));
	}
	
	@Test
	public void testLoginincorrectEmailPassword() {
		String email = "joseph.k.moses6@l.com";
		String password = "wrong";
		
		assertFalse(testBank.login(email, password));
	}

	@Test
	public void testRegisterExistingUser() {
		String email = "joseph.k.moses6@gmail.com";
		String password = "wrong";
		String firstName = "Shouldn't be in DB";
		String lastName = "Shouldn't be in DB";
		
		assertFalse(testBank.register(firstName, lastName, email, password));
	}
	
//	@Test
//	public void testRegisterNewUser() {
		//Uncomment this code to run the test
//		//This will fail after being ran once
//		//To run again change the email to one that is not in the DB
//		String email = "joseph.k.moses6@test1.com";
//		String password = "password";
//		String firstName = "JUnit";
//		String lastName = "Test";
//		
//		assertTrue(testBank.register(firstName, lastName, email, password));
//	}

//	@Test
//	public void testCreateAccount() {
		//Uncomment this code to run the test
//		//This will fail after being ran once
//		//To run again change the AccId to the next one to be assigned in DB
//		testBank.login("joseph.k.moses6@gmail.com","password");
//		Account expected = new Account();
//		expected.setAccId(13);
//		expected.setUserId(1);
//		expected.setBalance(0.0);
//		
//		assertEquals(expected.getAccId(), testBank.createAccount().getAccId());
//	}

	@Test
	public void testWithdrawNotLoggedin() {
		int expected = -1;
		
		assertEquals(expected, testBank.withdraw(1, 100));
	}
	
	@Test
	public void testWithdrawInsufficiantFunds() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 2;
		
		assertEquals(expected, testBank.withdraw(6, 100));
	}

	
	@Test
	public void testWithdrawUnauthorizedAccount() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 1;
		
		assertEquals(expected, testBank.withdraw(3, 100));
	}
	
	@Test
	public void testWithdrawSuccessful() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 3;
		
		assertEquals(expected, testBank.withdraw(2, 100));
	}

	@Test
	public void testDepositNotLoggedIn() {
		assertFalse(testBank.deposit(2, 100));
	}
	
	@Test
	public void testDepositUnauthorizedAccount() {
		testBank.login("joseph.k.moses6@gmail.com", "password");
		
		assertFalse(testBank.deposit(3, 100));
	}
	
	@Test
	public void testDepositSuccessful() {
		testBank.login("joseph.k.moses6@gmail.com", "password");
		
		assertTrue(testBank.deposit(2, 100));
	}

	@Test
	public void testTransferNotLoggedin() {
		int expected = -1;
		
		assertEquals(expected, testBank.transfer(2, 1, 100));
	}
	
	@Test
	public void testTransferInsufficiantFunds() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 2;
		
		assertEquals(expected, testBank.transfer(1, 2, 1000));
	}
	
	@Test
	public void testTransferUnauthorizedAccount() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 1;
		
		assertEquals(expected, testBank.transfer(3, 4, 100));
	}
	
	@Test
	public void testTransferSuccessful() {
		
		testBank.login("joseph.k.moses6@gmail.com", "password");
		int expected = 3;
		
		assertEquals(expected, testBank.transfer(2, 1, 100));
	}

	@Test
	public void testViewBalanceNotLoggedIn() {
		assertNull(testBank.viewBalance(1));
	}
	
	@Test
	public void testViewBalanceSuccess() {
		testBank.login("joseph.k.moses6@gmail.com", "password");
		String expected = "$25.00";
		assertEquals(expected, testBank.viewBalance(6));
	}

	@Test
	public void testViewTotalBalanceOfAllAccountsNotLoggedIn() {
		assertNull(testBank.viewTotalBalanceOfAllAccounts());
	}
	
	@Test
	public void testViewTotalBalanceOfAllAccountsSuccess() {
		testBank.login("peter.chase@gmail.com", "password");
		String expected = "$100.00";
		assertEquals(expected, testBank.viewTotalBalanceOfAllAccounts());
	}

}
