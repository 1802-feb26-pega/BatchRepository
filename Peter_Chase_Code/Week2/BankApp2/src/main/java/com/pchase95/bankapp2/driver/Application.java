package com.pchase95.bankapp2.driver;

import java.util.List;

import com.pchase95.bankapp2.dao.AccountDAO;
import com.pchase95.bankapp2.dao.AccountDAOImpl;
import com.pchase95.bankapp2.dao.UserDAO;
import com.pchase95.bankapp2.dao.UserDAOImpl;
import com.pchase95.bankapp2.pojos.Account;
import com.pchase95.bankapp2.pojos.TransferResult;
import com.pchase95.bankapp2.pojos.User;
import com.pchase95.bankapp2.ui.WindowController;
import com.pchase95.bankapp2.util.Sanitizer;

public class Application {
	private static User currentUser = null;
	private static final UserDAO userDAO = new UserDAOImpl();
	private static final AccountDAO accountDAO = new AccountDAOImpl();
	
	public static User getUser() { return currentUser; }
	
	
	public static void main(String[] args) {
		WindowController.launchLogIn();
	}
	
	private Application() { }
	
	public static boolean addUser(User newUser) {
		if (userDAO.addUser(newUser)) {
			currentUser = newUser;
			return true;
		}
		return false;
	}
	
	public static boolean addAccount(Account newAccount) {
		return accountDAO.addAccount(newAccount);
	}
	
	public static Account getAccountById(long accountId) {
		return accountDAO.getAccountById(accountId);
	}
	
	public static double getTotalMoney() {
		return accountDAO.totalBalance();
	}
	
	public static TransferResult transfer(Account account, double amount, long recipientAccountId) {
		if (account.getBalance() - amount < 0.0) {
			return TransferResult.NOFUNDS;
		}
		
		Account other = accountDAO.getAccountById(recipientAccountId);
		
		if (other == null) {
			return TransferResult.NOUSER;
		}

		if (account.getId() == other.getId()) {
			return TransferResult.SAMEUSER;
		}
		
		account.setBalance(account.getBalance() - amount);
		accountDAO.updateAccount(account.getId(), account);
		
		other.setBalance(other.getBalance() + amount);
		accountDAO.updateAccount(other.getId(), other);
		
		return TransferResult.SUCCESS;
	}
	
	public static void deposit(Account account, double amount) {
		account.setBalance(account.getBalance() + amount);
		accountDAO.updateAccount(account.getId(), account);
	}
	
	public static List<Account> fetchUserAccounts() {
		return accountDAO.getUserAccounts(currentUser.getId());
	}
	
	public static Account getAccountFromDB(long accountId) {
		return accountDAO.getAccountById(accountId);
	}
	
	public static boolean withdraw(Account account, double amount) {
		if (account.getBalance() - amount >= 0) {
			account.setBalance(account.getBalance() - amount);
			accountDAO.updateAccount(account.getId(), account);
			return true;
		}
		return false;
	}
	
	public static boolean attemptSignIn(String nameOrEmail, String password) {
		User user = null;
		if (Sanitizer.sanitizeUsername(nameOrEmail)) {
			user = userDAO.getUserByName(nameOrEmail);
		} else if (Sanitizer.sanitizeEmail(nameOrEmail)) {
			user = userDAO.getUserByEmail(nameOrEmail);
		}
		
		if (user != null && user.getPassword().equals(password)) {
			currentUser = user;
			return true;
		}
				
		currentUser = null;
		return false;
	}
	
	public static void logOut() {
		currentUser = null;
		WindowController.launchLogIn();
	}

	public static void deleteAccount(Account account) {
		accountDAO.removeAccount(account.getId());
	}
	
}
