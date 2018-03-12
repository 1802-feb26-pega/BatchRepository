package com.revature.bank.pojos;

import java.text.DecimalFormat;

//Name: Katelyn Peterson
//Date: Mar. 9th, 2018

/**
 * <h1> Account </h1> 
 * The Account class includes the account balance.
 * 
 * @author Katelyn Peterson
 * @version 2.0
 * @since 03-09-2018 
 *
 */
public class Account
{
	// Account will store balance and handle withdrawing and depositing money.  It will validate whether or not 
	// money can be withdrawn.
	
	// Variables
	private int accountId;
	private Double balance;
	static private DecimalFormat newFormat = new DecimalFormat("#.00");
	
	// Constructor
	public Account()
	{
		this.balance = 0.00;
		this.accountId = 0;
	}
	
	public Account(Double balance)
	{
		super();
		this.balance = balance;
	}
	
	// Getters
	public int getAccountId()
	{
		return accountId;
	}
	
	public Double getBalance()
	{
		return balance;
	}
	
	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}
	
	public void setBalance(Double balance)
	{
		this.balance = balance;
	}
	
	// Credit and Debit
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

	@Override
	public String toString()
	{
		return "Account: " + accountId + ", Balance: $" + Double.valueOf(newFormat.format(balance));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}
}
