package BankInside.access;

import java.util.List;

import BankInside.pojos.Account;
import BankInside.pojos.User;

public interface AccountDAO {
	
	public void updateAccount(Account u);
	public void saveAccount(Account u);
	public Account getAccountbyId (int id);
	public List<Account> getAccountsbyId (int id);
	public List<Account> getAllAccounts();
}
