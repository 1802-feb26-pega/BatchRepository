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
	/**
	 * This is the default constructor for the Account class.
	 */
	public Account()
	{
		this.balance = 0.00;
		this.accountId = 0;
	}
	
	/**
	 * This is the full constructor for the Account class.
	 * @param balance  This is the amount of money in an Account.
	 */
	public Account(Double balance)
	{
		super();
		this.balance = balance;
		this.accountId = 0;
	}
	
	// Getters
	/**
	 * This is the method to get a particular Account's ID.
	 * @return accountId - This is an Account's ID.
	 */
	public int getAccountId()
	{
		return accountId;
	}
	
	/**
	 * This is the method to get a particular Account's balance.
	 * @return balance - This is an Account's balance.
	 */
	public Double getBalance()
	{
		return balance;
	}
	
	/**
	 * This is the method to set a particular Account's ID.
	 * @param accountId  This is the new ID for an Account.
	 */
	public void setAccountId(int accountId)
	{
		this.accountId = accountId;
	}
	
	/**
	 * This is the method to set a particular Account's balance.  However, this method should not be used.
	 * Use credit or debit instead.
	 * @param balance  This is the new balance for an Account.
	 */
	public void setBalance(Double balance)
	{
		this.balance = balance;
	}
	
	// Credit and Debit
	/**
	 * This method credits money to the Account's balance.
	 * @param accountCredit - This is the credit to add to the Account balance.
	 */
	public void credit(double accountCredit)
	{
		if (accountCredit > 0)
		{
			balance = balance + accountCredit;
		}		
	}
	
	/**
	  * This function debits money from the Account's balance if the balance is higher than the debit.
	  * If the debit is greater, an IllegalArgumentException is thrown, since the debit amount is an 
	  * inappropriate argument.
	  * @param accountDebit - This is the debit to subtract from the Account balance.
	  * @throws IllegalArgumentException
	  */
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
	
	/**
	 * This is the method to get a particular Account's hash code.
	 * @return result - This is the hash code.
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}
	
	/**
	 * This is the method to check if this Account is equal to another Account.
	 * @return false - If the two Accounts are not equal.
	 * @return true - If the two Accounts are equal.
	 */
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
	
	/**
	 * This is the toString method for Account.
	 * @return String - This is all the information concerning an Account.
	 */
	@Override
	public String toString()
	{
		return "Account: " + accountId + ", Balance: $" + Double.valueOf(newFormat.format(balance));
	}

}
