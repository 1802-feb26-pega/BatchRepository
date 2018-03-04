package Bank;

import java.util.ArrayList;

public class Account {

	private String name;
	
	private String uuid;
	
	private User holder;
	
	private ArrayList<Transaction> transactions;
	//Create account object
	
	public Account(String name, User holder, Bank theBank)
	{
		//Set name and holder
		this.name = name;
		this.holder = holder;
		
		//Get a new account UUID
		this.uuid = theBank.getNewAccountUUID();
		
		//init transactions
		this.transactions = new ArrayList<Transaction>();
		
	}
	//Get the account ID
	public String getUUID() {
		return this.uuid;
	}
	
	//Get summary line for the account
	public String getSummaryLine() {
		
		// get the account's balance
		double balance = this.getBalance();
		
		// format summary line depending on whether balance is negative
		if (balance >= 0) {
			return String.format("%s : $%.02f : %s", this.uuid, balance, 
					this.name);
		} else {
			return String.format("%s : $(%.02f) : %s", this.uuid, balance, 
					this.name);
		}
		
}
	
	//Gets the balance for the user
	public double getBalance()
	{
		double balance = 0;
		for (Transaction t : this.transactions)
		{
			balance += t.getAmount();
		}
		return balance;
	}
	
	//Print the transaction history of the account
	public void printTransHistory() {
		
		System.out.printf("\nTransaction history for account %s\n", this.uuid);
		for (int t = this.transactions.size()-1; t >= 0; t--) {
			System.out.println(this.transactions.get(t).getSummaryLine());
		}
		System.out.println();
	}
	
	//Add transaction method
	public void addTransaction(double amount, String memo) {
		
		// create new transaction and add it to our list
		Transaction newTrans = new Transaction(amount, memo, this);
		this.transactions.add(newTrans);
	
	}
	
	
}
