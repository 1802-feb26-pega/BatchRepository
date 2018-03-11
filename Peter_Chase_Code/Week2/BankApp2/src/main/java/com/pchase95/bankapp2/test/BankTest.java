package com.pchase95.bankapp2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pchase95.bankapp2.dao.AccountDAO;
import com.pchase95.bankapp2.dao.AccountDAOImpl;
import com.pchase95.bankapp2.pojos.Account;

public class BankTest {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private static final double DELTA = 1e-15;
	private static final AccountDAO actDAO = new AccountDAOImpl();
	
	private static final String NAME = "testaccount";
	private static final String EMAIL = "testaccount@testmail.com";
	private static final String PASSWORD = "password123";
	private static final double BALANCE = 0.0;
	
	private static final String UPDATED_NAME = "updatedaccount";
	private static final String UPDATED_EMAIL = "updatedaccount@update.com";
	private static final String UPDATED_PASSWORD = "upd4t3";
	private static final double UPDATED_BALANCE = 12345.67;
	
	
	@Test
	public void testAddAndGetAllAndGetById() {
		List<Account> accounts = actDAO.getAllAccounts();
		int originalSize = accounts.size();
		Account act = new Account(
			NAME, EMAIL, PASSWORD, BALANCE);
		
		assertTrue(actDAO.addAccount(act));
		
		accounts = actDAO.getAllAccounts();
		
		assertEquals(accounts.size(), originalSize + 1);
		
		Account act2 = actDAO.getAccountById(act.getId());
		
		assertEquals(act, act2);
		
		actDAO.removeAccount(act.getId());
	}
	
	@Test
	public void testGetByName() {
		Account act = new Account(
				NAME, EMAIL, PASSWORD, BALANCE);
		act = actDAO.getAccountByName(NAME);
		assertNotNull(act);
		actDAO.removeAccount(act.getId());
	}
	
	@Test
	public void testGetEmail() {
		Account act = new Account(
				NAME, EMAIL, PASSWORD, BALANCE);
		actDAO.addAccount(act);
		act = actDAO.getAccountByEmail(EMAIL);
		assertNotNull(act);
		actDAO.removeAccount(act.getId());
	}
	
	@Test
	public void testUpdate() {
		Account act = new Account(
				NAME, EMAIL, PASSWORD, BALANCE);
		act.setName(UPDATED_NAME);
		act.setEmail(UPDATED_EMAIL);
		act.setPassword(UPDATED_PASSWORD);
		act.setBalance(UPDATED_BALANCE);
		actDAO.addAccount(act);
		
		actDAO.updateAccount(act.getId(), act);
		act = actDAO.getAccountById(act.getId());
		
		assertEquals(act.getName(), UPDATED_NAME);
		assertEquals(act.getEmail(), UPDATED_EMAIL);
		assertEquals(act.getPassword(), UPDATED_PASSWORD);
		
		assertEquals(act.getBalance(), UPDATED_BALANCE, DELTA);
		actDAO.removeAccount(act.getId());
	}
	
	@Test
	public void testDelete() {
		Account act = new Account(
				NAME, EMAIL, PASSWORD, BALANCE);
		actDAO.addAccount(act);
		assertTrue(actDAO.removeAccount(act.getId()));
		
		act = actDAO.getAccountById(act.getId());
		assertNull(act);
	}
	
}
