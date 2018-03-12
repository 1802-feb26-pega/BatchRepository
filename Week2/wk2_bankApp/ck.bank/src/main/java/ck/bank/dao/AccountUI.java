package ck.bank.dao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import ck.bank.driver.Main;
import ck.bank.pojos.Account;
import ck.bank.pojos.User;

public class AccountUI {
	User loggedIn;

	public AccountUI(User u)
	{
		this.loggedIn = u;
	}

	public void topUI()
	{

		int userInput = -1;
		boolean valid = false;

		while(!valid)
		{
			userInput = -1;
			System.out.println("\n\nMake your selection:");
			System.out.println("1 - Display Accounts");
			System.out.println("2 - Create New Account");
			System.out.println("3 - Withdraw Funds");
			System.out.println("4 - Deposit Funds");
			System.out.println("0 - Exit");
			try
			{
				userInput = Main.sc.nextInt();
				if(userInput >= 0 && userInput <= 4)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 0-3. Try again.\n");
					Main.sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int input. Try again.\n");
				Main.sc.nextLine();
			}//try-catch



			//switch statement
			switch(userInput)
			{
			case 1:
				//get list of accounts from accountdao
				List<Account> accounts = Main.aDao.getAllAccounts(this.loggedIn.getUserId());

				//print them to screen
				if(accounts.size() == 0)
				{
					System.out.println("\n\nNo active accounts\n\n");
				}else
				{
					for(Account a : accounts)
					{
						System.out.println(a.toString());
					}//for
				}//if-else

				valid = false;
				break;
			case 2:
				//create new account
				//get starting balance - validate balance
				double newBalance = validateBalance();
				//send starting balance and userid to accountdao to create new account
				Main.aDao.createNewAccount(this.loggedIn.getUserId(), newBalance);
				System.out.println("account created successfully");
				valid = false;
				break;
			case 3:
				//withdraw funds
				//display list of active accounts
				List<Account> accountWithdraw = Main.aDao.getAllAccounts(this.loggedIn.getUserId());
				Account withdrawSelection=null;
				double amountToWithdraw=-1;

				//select account to withdraw from
				if(accountWithdraw.isEmpty())
				{
					System.out.println("\n\nNo active accounts\n\n");
				}else
				{
					System.out.println("select account to withdraw from:");
					withdrawSelection = validateAccountSelection(accountWithdraw);
					withdrawFunds(withdrawSelection);

				}//if-else

				valid = false;
				break;
			case 4:
				//deposit funds
				valid = false;
				break;
			case 0:
				//exit MemberUI
				System.out.println("Exit AccountUI");
				break;
			default:
				System.out.println("AccountUI - default");
			}//switch

		}//while

	}//topUI

	//========================================================

	public double validateBalance()
	{
		double newB = 0;
		boolean valid = false;

		while(!valid)
		{
			System.out.println("Enter starting balance: (greater than 0)");
			try
			{
				newB = Main.sc.nextDouble();
				if(newB>=0)
				{
					System.out.println("Good balance: " + newB);
					valid = true;
				}else
				{
					System.out.println("\nStarting balance must be greater than 0\n");
					Main.sc.nextLine();
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-double entry\n");
				Main.sc.nextLine();
			}//try-catch
		}//while


		return newB;
	}//validateBalance

	//=============================================================

	public Account validateAccountSelection(List<Account> accounts)
	{
		int value = -1;
		boolean valid = false;

		while(!valid)
		{
			value = -1;
			for(Account a : accounts)
			{
				System.out.println(a.toString());
			}
			System.out.println("\nenter account #:");
			try
			{
				value = Main.sc.nextInt();
				for(Account a : accounts)
				{
					if(a.getAccountNumber() == value)
					{
						System.out.println("Good account selection: " + value);
						
						return a;
					}else
					{
						System.out.println("\nBad account selection\n");
						Main.sc.nextLine();
					}//if-else
				}//for

			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int entry\n");
				Main.sc.nextLine();
			}//try-catch
		}//while


		return null;
	}//validateAccountSelection

	//==========================================================

	public void withdrawFunds(Account a)
	{
		double amountToWithdraw = 0;
		boolean valid = false;
		double newBalance;
		System.out.println("Available balance: $" + a.getBalance());
		System.out.println("Account cannot overdraw more than $100.00");
		System.out.println("Enter amount to withdraw:");

		while(!valid)
		{
			try
			{
				amountToWithdraw = Main.sc.nextDouble();
				if((a.getBalance()-amountToWithdraw) < 100)
				{
					System.out.println("\n\nWithdrawal too big\n");
					System.out.println("Account cannot overdraw more than $100.00");
					System.out.println("Available Balance: $" + a.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else if(amountToWithdraw < 0)
				{
					System.out.println("\n\nNegative input\n");
					System.out.println("Account cannot overdraw more than $100.00");
					System.out.println("Available Balance: $" + a.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else
				{
					newBalance = a.getBalance()-amountToWithdraw;
					Main.aDao.updateAccountBalance(a.getAccountNumber(),newBalance);
					
					System.out.println("\n\nWithdrawal completed successfully.");
					a.setBalance(newBalance);
					System.out.println("Your new available balance is: $" + a.getBalance());
					valid = true;
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\n\nNon-number input\n");
				System.out.println("Account cannot overdraw more than $100.00");
				System.out.println("Available balance: $" + a.getBalance());
				System.out.println("Enter the amount to withdraw:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		//sc.close();
	}//withdraw funds
}//AccountUI
