package com.revature.bank.pojos;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestAccount
{
	private static Account evaluateAccount;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testCreditWithNormalValue()
	{
		evaluateAccount = new Account();
		
		assertEquals(new Double(0.00), evaluateAccount.getBalance());
		evaluateAccount.credit(56.00);
		assertEquals(new Double(56.00), evaluateAccount.getBalance());
	}
	
	@Test
	public void testDebitWithNormalValue()
	{
		evaluateAccount = new Account();
		
		evaluateAccount.credit(100.00);
		evaluateAccount.debit(68.75);
		assertEquals(new Double(31.25), evaluateAccount.getBalance());
	}
	
	@Test
	public void testDebitWithMoreThanBalance()
	{
		evaluateAccount = new Account();
		evaluateAccount.credit(97.86);
		expectedException.expect(IllegalArgumentException.class);
		evaluateAccount.debit(100.00);
	}
}
