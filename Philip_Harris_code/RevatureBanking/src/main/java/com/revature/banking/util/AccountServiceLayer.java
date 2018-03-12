package com.revature.banking.util;

import java.util.List;
import java.util.Random;

import com.revature.banking.dao.DaoImpl;
import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;

public class AccountServiceLayer {
	
	static DaoImpl dao = new DaoImpl();

	//Method that withdraws the balance and checks to see if account
	//would go negative. If negative throws back false and warns user
	public static boolean withdraw(int w,Account a) {
		int temp = a.getBalance() - w;
		if(temp >= 0) {
			a.setBalance(temp);			
			return dao.updateBalance(a);
		}
		return false;
	}
	
	//Method that deposits money into account
	public static boolean deposit(int d,Account a) {
		// TODO Auto-generated method stub
		a.setBalance(a.getBalance() + d);
		 return dao.updateBalance(a);
	}
	
	//Method that generates an Account number that is 9 digits long
	public static Long account_Gen() {
		Random r = new Random();
		Long Low = 100000000L;
		Long High = 999999999L;
		Long Result = r.nextInt((int)(High-Low)) + Low;
		return Result;
	}	
	
	//Creates an account with users input
	public static boolean createAccount(Account a,int amount, int typeof) {
		// TODO Auto-generated method stub
		
		a.setType(typeof);
		a.setBalance(amount);
		a.setAccountNumber(account_Gen());
		
		if(dao.createAcc(a)) return true;
			return false;
	}
	
	//Return list of accounts that current user has
	public static List<Account> getAccounts(){
		return dao.getAccounts();
	}
	
	//Method that transfers money from one account to another.
	public static boolean transfer(Account debit, Account credit,int m) {
		// TODO Auto-generated method stub
			debit.setBalance(debit.getBalance() - m);
			credit.setBalance(credit.getBalance() + m);
			if(debit.getBalance() >= 0) {
				if( dao.updateBalance(debit) & dao.updateBalance(credit)) return true;
				else return false;
			}
		return false;
	}
}
