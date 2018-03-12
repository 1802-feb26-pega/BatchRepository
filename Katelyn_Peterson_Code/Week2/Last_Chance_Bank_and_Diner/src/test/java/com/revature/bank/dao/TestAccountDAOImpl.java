package com.revature.bank.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.bank.pojos.Account;

public class TestAccountDAOImpl
{
	static private AccountDAOImpl accountDao = new AccountDAOImpl();

	@Test
	public void testGetAllAccounts()
	{
		boolean find = false;
		
		/*List<Account> accounts = new ArrayList<>();
		Account temp = new Account();
		temp.setAccountId(1);
		accounts.add(temp);*/
		
		List<Account> testAccounts = accountDao.getAllAccounts();
		
		for (Account x : testAccounts)
		{
			if (x.getBalance() == 0.00)
			{
				find = true;
			}
		}
		
		//assertEquals(accounts, testAccounts);
		
		assertTrue(find);
	}
	
	@Test
	public void testGetAllAccountsByUserId()
	{
		List<Account> accounts = new ArrayList<>();
		Account temp = new Account();
		temp.setAccountId(1);
		accounts.add(temp);
		
		List<Account> testAccounts = accountDao.getAccountsByUserId(1);
		
		assertEquals(accounts, testAccounts);
	}
	
	@Test
	public void testGetAllAccountsByAccountId()
	{
		Account temp = new Account();
		temp.setAccountId(1);
		
		Account testAccount = accountDao.getAccountByAccountId(1);
		
		assertEquals(temp, testAccount);
	}

}
