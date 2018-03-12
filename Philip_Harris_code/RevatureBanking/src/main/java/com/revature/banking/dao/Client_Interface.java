package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public interface Client_Interface{
		public boolean writeUser(Client c, Account a);
		public void readCustomer(Client c, String usrname, String pass);
		public  List<Client> getClients();
	}

