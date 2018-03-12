package com.bank.eval.java.core;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.bank.obj.Teller;

public class BankDriverTest {
	
	private static final Teller teller = new Teller();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test() {
		//assertEquals("", teller.createUser(newUser, newPass));
	}

}
