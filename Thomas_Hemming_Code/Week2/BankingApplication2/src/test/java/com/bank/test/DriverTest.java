package com.bank.test;

import java.io.IOException;

import com.bank.objects.Bank;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DriverTest extends TestCase {
	Bank bank;
	
Bank testBank;
	
	@Before
	public void setUp() {
		bank = new Bank();
	}
	
	@After
	public void tearDown() {
		bank = null;
	}
	
	//USER test.user@gmail.com : password IS A TEST USER
	//DO NOT ALTER test.user@gmail.com : password OR 
	//TESTS WILL BREAK
	
	//ACCOUNT IDs:
	//6 - Balance: $25
	//7 - Balance: $30
	
	@Test
	public void testCreateExistingUser() {
		String email = "hemming.thomas@gmail.com";
		String password = "password";
		String firstName = "Please Don't Work";
		String lastName = "Please Don't Work";
		
		assertFalse(bank.register(firstName, lastName, email, password));
	}
	
	@Test
	public void testWrongEmail() {
		String email = "h.t@gmail.com";
		String password = "password";
		assertFalse(bank.login(email, password));
	}
	
	@Test
	public void testWrongPassword() {
		String email = "hemming.thomas@gmail.com";
		String password = "this password should not work";
		assertFalse(bank.login(email, password));
	}
	
	@Test
	public void testWrongEmailWrongPassword() {
		String email = "h.t@gmail.com";
		String password = "this password should not work";
		assertFalse(bank.login(email, password));
	}
	
	@Test
	public void testLogin() {
		String email = "hemming.thomas@gmail.com";
		String password = "password";
		assertTrue(bank.login(email, password));
	}
	
	@Test
	public void testNotLoggedin() {
		int expected = -1;
		assertEquals(expected, bank.withdraw(4, 25));
	}
	
	@Test
	public void testInsufficientFunds() {
		bank.login("test.user@gmail.com", "password");
		int expected = 2;
		assertEquals(expected, bank.withdraw(7, 100));
	}

	@Test
	public void testWrongAccount() {
		bank.login("test.user@gmail.com", "password");
		int expected = 1;
		assertEquals(expected, bank.withdraw(1, 200));
	}
	
	@Test
	public void testSuccessfulWithdraw() {
		bank.login("test.user@gmail.com", "password");
		int expected = 3;
		assertEquals(expected, bank.withdraw(6, 25));
	}

	@Test
	public void testNotLoggedIn2() {
		assertFalse(bank.deposit(2, 100));
	}
	
	@Test
	public void testDepositWrongAcount() {
		bank.login("test.user@gmail.com", "password");
		assertFalse(bank.deposit(3, 100));
	}
	
	@Test
	public void testDeposit() {
		bank.login("test.user@gmail.com", "password");
		assertTrue(bank.deposit(6, 25));
	}
	
	@Test
	public void testTransferInsufficiantFunds() {
		bank.login("test.user@gmail.com", "password");
		int expected = 2;
		assertEquals(expected, bank.transfer(6, 7, 1000));
	}
	
	@Test
	public void testTransferUnauthorizedAccount() {
		
		bank.login("test.user@gmail.com", "password");
		int expected = 1;
		
		assertEquals(expected, bank.transfer(3, 4, 100));
	}
	
	@Test
	public void testTransferSuccessful() {
		
		bank.login("test.user@gmail.com", "password");
		int expected = 3;
		
		assertEquals(expected, bank.transfer(6, 7, 0));
	}

	@Test
	public void testTotalBalanceLoggedOut() {
		assertNull(bank.getTotalBalance());
	}
	
	@Test
	public void testTotalBalance() {
		bank.login("test.user@gmail.com", "password");
		String expected = "$55.00";
		assertEquals(expected, bank.getTotalBalance());
	}
}
