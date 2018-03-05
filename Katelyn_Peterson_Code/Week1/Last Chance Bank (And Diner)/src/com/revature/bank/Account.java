package com.revature.bank;

//Name: Katelyn Peterson
//Date: Mar. 3rd, 2018

public class Account
{
	// Account will store balance and handle withdrawing and depositing money.  It will validate whether or not 
	// money can be withdrawn.
	
	// Variables
	private Double balance;
	
	// Constructor
	Account()
	{
		this.balance = 0.00;
	}
	
	public Account(Double balance)
	{
		super();
		this.balance = balance;
	}



	// Getters
	public Double getBalance()
	{
		return balance;
	}
	
	public void credit(double accountCredit)
	{
		if (accountCredit > 0)
		{
			balance = balance + accountCredit;
		}		
	}
	
	public void debit(double accountDebit)
	{
		if (accountDebit > 0)
		{
			if (accountDebit <= balance)			
			{
				balance = balance - accountDebit;
			}
			else
			{
				// If the person tries to get more than is there
				throw new IllegalArgumentException();
			}
		}
	}
}
