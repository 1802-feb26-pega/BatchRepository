package com.pchase95.bankapp2.driver;

import java.awt.EventQueue;

import com.pchase95.bankapp2.dao.AccountDAO;
import com.pchase95.bankapp2.dao.AccountDAOImpl;
import com.pchase95.bankapp2.pojos.Account;
import com.pchase95.bankapp2.pojos.TransferResult;
import com.pchase95.bankapp2.ui.Bank;
import com.pchase95.bankapp2.ui.LogIn;
import com.pchase95.bankapp2.ui.SignUp;
import com.pchase95.bankapp2.util.Sanitizer;

public class Application {
	private static Account currentAccount = null;
	private static final AccountDAO actDAO = new AccountDAOImpl();
	
	private Application() { }
	
	public static Account getAccount() { return currentAccount; }
	
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
	
	public static void updateAccount() {
		currentAccount = actDAO.getAccountById(currentAccount.getId());
	}
	
	public static void deposit(double amount) {
		currentAccount.setBalance(currentAccount.getBalance() + amount);
		actDAO.updateAccount(currentAccount.getId(), currentAccount);
	}
	
	public static TransferResult transfer(double amount, String nameOrEmail) {
		if (currentAccount.getBalance() - amount < 0.0) {
			return TransferResult.NOFUNDS;
		}
		
		Account other = null;
		if (Sanitizer.sanitizeUsername(nameOrEmail)) {
			other = actDAO.getAccountByName(nameOrEmail);
		} else if (Sanitizer.sanitizeEmail(nameOrEmail)) {
			other = actDAO.getAccountByEmail(nameOrEmail);
		}
		
		if (other == null) {
			return TransferResult.NOUSER;
		}

		if (currentAccount.getId() == other.getId()) {
			return TransferResult.SAMEUSER;
		}
		
		currentAccount.setBalance(currentAccount.getBalance() - amount);
		actDAO.updateAccount(currentAccount.getId(), currentAccount);
		
		other.setBalance(other.getBalance() + amount);
		actDAO.updateAccount(other.getId(), other);
		
		return TransferResult.SUCCESS;
		
	}
	
	
	public static boolean withdraw(double amount) {
		if (currentAccount.getBalance() - amount >= 0) {
			currentAccount.setBalance(currentAccount.getBalance() - amount);
			actDAO.updateAccount(currentAccount.getId(), currentAccount);
			return true;
		}
		return false;
	}
	
	public static boolean attemptSignIn(String nameOrEmail, String password) {
		Account act = null;
		if (Sanitizer.sanitizeUsername(nameOrEmail)) {
			act = actDAO.getAccountByName(nameOrEmail);
		} else if (Sanitizer.sanitizeEmail(nameOrEmail)) {
			act = actDAO.getAccountByEmail(nameOrEmail);
		}
		
		if (act != null && act.getPassword().equals(password)) {
			currentAccount = act;
			return true;
		}
				
		currentAccount = null;
		return false;
	}
	
	public static void logOut() {
		currentAccount = null;
		launchLogIn();
	}
	
	
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
