package com.pchase95.bankapp2.driver;

import java.awt.EventQueue;

import com.pchase95.bankapp2.dao.AccountDAO;
import com.pchase95.bankapp2.dao.AccountDAOImpl;
import com.pchase95.bankapp2.pojos.Account;
import com.pchase95.bankapp2.pojos.TransferResult;
import com.pchase95.bankapp2.ui.Bank;
import com.pchase95.bankapp2.ui.LogIn;
import com.pchase95.bankapp2.ui.SignUp;

public class Application {
	private static Account currentAccount = null;
	private static AccountDAO actDAO = new AccountDAOImpl();
	
	private Application() { }
	
	public static void main(String[] args) {
		launchLogIn();
	}
	
	
	public static boolean addAccount(Account newAccount) {
		return actDAO.addAccount(newAccount);
	}
	
	public static void deleteAccount() {
		actDAO.removeAccount(currentAccount.getId());
		logOut();
	}
	
	public static void deposit(double amount) {
		currentAccount.setBalance(currentAccount.getBalance() + amount);
		actDAO.updateAccount(currentAccount.getId(), currentAccount);
	}
	
	public static TransferResult transfer(double amount, String nameOrEmail) {
		if (currentAccount.getBalance() - amount < 0.0) {
			return TransferResult.NOFUNDS;
		}
		
		for (Account other : actDAO.getAllAccounts()) {
			if (other.getName().equals(nameOrEmail)
			|| other.getEmail().equals(nameOrEmail)) {
				if (currentAccount.equals(other)) {
					return TransferResult.SAMEUSER;
				}
				
				currentAccount.setBalance(currentAccount.getBalance() - amount);
				other.setBalance(other.getBalance() + amount);
				return TransferResult.SUCCESS;
			}
		}
		return TransferResult.NOUSER;
	}
	
	
	public static boolean withdraw(double amount) {
		if (currentAccount.getBalance() - amount >= 0) {
			currentAccount.setBalance(currentAccount.getBalance() - amount);
			actDAO.updateAccount(currentAccount.getId(), currentAccount);
			return true;
		}
		return false;
	}
	
	public static void attemptSignIn(String nameOrEmail, String password) {
		for (Account act : actDAO.getAllAccounts()) {
			if (nameOrEmail.equals(act.getName()) || nameOrEmail.equals(act.getEmail())
			&& password.equals(act.getPassword())) {
				currentAccount = act;
				return;
			}
		}
		
		currentAccount = null;
	}
	
	public static void logOut() {
		currentAccount = null;
		launchLogIn();
	}
	
	public static Account getAccount() { return currentAccount; }
	
	public static void launchLogIn() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn login = new LogIn();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchSignUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp signup = new SignUp();
				
					signup.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	public static void launchBank() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bank bank = new Bank();
					bank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
