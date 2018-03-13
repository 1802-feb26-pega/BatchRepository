package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.Customer;

public interface BankDAO {
	
	public Customer getCustomerById(int id); 
	public Account getAccountById(int id); 
	public Customer getCustomerByUsername(String username);
	public Customer registerCustomer(Customer customer);
	public Account registerAccount(Account account);
	public Account updateBalance(Account account, int num);
	public int balance(Account account);
	public List<Customer> getAllCustomers();
}
