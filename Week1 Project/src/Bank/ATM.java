package Bank;

import java.util.Scanner;

public class ATM {
	
	
	
	
	
	public static void main(String[] args) {
		
		//init Scanner
		Scanner sc = new Scanner(System.in);
		
		//init Bank
		Bank theBank = new Bank("Smith Bank");
		
		//Add a user which also creates a savings account
		User aUser = theBank.addUser("Josh", "Smith", "1234");
		
		//Add a checking account
		Account newAccount = new Account("Checking", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		
		User curUser;
		while(true)
		{
			//Stay in the login prompt until successful
			curUser = ATM.mainMenuPrompt(theBank, sc);
			
			//Stay in main menu until user quits
			ATM.printUserMenu(curUser, sc);
		}
	}	
	//Method for main menu prompt
	public static User mainMenuPrompt(Bank theBank, Scanner sc)
	{
		String userID;
		String pin;
		User authUser;
		
		//Prompt the user for userID and Pin until correct
		do {
			System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
			System.out.print("Enter User ID:");
			userID = sc.nextLine();
			System.out.printf("Enter Pin: ");
			pin = sc.nextLine();
			
			//Try to get user object to correspond with user ID and pin 
			authUser = theBank.userLogin(userID, pin);
			if(authUser == null)
			{
				System.out.println("Incorrect User ID or PIN, please try again.");
			}
			
		}while(authUser == null);//Continue looping until successful login
		
		return authUser;
	}
	
	//Giant method for printing the user menu
	public static void printUserMenu(User theUser, Scanner sc)
	{
		//Print a summary of the users accounts
		theUser.printAccountsSummary();
		
		//init
		int choice;
		
		//User Menu
		do {
			System.out.println("What would you like to do?");
			System.out.println("  1) Show Transaction History");
			System.out.println("  2) Withdraw");
			System.out.println("  3) Deposit");
			System.out.println("  4) Transfer");
			System.out.println("  5) Quit");
			System.out.println();
			System.out.println("Please enter choice");
			choice = sc.nextInt();
		
			if (choice < 1 || choice > 5)
			{
				System.out.println("Invalid choice, please enter 1-5");
			}
		}while (choice < 1 || choice > 5);
	
	
		//Process the choice
		switch(choice)
		{
		case 1:
			ATM.showTransHistory(theUser, sc);
			break;
		case 2:
			ATM.withdrawFunds(theUser, sc);
			break;
		case 3:
			ATM.depositFunds(theUser, sc);
			break;
		case 4:
			ATM.transferFunds(theUser, sc);
			break;
		case 5:
			// Gather rest of previous input
			sc.nextLine();
			break;
		}
		//Redisplay unless the user wants to quit
			if (choice != 5)
			{
				ATM.printUserMenu(theUser, sc);
			}
	
	}
	
	//Shows the transaction history for an account
	public static void showTransHistory(User theUser, Scanner sc) {
		
		int theAcct;
		
		// get account whose transactions to print
		do {
			System.out.printf("Enter the number (1-%d) of the account\nwhose " + 
					"transactions you want to see: ", theUser.numAccounts());
			theAcct = sc.nextInt()-1;
			if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (theAcct < 0 || theAcct >= theUser.numAccounts());
		
		// print the transaction history
		theUser.printAcctTransHistory(theAcct);
	
	}		
	
	//Method for transferring funds
	public static void transferFunds(User theUser, Scanner sc)
	{
		//Inits
		int fromAcct;
		int toAcct;
		double amount;
		double acctBal;
		
		//Get the account to transfer from
		// get account to transfer from
				do {
					System.out.printf("Enter the number (1-%d) of the account to " + 
							"transfer from: ", theUser.numAccounts());
					fromAcct = sc.nextInt()-1;
					if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
						System.out.println("Invalid account. Please try again.");
					}
				} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
				acctBal = theUser.getAcctBalance(fromAcct);
				
				// get account to transfer to
				do {
					System.out.printf("Enter the number (1-%d) of the account to " + 
							"transfer to: ", theUser.numAccounts());
					toAcct = sc.nextInt()-1;
					if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
						System.out.println("Invalid account. Please try again.");
					}
				} while (toAcct < 0 || toAcct >= theUser.numAccounts());
				
				// get amount to transfer
				do {
					System.out.printf("Enter the amount to transfer (max $%.02f): $", 
							acctBal);
					amount = sc.nextDouble();
					if (amount < 0) {
						System.out.println("Amount must be greater than zero.");
					} else if (amount > acctBal) {
						System.out.printf("Amount must not be greater than balance " +
								"of $.02f.\n", acctBal);
					}
		} while (amount < 0 || amount > acctBal);
		//Finally, do the transfer
		theUser.addAcctTransaction(fromAcct, -1*amount, String.format("Transfer to account%s", theUser.getAccountUUID(toAcct)));
		theUser.addAcctTransaction(toAcct, +1*amount, String.format("Transfer to account%s", theUser.getAccountUUID(fromAcct)));
}

	//Method for withdrawing funds
	public static void withdrawFunds(User theUser, Scanner sc) {
		
		int fromAcct;
		double amount;
		double acctBal;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"withdraw from: ", theUser.numAccounts());
			fromAcct = sc.nextInt()-1;
			if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(fromAcct);
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to withdraw (max $%.02f): $", 
					acctBal);
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} else if (amount > acctBal) {
				System.out.printf("Amount must not be greater than balance " +
						"of $%.02f.\n", acctBal);
			}
		} while (amount < 0 || amount > acctBal);
		
		// Gather rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the withdrwal
		theUser.addAcctTransaction(fromAcct, -1*amount, memo);
		
}

	//Mehod for depositing funds
	public static void depositFunds(User theUser, Scanner sc) {
		
		int toAcct;
		double amount;
		String memo;
		
		// get account to withdraw from
		do {
			System.out.printf("Enter the number (1-%d) of the account to " + 
					"deposit to: ", theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
			}
		} while (toAcct < 0 || toAcct >= theUser.numAccounts());
		
		// get amount to transfer
		do {
			System.out.printf("Enter the amount to deposit: $");
			amount = sc.nextDouble();
			if (amount < 0) {
				System.out.println("Amount must be greater than zero.");
			} 
		} while (amount < 0);
		
		// Gather rest of previous input
		sc.nextLine();
		
		// get a memo
		System.out.print("Enter a memo: ");
		memo = sc.nextLine();
		
		// do the deposit
		theUser.addAcctTransaction(toAcct, amount, memo);
		
}





}
