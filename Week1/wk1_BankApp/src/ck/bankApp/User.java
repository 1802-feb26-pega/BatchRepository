package ck.bankApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private double balance;
	
	public User() {}
	
	public User(String uName,String fName,String lName,double bal)
	{
		username = uName;
		firstName = fName;
		lastName = lName;
		balance = bal;
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String uName)
	{
		this.username = uName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String fName)
	{
		this.firstName = fName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lName)
	{
		this.lastName = lName;
	}
	
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double newBalance)
	{
		this.balance = newBalance;
	}
	
	public void withdrawFunds()
	{
		Scanner sc = new Scanner(System.in);
		double amountToWithdraw = 0;
		boolean inputCheck = true;
		System.out.println("Your available balance: $" + this.getBalance());
		System.out.println("Your account cannot overdraw more than $100.00");
		System.out.println("Enter the amount you would like to withdraw:");
		
		while(inputCheck)
		{
			try
			{
				amountToWithdraw = sc.nextDouble();
				if((this.getBalance()-amountToWithdraw) < 100)
				{
					System.out.println("!!!!!!!!!!!!!!!!!");
					System.out.println("Your account cannot overdraw more than $100.00");
					System.out.println("Available Balance: $" + this.getBalance());
					System.out.println("Enter amount to withdraw:");
				}else if(amountToWithdraw < 0)
				{
					System.out.println("!!!!!!!!!!!!!!!!!");
					System.out.println("You cannot withdraw negative amounts!");
					System.out.println("Available Balance: $" + this.getBalance());
					System.out.println("Enter amount to withdraw:");
				}else
				{
					this.setBalance(this.getBalance()-amountToWithdraw);
					System.out.println("Withdrawal completed successfully.");
					System.out.println("Your new available balance is: $" + this.getBalance());
					inputCheck = false;
				}//if-else
			}catch(InputMismatchException ime)
			{
				System.out.println("--INVALID ENTRY--INVALID ENTRY--");
				System.out.println("================================");
				System.out.println("Your available balance: $" + this.getBalance());
				System.out.println("Your account cannot overdraw more than $100.00");
				System.out.println("Enter the amount you would like to withdraw:");
			}//try-catch
		}//while
		sc.close();
	}//withdraw funds
	
	public void depositFunds()
	{
		Scanner sc = new Scanner(System.in);
		double amountToDeposit;
		boolean inputCheck = true;
		System.out.println("Your current balance is: $" + this.getBalance());
		System.out.println("Please enter the amount you would like to deposit:");
		
		while(inputCheck)
		{
			try
			{
				amountToDeposit = sc.nextDouble();
				if(amountToDeposit < 0)
				{
					System.out.println("!!!!!!!!!!!!!!!!!");
					System.out.println("You cannot deposit negative amounts!");
					System.out.println("Available Balance: $" + this.getBalance());
					System.out.println("Enter amount to deposit:");
				}else
				{
					this.setBalance(this.getBalance() + amountToDeposit);
					System.out.println("Deposit completed successfully");
					System.out.println("Your new available balance is: $" + this.getBalance());
					
				}
				
			}catch(InputMismatchException ime)
			{
				System.out.println("--INVALID ENTRY--INVALID ENTRY--");
				System.out.println("================================");
				System.out.println("Your current balance is: $" + this.getBalance());
				System.out.println("Please enter the amount you would like to deposit:");
			}
		}
	}//deposit funds
	
	public void checkBalance()
	{
		System.out.println("Available balance: $" + this.getBalance());
	}//check balance
	
	public void displayAccountInformation()
	{
		System.out.println("Your account information:");
		System.out.println("  Username = " + this.getUsername());
		System.out.println("First Name = " + this.getFirstName());
		System.out.println(" Last Name = " + this.getLastName());
	}//display account information

	@Override
	public String toString()
	{
		return new String(username + ":" + firstName + ":" + lastName + ":" + balance);
	}
	
	
	
}
