package com.revature.bank.dao;

import java.util.List;

import com.revature.bank.pojos.Account;
import com.revature.bank.pojos.Customer;

public interface BankDAO {
	public List<Customer> getAllCustomers(); //
	public Customer getCustomerById(int id); //
	public Account getAccountById(int id); //
	public Customer registerCustomer(Customer customer);//
	public Account registerAccount(Account account);//
	//public void logOut(Customer customer);
	//public Customer logIn(Customer customer);
	public int deposit(Account account, int num);
	public int withdraw(Account account, int num);
	public int transfer(Account act1, Account act2);
	public int balance(Account account);
}
