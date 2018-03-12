package com.revature.bank.driver;


import java.util.List;

import com.revature.bank.dao.BankDAO;
import com.revature.bank.dao.BankDAOImpl;
import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.Customer;

public class BankDriver {

	public static void main(String[] args) {
		BankDAO bankDao = new BankDAOImpl();
		
		List<Customer> customers = bankDao.getAllCustomers();
		
		for(Customer a : customers) {
			System.out.println(a);
		}
		
		
		Customer customer = new Customer();
		customer = bankDao.getCustomerById(1);
		System.out.println(customer);
		
		Account account = new Account();
		account = bankDao.getAccountById(1);
		System.out.println(account);
		
		Customer temp = new Customer("Edgar","Martinez","edog","mariners");
		
		customer = bankDao.registerCustomer(temp);
		System.out.println(customer);
	}

}
