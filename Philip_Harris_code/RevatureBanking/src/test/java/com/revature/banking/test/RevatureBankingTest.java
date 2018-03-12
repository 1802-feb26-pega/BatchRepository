package com.revature.banking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.banking.dao.DaoImpl;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.Validation;

public class RevatureBankingTest {
	
	private static final Client client = new Client();
	private static final Validation valid = new Validation();
	private static final Account account = new Account();
	private static final DaoImpl dao = new DaoImpl();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	

	@Before
	public void setUp() throws Exception {
		//System.out.println("Read before code");
	}
	
	@Test 
	public void test_writeUser() {
		client.setfName("Philip");
		client.setlName("Martinez");
		client.setSsn(622199300);
		client.setUsrName("phil2");
		client.setPassword("6017");
		
		account.setAccountNumber(123456789);
		account.setBalance(111111111);
		account.setType(1);
		
		assertTrue(dao.writeUser(client,  account));
	}
	@Test
	public void test_readCustomer() {
		boolean flag = false;
		String usr = "phil2";
		String pwd = "6017";
		dao.readCustomer(client, usr, pwd);
		
		if((client.getUsrName().equals(usr))&(client.getPassword().equals(pwd))) flag = true;
		assertTrue(flag);
	}
	@Test
	public void test_ReadAccount() {
		boolean flag = false;
		Client.setId(21);
		dao.readAccount(client, account);
		if(account.getId() == 47) flag = true;
		assertTrue(flag);
	}
	@Test
	public void test_updateBalance() {
		account.setBalance(5000);
		account.setId(21);
		
		assertTrue(dao.updateBalance(account));
	}
	@Test
	public void test_createAcc() {
		Client.setId(21);
		account.setAccountNumber(00000500000);
		account.setBalance(1000);
		account.setType(2);
		assertTrue(dao.createAcc(account));
	}
@Test

 	public void test_getaccounts() {	
		Client.setId(3);
		boolean found = false;
		List<Account> list = DaoImpl.getAccounts();
		for(Account l: list)
			if(l.getAccountNumber() == 852688640 & l.getBalance() == 27845)
				found = true;
		assertTrue(found);
	}
	@Test
	public void test_d_account() {
		
	}



@Test
	
public void test_getClientsUserName() {
		boolean found = false;
		List<Client> list = DaoImpl.getClients();
		for(Client l: list)
			if(l.getUsrName().equals("pharris"))
				found = true;
		assertTrue(found);
	}

	
	
	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	


}
