package com.revature.banking.driver;


import java.util.List;
import java.util.Scanner;


import com.revature.banking.pojos.Account;
import com.revature.banking.pojos.Client;
import com.revature.banking.util.AccountServiceLayer;
import com.revature.banking.util.ClientServiceLayer;
import com.revature.banking.util.Validation;

public class BankDriver {

	static Scanner scan = new Scanner(System.in);
	static Account account;
	static Client customer;
	static int tries = 0;
	static boolean exit = false;

	static boolean valid = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean loop = true;
		int choice = 0;
		System.out.println("Hello welcome to Revature Banking!");
		System.out.println("Where we take care of all your banking needs.");

		//Keeps the menu up and running even after completing a list of commands 
		//Also allows the user to log out if they want to
		//Allows user to Sign in or Sign up
		while(loop) {
			System.out.println("Please read the following menu as our options may have changed:");
			System.out.println("If you would like to SIGN IN please PRESS 1");
			System.out.println("If you would like to SIGN UP please PRESS 2");
			System.out.println("If you you feel like you are in the wrong place and/or you would like to exit please PRESS 3");
			System.out.print("Input Here:");

			
			//validateNum() validates all numbers
			choice = Validation.validateNum(scan.next());
			System.out.println();
			System.out.println();

			//Calls the sign up or the sign in method
			//inputvalidation where if the user did not enter a valid input
			//Forces user to input a valid number
			switch(choice) {
			case 1: signin();break;
			case 2: signup(); break;
			case 3: 
				System.out.println("Thank for visiting Revature Banking Bye-bye");
				loop = false;
				break;
			default:
				System.out.println("Please enter in one of the options");
				System.out.println();
			}

		}
	}




	//User inputs infomation about account and self
	//to create a new user and account
	//Everything will be sent to the DB after being validated in the 
	//Service layer
	private static void signup() {
		// TODO Auto-generated method stub

		System.out.print("Please enter your First Name:");	
		String fn = scan.next();
		System.out.println();
		System.out.println();
		
		System.out.print("Please enter in your Last Name:");
		String ln = scan.next();
		System.out.println();
		System.out.println();

		System.out.print("Please enter in your SSN:"); //strip the dashes if used
		int ssn = Validation.validateNumSSN(scan.next());

		System.out.println();
		System.out.println();

		System.out.println("*Note: Please do not use your name or your SSN as apart of your User Name and Password.");
		System.out.print("Please enter in your desired User Name:");
		String usr = scan.next();
		
		System.out.println();
		System.out.println();

		//Blocks user from using a non unique password
		while(true) {
			if(!ClientServiceLayer.getClient(usr)) break;
			System.out.println("Username is already taken please choose another: ");
			usr = scan.next();
			System.out.println();
			System.out.println();
		}
		
		System.out.println();
		System.out.print("Please enter in your desired password:");
		String pwd = scan.next();
		System.out.println();
		System.out.println();

		System.out.println("How much money will you like to put in initially?");
		int amount = Validation.validateNum(scan.next());
		System.out.println();
		System.out.println();


		System.out.println("We have added a couple of more option to our application");
		System.out.println("Please read the following menu as our options may have changed:");
		System.out.println("If you want a Checking account PRESS 1");
		System.out.println("If you want a Credit account PRESS 2");
		System.out.println("If you want a Saving account PRESS 3");
		System.out.println("If you want a Loans account PRESS 4");
		System.out.println("What type of account do you want?");
		int typeof = user_type(Validation.validateNum(scan.next()));
		System.out.println();
		System.out.println();


		String checkInfo; 
		String type = null;

		//Asks user to verify that the information entered in is correct and
		//will allow them to restart process is incorrect

		while(true) {

			System.out.println();
			System.out.println("Please review the following information:");
			System.out.println();
			System.out.println("[First Name: " + fn + "]");
			System.out.println();
			System.out.println("[Last Name: " + ln + "]");
			System.out.println();
			System.out.println("[Social: " + ssn + "]");
			System.out.println();
			System.out.println("[User Name: " + usr + "]");
			System.out.println();
			System.out.println("[Password: " + pwd + "]");
			System.out.println();
			System.out.println("[Starting amount: "  + amount + "]");
			System.out.println();

			switch(typeof) {
			case 1:  type = "Checking";break;
			case 2:  type = "Credit"; break;
			case 3:  type = "Saving";break;
			case 4:  type = "Loans";break;
			}

			System.out.println("Type of account : " + type);
			System.out.println();


			System.out.println("Is it correct?");
			System.out.print("Input here:");


			checkInfo = scan.next();
			System.out.println();
			System.out.println();

			if(checkInfo.equalsIgnoreCase("yes") |  checkInfo.equalsIgnoreCase("y")){	
				customer = new Client(fn,ln,ssn,usr,pwd);				
				account = new Account(typeof, amount, null);

				if(Validation.writeNewUser(customer, account)) {
					System.out.println(fn + " " + ln + "  has been added successfully");
					System.out.println("Thank you for signing up with Revature bank!");
					System.out.println();
					System.out.println();
				}
				else {
					System.out.println("Problems have occured please try again.");
					System.out.println();
					System.out.println();
				}

				break;
			}
			else if(checkInfo.equalsIgnoreCase("no") | checkInfo.equalsIgnoreCase("n")) {
				System.out.println();
				System.out.println();
				signup();//Form of recusrion
				break;
			}
			else {
				System.out.println("Please enter in Y or Yes for confirmation.");
				System.out.println("Please enter in N or No to restart.");
				System.out.println();
				System.out.println();		
			}

		}
	}


	//List of type of accounts
	
	public static int user_type(int wants) {
		int temp = 0;


		switch(wants) {
		case 1: temp = 1; break;//"Checking";
		case 2: temp = 2; break;//"Credit"; 
		case 3: temp = 3; break;//"Saving"; 
		case 4: temp = 4; break;// "Loans";
		}
		return temp;

	}

	//Allows the user to sign in if the user credetentails are correct
	//Calls startBanking to start interacting with own accoutns

	private static void signin() {

		boolean bankloop = true;

		System.out.print("Please enter in your UserName:");
		String usrName = scan.next();

		System.out.print("Please enter in your Password:");
		String pwd = scan.next();


		System.out.println("");
		System.out.println("");
		System.out.println("");

		customer = new Client();
		account = new Account();
		//Multiple login in attempts 3
		//After 3 attempts returns to main menu
		if (Validation.access(customer,account,usrName,pwd))
			while(bankloop) 
				bankloop = startBanking();
		else {
			System.out.println("Access denied");
			System.out.println("Password and/or Username was incorrect");
			System.out.println();
			if(tries <= 1) {
				tries++; signin();
			}
			else {
				System.out.println();
				System.out.println();
				System.out.println("Too many attempts returning to main menu");
				System.out.println();
				System.out.println();
				return;
			}
		}		

	}

	//Starts the process of interacting with owned accounts
	//User can add, delete, withdraw and transfer money from accounts
	//In order to transfer money one must have and empty account.
	//Also allows user to create accounts
	//Once user logs out clears user object to prevent data leaking

	private static boolean startBanking() {
		// TODO Auto-generated method stub
		System.out.println("Welcome " + customer.getfName() + " " + customer.getlName());
		System.out.println("What you would you like to do?");
		System.out.println("PRESS 1 to Check Balance");
		System.out.println("PRESS 2 to Withdraw");
		System.out.println("PRESS 3 to Deposit");
		System.out.println("PRESS 4 to Add An Account");
		System.out.println("PRESS 5 to View Accounts");
		System.out.println("PRESS 6 to Transfer money");
		System.out.println("PRESS 7 to Remove Account");
		System.out.println("PRESS 0 to Exit");
		System.out.print("Input here: ");
		int option = Validation.validateNum(scan.next());
		System.out.println("");

		switch(option) {
		//Grabs the last account added balance
		//Displays it to user
		case 1: check_Bal(); break;
		
			//Prompts user to input witdraw funds
			//Shows balance after transaction
			//If user tries to witdraw more than allowed sends error message
		
		case 2: withdraw(); break;
		
			//Prompts user to input deposit funds
			//Shows balance after transaction
			//No deposit limit
		
		case 3:deposit(); break;
		
			//Creates a new account based on user input
			//If problems arises informs
			//sends information to database and stores it there
		
		case 4: new_Account(); break;
		
			//Shows list of account to the user
			//If user has no account system prompts user to create one 
		
		case 5: listOfAccounts();; break;
		
			//Transfer money if and only if user has two accounts
			//Takes money from the debitor and give it to the creditor
			//If transfer fails informs user
		case 6: transfer(); break;

			//Deletes account informations
			//Prompts user for input just in case the user made a mistake
			//Once account is deleted all information is lost
			//Forces user to transfer all money to another account before 
		case 7: delete(); break;			
		case 0: 
			return clear();
		}
		return true;
	}
	public static void check_Bal() {
		if(!Validation.haveAccount()) return;
		List<Account> acc = AccountServiceLayer.getAccounts();
		System.out.println("Here is the list of your accounts:");
				
		int num_of_accounts = 1;
		for(Account x: AccountServiceLayer.getAccounts()) {
			System.out.println(num_of_accounts + ". " + x.showAccountNumber());
			System.out.println();
			num_of_accounts++;
		}
		
		System.out.println("Please choose an account to see balance.");
		System.out.print("Input Here: ");
		int see_bal = Validation.validateNum(scan.next());
		see_bal = Validation.valid_optionForAccounts(see_bal, num_of_accounts);
		
		account = acc.get(see_bal-1);
		
		System.out.println();
		System.out.println();
					
		System.out.println( "You have $" + account.getBalance() + " remaining");
		System.out.println();
		System.out.println();
	}
	public static void withdraw() {
		if(!Validation.haveAccount()) return;
		List<Account> acc = AccountServiceLayer.getAccounts();
		System.out.println("Here is the list of your accounts:");
				
		int num_of_accounts = 1;
		for(Account x: AccountServiceLayer.getAccounts()) {
			System.out.println(num_of_accounts + ". " + x.toString());
			System.out.println();
			num_of_accounts++;
		}
		
		System.out.println("Please choose an account to withdraw from.");
		System.out.print("Input Here: ");
		int see = Validation.validateNum(scan.next());
		see = Validation.valid_optionForAccounts(see, num_of_accounts);
				
		account = acc.get(see-1);
		
		System.out.println();
		System.out.println();					
		System.out.println( "You have $" + account.getBalance() + " remaining");
		System.out.println();
		System.out.println();
		System.out.println("How much would you like to withdraw?");	
		System.out.print("Input here: ");
		int w = Validation.validateNum(scan.next());

		System.out.println();
		System.out.println();



		if(!AccountServiceLayer.withdraw(w,account))
		{
			System.out.println("Error Occured.");
			System.out.println("Please check your balance before withdraw more than you have.");
			System.out.println();
			return;
		}

		System.out.println();
		System.out.println();
		System.out.println("Balance is: $" + account.getBalance());
		System.out.println();
		System.out.println();


	}
	public static void deposit() {
		
		if(!Validation.haveAccount()) return;
		List<Account> acc = AccountServiceLayer.getAccounts();
		System.out.println("Here is the list of your accounts:");
				
		int num_of_accounts = 1;
		for(Account x: AccountServiceLayer.getAccounts()) {
			System.out.println(num_of_accounts + ". " + x.showAccountNumber());
			System.out.println();
			num_of_accounts++;
		}
		
		System.out.println("Please choose an account to deposit to.");
		System.out.print("Input Here: ");
		int see = Validation.validateNum(scan.next());
		see = Validation.valid_optionForAccounts(see, num_of_accounts);

		account = acc.get(see-1);
		
		System.out.println();
		System.out.println();					
		System.out.println( "You have $" + account.getBalance() + " remaining");
		System.out.println();
		System.out.println();
		System.out.println("How much would you like to deposit?");	
		System.out.print("Input here: ");
		int d = Validation.validateNum(scan.next());
		if(!AccountServiceLayer.deposit(d,account)) System.out.println("Problems has occured please try again later");
		System.out.println();
		System.out.println();
		System.out.println("Balance is: $" + account.getBalance());
		System.out.println();
		System.out.println();					
	}
	public static void new_Account() {
		System.out.println("Creating new account...");
		System.out.println("How much money will you like to put in initially?");
		int amount = Validation.validateNum(scan.next());
		System.out.println();
		System.out.println();
		System.out.println("We have added a couple of more option to our application");
		System.out.println("Please read the following menu as our options may have changed:");
		System.out.println("If you want a Checking account PRESS 1");
		System.out.println("If you want a Credit account PRESS 2");
		System.out.println("If you want a Saving account PRESS 3");
		System.out.println("If you want a Loans account PRESS 4");
		System.out.println("What type of account do you want?");
		int typeof = user_type(Validation.validateNum(scan.next()));
		System.out.println();
		System.out.println();

		while(true) {
			if(typeof == 1 | typeof == 2 | typeof ==3 | typeof ==4) break;
			else {
				System.out.println("Please enter in a number that is 1 - 4");
				typeof = user_type(Validation.validateNum(scan.next()));
				System.out.println();
				System.out.println();
			}
		}

		if(!AccountServiceLayer.createAccount(account,amount,typeof)) {
			System.out.println("There was a problem creating your account.");
			System.out.println("Please try again.");
			System.out.println();
			System.out.println();
		}else {
			System.out.println("Account " + account.getAccountNumber() + " has been created");
			System.out.println();
			System.out.println();
		}
	}
	public static void listOfAccounts() {
		if(AccountServiceLayer.getAccounts().isEmpty()) {
			System.out.println("You currently have no accounts.");
			System.out.println("Please create a new account.");
			return;
		}
		System.out.println("Here is the list of your accounts:");
		//print			
		for(Account x: AccountServiceLayer.getAccounts()) {
			System.out.println(x.toString());
			System.out.println();
		}


	}
	public static void transfer() {		
		List<Account> acc = AccountServiceLayer.getAccounts();
		Account creditor = new Account();
		
		if(!Validation.haveOnlyOneAccount(acc)) return;
		int num_of_accounts= 1;
		for(Account x: AccountServiceLayer.getAccounts()) {
			System.out.println(num_of_accounts + ". " + x.toString());
			System.out.println();
			num_of_accounts++;
		}

		System.out.println("Please choose an account to transfer money from:");
		System.out.print("Input Here: ");
		int debit = Validation.validateNum(scan.next());
		System.out.println();
		System.out.println();
		
		debit = Validation.valid_optionForAccounts(debit,num_of_accounts);

		System.out.println("Please choose an account to transfer money to:");
		System.out.print("Input Here: ");
		int credit   = Validation.validateNum(scan.next());
		System.out.println();
		System.out.println();
		
		credit = Validation.valid_optionForAccounts(credit,num_of_accounts);
		
		if(!Validation.valid_transfer(debit, credit, num_of_accounts)) {
			System.out.println();
			System.out.println();
			System.out.println("You can not transfer money to and from the same account.");
			System.out.println("Please try again");
			System.out.println();
			System.out.println();
			return;
		}
		
		

		System.out.println("How much money would you like to transfer?");
		System.out.print("Input Here: ");
		int money   = Validation.validateNum(scan.next());
		System.out.println();
		System.out.println();
		
		account = acc.get(debit -1);
		creditor = acc.get(credit -1);

		if(!AccountServiceLayer.transfer(account,creditor,money)) {
			System.out.println("You can not transfer money you do not have.");
			System.out.println("Please try again");
			System.out.println();
			System.out.println();
		}else {
			System.out.println("Money has been transferred Successfully");
			System.out.println(account.toString());
			System.out.println();
			System.out.println(creditor.toString());
			System.out.println();
			System.out.println();
		}
	}
	public static void delete() {
		int num_of_accounts = 1;

		List<Account> d_account = AccountServiceLayer.getAccounts();
		Account other_Account = new Account();

		for(Account x: d_account) {
			System.out.println(num_of_accounts + ". " + x.toString());
			System.out.println();
			num_of_accounts++;
		}

		System.out.println("Which account would you like to delete?");
		System.out.print("Input Here: ");
		int del   = Validation.validateNum(scan.next());
		del = Validation.valid_optionForAccounts(del, num_of_accounts);
		boolean flag = true;
		String last_check;
		
		account = d_account.get(del -1);
		
		while(flag) {
			System.out.println("Since you have chosen to delele Account #: " + account.getAccountNumber());
			System.out.println("We must verify that you are sure about this.");
			System.out.println("Please make sure your money has been transferred to another account.");
			System.out.println("Once the account has been deleted there is no recovering the account.");
			System.out.println("Are you sure you want to delete your account?");
			System.out.print("Input Here: ");
			last_check = scan.next();
			System.out.println();
			System.out.println();

			if(last_check.equalsIgnoreCase("yes") |  last_check.equalsIgnoreCase("y")){	
				if(account.getBalance() > 0) {
					System.out.println("Sorry you can not delete this account because you still have money in it.");
					System.out.println("Please transfer the money into another account then re-try the delete");
					System.out.println();
					System.out.println();

					System.out.println("Please choose an account to transfer money to:");
					System.out.print("Input Here: ");
					int otherAccount   = Validation.validateNum(scan.next());
					System.out.println();
					System.out.println();

					other_Account = d_account.get(otherAccount-1);
					
					System.out.println("Transfering: " + account.getBalance() + " to " + other_Account.getBalance());

					while(true) {
						if((other_Account.getId()) == (account.getId())){
							System.out.println("Please enter valid account number other than: " + account.getAccountNumber());
							otherAccount   = Validation.validateNum(scan.next());
							System.out.println();
							System.out.println();
						}else break;
					}

					if(!AccountServiceLayer.transfer(account,other_Account,account.getBalance())) {
						System.out.println("You can not transfer money you do not have.");
						System.out.println("Please try again");
						System.out.println();
						System.out.println();
					}else {
						System.out.println("Money has been transferred Successfully");
						System.out.println(account.toString());
						System.out.println(other_Account.toString());
						System.out.println();
						System.out.println();
					}

				}
				if(!ClientServiceLayer.delAccount(account))
				{
					System.out.println("There was a problem deleting your account.");
					System.out.println("Please try again.");
					System.out.println();
					System.out.println();
				}else {
					System.out.println("Account has been removed");
					System.out.println();
					System.out.println();
				}
				flag = false;
			}
			else if(last_check.equalsIgnoreCase("no") | last_check.equalsIgnoreCase("n")) {
				flag = false;
			}
			else {
				System.out.println("Please enter in Y or Yes for confirmation.");
				System.out.println("Please enter in N or No to restart.");
				System.out.println();		
			}
		}
	}
	public static boolean clear() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		customer.setfName("");
		customer.setlName("");
		customer.setPassword("");
		customer.setSsn(0);
		customer.setUsrName("");
		customer.setId(0);
		return false;
	}
	
}

