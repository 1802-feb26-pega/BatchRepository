package com.bank.eval.java.core;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bank.dao.BankDatabaseImpl;
import com.bank.obj.Teller;

public class BankDriverTest {
	
	private static Teller teller = new Teller();
	private static BankDatabaseImpl bank = new BankDatabaseImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testCreateUser() {
		teller.createUser("a", "b", "c", "d");
		assertEquals(true, bank.userValidation("a"));
	}
	
	@Test
	public void testCreateAccount() {
		teller.createUser("a", "b", "c", "d");
		teller.createAccount("a", "e", true);
		assertEquals(true, bank.accountValidation("a", "e"));
	}

}
