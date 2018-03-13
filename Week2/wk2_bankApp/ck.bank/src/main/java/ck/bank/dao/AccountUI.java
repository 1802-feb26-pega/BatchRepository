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
			System.out.println("\nACCOUNT INTERFACE");
			System.out.println("Make your selection:");
			System.out.println("1 - Display Accounts");
			System.out.println("2 - Create New Account");
			System.out.println("3 - Withdraw Funds");
			System.out.println("4 - Deposit Funds");
			System.out.println("5 - Transfer Funds");
			System.out.println("0 - Exit");
			try
			{
				userInput = Main.sc.nextInt();
				if(userInput >= 0 && userInput <= 5)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 0-5. Try again.\n");
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
				List<Account> accountsWithdraw = Main.aDao.getAllAccounts(this.loggedIn.getUserId());
				Account withdrawSelection=null;

				//select account to withdraw from
				if(accountsWithdraw.isEmpty())
				{
					System.out.println("\n\nNo active accounts\n\n");
				}else
				{
					System.out.println("select account to withdraw from:");
					withdrawSelection = validateAccountSelection(accountsWithdraw);
					withdrawFunds(withdrawSelection);
				}//if-else
				valid = false;
				break;
			case 4:
				//deposit funds
				List<Account> accountsDeposit = Main.aDao.getAllAccounts(this.loggedIn.getUserId());
				Account depositSelection=null;

				//select account for deposit
				if(accountsDeposit.isEmpty())
				{
					System.out.println("\n\nNo active accounts\n\n");
				}else
				{
					System.out.println("select account to withdraw from:");
					depositSelection = validateAccountSelection(accountsDeposit);
					depositFunds(depositSelection);
				}//if-else
				valid = false;
				break;
			case 5:
				//transfer funds between accounts
				List<Account> accountsTransfer = Main.aDao.getAllAccounts(this.loggedIn.getUserId());
				
				//if number of accounts is 0 or 1, print appropriate message --> need at least two active accounts to transfer funds
				if(accountsTransfer.size() == 0)
				{
					System.out.println("\n\nNo active accounts\n\n");
				}else if(accountsTransfer.size() == 1)
				{
					System.out.println("\n\nOnly 1 active account. Need at least 2 to transfer funds\n\n");
				}else
				{
					transferFunds(accountsTransfer);
				}
				
				
				valid = false;
				break;
			case 0:
				//exit AccountUI
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
						continue;
					}//if-else
				}//for
				
				if(!valid)
				{
					System.out.println("\nBad account selection\n");
					value = -1;
					Main.sc.nextLine();
				}

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
					a.setBalance(newBalance);
					System.out.println("\n\nWithdrawal completed successfully.");
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
	}//withdraw funds

	//=============================================================================

	public void depositFunds(Account a)
	{
		double amountToDeposit;
		boolean valid = false;
		double newBalance;
		System.out.println("Current balance: $" + a.getBalance());
		System.out.println("Enter amount to deposit:");

		while(!valid)
		{
			try
			{
				amountToDeposit = Main.sc.nextDouble();
				if(amountToDeposit < 0)
				{
					System.out.println("\n\nNegative input\n");

					System.out.println("Current Balance: $" + a.getBalance());
					System.out.println("Enter amount to deposit:");
				}else
				{
					newBalance = a.getBalance() + amountToDeposit;
					
					Main.aDao.updateAccountBalance(a.getAccountNumber(),newBalance);
					a.setBalance(newBalance);
					System.out.println("\n\nDeposit completed successfully");
					System.out.println("Your new balance is: $" + a.getBalance());
					valid = true;
				}//if-else

			}catch(InputMismatchException ime)
			{

				System.out.println("\n\nNon-number input\n");
				System.out.println("Current balance is: $" + a.getBalance());
				System.out.println("Enter amount to deposit:");
				Main.sc.nextLine();
			}//try-catch
		}//while
	}//deposit funds
	
	//==============================================================================
	
	public void transferFunds(List<Account> accounts)
	{
		double amountToTransfer;
		double senderNewBalance;
		double receiverNewBalance;
		Account sender=null;
		Account receiver=null;
		int value = -1;
		boolean valid = false;
		int iterate = 1;

		//account selection
		while(!valid)
		{
			value = -1;
			for(Account a : accounts)
			{
				System.out.println(a.toString());
			}
			
			if(iterate ==1)
			{
				System.out.println("\nenter account # (sender):");
			}else
			{
				System.out.println("\nenter account # (receiver):");
			}
			try
			{
				value = Main.sc.nextInt();
				for(Account a : accounts)
				{
					if(a.getAccountNumber() == value)
					{
						if(iterate == 1)
						{
							System.out.println("Good account selection for sender: " + value);
							sender = a;
							iterate = 2;
						}else
						{
							if(a.equals(sender))
							{
								System.out.println("Account already selected");
							}else
							{
								System.out.println("Good account selection for receiver: " + value);
								receiver = a;
								valid = true;
							}//if-else
						}//if-else
					}else
					{
						continue;
					}//if-else
				}//for
				
				if(!valid)
				{
					System.out.println("\nBad account selection\n");
					value = -1;
					Main.sc.nextLine();
				}

			}catch(InputMismatchException ime)
			{
				System.out.println("\nNon-int entry\n");
				Main.sc.nextLine();
			}//try-catch
		}//while

		
		
		//input amount to transfer and then transfer funds
		valid = false;
		System.out.println("Available balance: $" + sender.getBalance());
		System.out.println("Account transfers cannot go below $0");
		System.out.println("Enter amount to transfer:");

		while(!valid)
		{
			try
			{
				amountToTransfer = Main.sc.nextDouble();
				if((sender.getBalance()-amountToTransfer) < 0)
				{
					System.out.println("\n\nTransfer too big\n");
					System.out.println("Account transfers cannot go below $0");
					System.out.println("Available Balance: $" + sender.getBalance());
					System.out.println("Enter amount to transfer:");
					Main.sc.nextLine();
				}else if(amountToTransfer < 0)
				{
					System.out.println("\n\nNegative input\n");
					System.out.println("Account transfers cannot go below $0");
					System.out.println("Available Balance: $" + sender.getBalance());
					System.out.println("Enter amount to transfer:");
					Main.sc.nextLine();
				}else
				{
					//transfer funds if amount entered is accepted
					senderNewBalance = sender.getBalance()-amountToTransfer;
					receiverNewBalance = receiver.getBalance()+amountToTransfer;
					Main.aDao.updateAccountBalance(sender.getAccountNumber(),senderNewBalance);
					Main.aDao.updateAccountBalance(receiver.getAccountNumber(),receiverNewBalance);
					sender.setBalance(senderNewBalance);
					receiver.setBalance(receiverNewBalance);
					System.out.println("\n\nTransfer completed successfully");
					System.out.println("(Act# "+sender.getAccountNumber()+") ---|$"+amountToTransfer+"|---> (Act# "+receiver.getAccountNumber()+")");
					System.out.println("New account balances after transfer:");
					System.out.println("Account #: "+sender.getAccountNumber()+ " = $" + sender.getBalance());
					System.out.println("Account #: "+receiver.getAccountNumber()+ " = $" + receiver.getBalance());
					valid = true;
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\n\nNon-number input\n");
				System.out.println("Account transfers cannot go below $0");
				System.out.println("Available balance: $" + sender.getBalance());
				System.out.println("Enter the amount to withdraw:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		
	}//transfer funds
	
	
}//AccountUI
