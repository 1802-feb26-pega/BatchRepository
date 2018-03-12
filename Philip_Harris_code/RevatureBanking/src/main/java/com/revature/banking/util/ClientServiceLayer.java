package com.revature.banking.util;

import java.util.List;

import com.revature.banking.dao.DaoImpl;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public class ClientServiceLayer {
	
		static DaoImpl dao = new DaoImpl();

		//Method that delAccount that user wants to delete
		public static boolean delAccount(Account account) {
			// TODO Auto-generated method stub
			if(dao.d_account(account)) return true;
			return false;
		}
		
		//Returns a list of usernames that prevent the from taking the same username
		public static boolean getClient(String username){
			List<Client> review =  dao.getClients();
			for(Client usr: review) {
				if(usr.getUsrName().equals(username)) return true;
			}
			return false;
		}


}
