package Bank;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class User {

	private String firstName;
	
	private String lastName;
	
	private String uuid;
	
	private byte pinHash[];
	
	private ArrayList<Account> accounts;
	
	
	public User(String firstName, String lastName, String pin, Bank theBank)
	{
		//Set user's name
		this.firstName = firstName;
		this.lastName = lastName;
	
		//Store pin's hash rather than original value for better security
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			this.pinHash = md.digest(pin.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error, caught noSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
		}
	
		//Get a new universal ID for the user
		this.uuid = theBank.getNewUserUUID();
	
		//Create empty list of accounts
		this.accounts = new ArrayList<Account>();
		System.out.printf("New User %s, %s with ID, %s created. \n", lastName, firstName, this.uuid);
	
	
	}
		
	//Adds an account for the user
	public void addAccount(Account anAcct)
		{
			this.accounts.add(anAcct);
		}
	
		//Method for returning UUID
	public String getUUID()
	{
		return this.uuid;
	}
	
	//Check whether the given pin matches the users true pin
	public boolean validatePin(String aPin)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Error, caught noSuchAlgorithmException");
			e.printStackTrace();
			System.exit(1);
			
		}
		return false;
	}
	
	public String getFirstName()
	{
		return this.firstName;
	}
	
	//Prints a summary of the accounts of the user
	public void printAccountsSummary() {
		
		System.out.printf("\n\n%s's accounts summary\n", this.firstName);
		for (int a = 0; a < this.accounts.size(); a++) {
			System.out.printf("%d) %s\n", a+1, 
					this.accounts.get(a).getSummaryLine());
		}

			System.out.println();}
	
	//Method to get the number of accounts
	public int numAccounts() {
	return this.accounts.size();
}	
	
	//Method to print the account transaction history with the account index
	public void printAcctTransHistory(int acctIdx) {
		this.accounts.get(acctIdx).printTransHistory();
}

	//Method for getting the account balance
	public double getAcctBalance(int acctIdx)
	{
		return this.accounts.get(acctIdx).getBalance();
	}
	
	//Method for getting the account's uuid
	public String getAccountUUID(int acctIdx)
	{
		return this.accounts.get(acctIdx).getUUID();
		}

	public void addAcctTransaction(int acctIdx, double amount, String memo) {
		this.accounts.get(acctIdx).addTransaction(amount, memo);
}


}

