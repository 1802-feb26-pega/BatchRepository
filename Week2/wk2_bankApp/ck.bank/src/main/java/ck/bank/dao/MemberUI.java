package ck.bank.dao;

import java.util.InputMismatchException;
import java.util.Scanner;

import ck.bank.driver.Main;
import ck.bank.pojos.User;



public class MemberUI {
	User loggedIn;

	public MemberUI(User u)
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
			System.out.println("1 - Display Account Information");
			//System.out.println("2 - Check Balance");
			System.out.println("2 - Change Username");
			System.out.println("3 - Change First Name");
			System.out.println("4 - Change Last Name");
			//System.out.println("5 - Withdraw Funds");
			//System.out.println("6 - Deposit Funds");
			System.out.println("0 - Exit");
			try
			{
				userInput = Main.sc.nextInt();
				if(userInput >= 0 && userInput <= 6)
				{
					System.out.println("\nGood selection: "+userInput + "\n");
					valid = true;
				}else
				{
					System.out.println("\nBad, need 0-6. Try again.\n");
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
				//display account information
				displayAccountInformation();
				valid = false;
				break;
			case 2:
				//change username
				changeUsername();
				valid = false;
				break;
			case 3:
				//change first name
				changeFirstName();
				valid = false;
				break;
			case 4:
				//change last name
				changeLastName();
				valid = false;
				break;
			case 5:
				//withdraw funds
				withdrawFunds();
				valid = false;
				break;
			case 6:
				//deposit funds
				depositFunds();
				valid = false;
				break;
			case 0:
				//exit MemberUI
				System.out.println("Exit MemberUI");
				break;
			default:
				System.out.println("MemberUI - default");
			}//switch


		}//while

		//return userInput;
	}//topUI

	//==========================================================

	public void displayAccountInformation()
	{
		System.out.println("Your account information:");
		System.out.println("  Username = " + this.loggedIn.getUsername());
		System.out.println("First Name = " + this.loggedIn.getFirstName());
		System.out.println(" Last Name = " + this.loggedIn.getLastName());
		
	}//display account information

	//==========================================================

	public void checkBalance()
	{
		System.out.println("Available balance: $" + this.loggedIn.getBalance());
	}//check balance

	//==========================================================

	public void withdrawFunds()
	{
		double amountToWithdraw = 0;
		boolean valid = false;
		System.out.println("Available balance: $" + this.loggedIn.getBalance());
		System.out.println("Your account cannot overdraw more than $100.00");
		System.out.println("Enter amount to withdraw:");

		while(!valid)
		{
			try
			{
				amountToWithdraw = Main.sc.nextDouble();
				if((this.loggedIn.getBalance()-amountToWithdraw) < 100)
				{
					System.out.println("\n\nYou cannot withdraw that much");
					System.out.println("Your account cannot overdraw more than $100.00");
					System.out.println("Available Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else if(amountToWithdraw < 0)
				{
					System.out.println("\n\nNegative input");
					System.out.println("You cannot withdraw negative amounts!");
					System.out.println("Available Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to withdraw:");
					Main.sc.nextLine();
				}else
				{
					this.loggedIn.setBalance(this.loggedIn.getBalance()-amountToWithdraw);
					System.out.println("\n\nWithdrawal completed successfully.");
					System.out.println("Your new available balance is: $" + this.loggedIn.getBalance());
					valid = true;
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("\n\nNon-number input");
				System.out.println("Your account cannot overdraw more than $100.00");
				System.out.println("Available balance: $" + this.loggedIn.getBalance());
				System.out.println("Enter the amount to withdraw:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		//sc.close();
	}//withdraw funds

	//=================================================================

	public void depositFunds()
	{
		double amountToDeposit;
		boolean valid = false;
		System.out.println("Current balance: $" + this.loggedIn.getBalance());
		System.out.println("Enter amount to deposit:");

		while(!valid)
		{
			try
			{
				amountToDeposit = Main.sc.nextDouble();
				if(amountToDeposit < 0)
				{
					System.out.println("\n\nNegative input");
					//System.out.println("You cannot deposit negative amounts!");
					System.out.println("Current Balance: $" + this.loggedIn.getBalance());
					System.out.println("Enter amount to deposit:");
				}else
				{
					this.loggedIn.setBalance(this.loggedIn.getBalance() + amountToDeposit);
					System.out.println("\n\nDeposit completed successfully");
					System.out.println("Your new balance is: $" + this.loggedIn.getBalance());
					valid = true;
				}//if-else

			}catch(InputMismatchException ime)
			{
				//System.out.println("--INVALID ENTRY--INVALID ENTRY--");
				//System.out.println("================================");
				System.out.println("\n\nNon-number input");
				System.out.println("Current balance is: $" + this.loggedIn.getBalance());
				System.out.println("Enter amount to deposit:");
				Main.sc.nextLine();
			}//try-catch
		}//while
		//sc.close();
	}//deposit funds

	//=====================================================================

	public void changeFirstName()
	{	
		ClientUI cui = new ClientUI();
		String newF = "";
		String uname = this.loggedIn.getUsername();
		
		newF = cui.validateFirstName();
		this.loggedIn.setFirstName(newF);
		Main.uDao.updateFirstName(newF,uname);
		System.out.println("first name changed");
	}//changeFirstName

	//==================================================================

	public void changeLastName()
	{
		ClientUI cui = new ClientUI();
		String newL = "";
		String uname = this.loggedIn.getUsername();
		
		newL = cui.validateLastName();
		this.loggedIn.setLastName(newL);
		Main.uDao.updateLastName(newL,uname);
		System.out.println("last name changed");
	}//changeLastName
	
	//==================================================================
	
	public void changeUsername()
	{
		ClientUI cui = new ClientUI();
		String newU = "";
		String old = this.loggedIn.getUsername();
		
		
		
		newU = cui.validateUsername();
		this.loggedIn.setUsername(newU);
		Main.uDao.updateUsername(newU,old);
		System.out.println("username changed");
		
		//use prepared statement to update db with new username
		
	}//changeUsername
	
}
