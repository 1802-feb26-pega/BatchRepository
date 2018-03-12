package com.revature.banking.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import com.revature.banking.util.AccountServiceLayer;
import com.revature.banking.util.ConnectionFactory;
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
		//delusr();
		//delAcc();
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
		Client.setId(49);
		dao.readAccount(client, account);
		if(account.getId() == 75) flag = true;
		assertTrue(flag);
	}
	@Test
	public void test_updateBalance() {
		account.setBalance(5000);
		account.setId(49);

		assertTrue(dao.updateBalance(account));
	}
	@Test
	public void test_createAcc() {
		Client.setId(49);
		account.setAccountNumber(00000500000);
		account.setBalance(1000);
		account.setType(2);
		assertTrue(dao.createAcc(account));
		//delAcc();
	}
	@Test

	public void test_getaccounts() {	
		Client.setId(49);
		boolean found = false;
		List<Account> list = dao.getAccounts();
		for(Account l: list)
			if(l.getAccountNumber() == 163840)
				found = true;
		assertTrue(found);
	}
	@Test
	public void test_d_account() {
		account.setId(13);
		assertTrue(dao.d_account(account));
	}
	@Test

	public void test_getClientsUserName() {
		boolean found = false;
		List<Client> list = dao.getClients();
		for(Client l: list)
			if(l.getUsrName().equals("pharris"))
				found = true;
		assertTrue(found);
	}

	
	
	@Test
	public void test_access() {
		Client.setId(49);
		String username = "phil2";
		String pass = "6017";
		
		assertTrue(valid.access(client, account, username, pass));
		
	}
	@Test
	public void test_validateNumSSN() {
		String n = "984653287";
		
		assertEquals(Integer.parseInt(n),valid.validateNumSSN(n));
	}
	@Test
	public void test_validateNum() {
		String n = "2";
		assertEquals(Integer.parseInt(n),valid.validateNum(n));
	}
	@Test
	public void test_encode_decode() {
		String code = "test";
		String hash = valid.encode(code);
		assertTrue(code.equals(valid.decode(hash)));
	}
	@Test
	public void test_ROT5() {
		int n = 9874562;
		int hash = valid.eROT5(n);
		assertTrue(valid.eROT5(hash)==n);
	}
	@Test
	public void test_checking_signup() {
		assertTrue(valid.checking_signup(4, 5));
	}
	@Test
	public void test_writeNewUser() {
		assertTrue(valid.writeNewUser(client, account));
	}
	@Test
	public void test_check_update() {
		int num = -1;
		assertTrue(!valid.check_update(num));
	}
	
	@Test
	public void test_withdraw() {
		int n = 324;
		account.setBalance(500);
		assertTrue(AccountServiceLayer.withdraw(324,account));
	}
	
	@Test
	public void test_deposit() {
		int n = 324;
		account.setBalance(500);
		assertTrue(AccountServiceLayer.deposit((324),account));
	}
	
	@Test
	public void test_Acc_Gen() {
		Long acc_num = AccountServiceLayer.account_Gen();
		assertTrue(acc_num > 0 & acc_num < 999999999L);
	}
	
	@After
	public void tearDown() throws Exception {		

	}
	
	public void delAcc() {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "DELETE FROM ACCOUNT WHERE ACC_ID = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, account.getId());

			int check = p_statement.executeUpdate();	

			if(Validation.check_update(check)){
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void delusr() {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){

			conn.setAutoCommit(false);

			String sql = "DELETE FROM CUSTOMER WHERE U_ID = ?";

			PreparedStatement p_statement = conn.prepareStatement(sql);
			p_statement.setInt(1, client.getId());

			int check = p_statement.executeUpdate();	

			if(Validation.check_update(check)){
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}



}
