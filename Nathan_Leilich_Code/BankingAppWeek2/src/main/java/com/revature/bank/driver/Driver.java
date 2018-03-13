package com.revature.bank.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.revature.bank.dao.AccountDAO;
import com.revature.bank.dao.AccountDAOImpl;
import com.revature.bank.dao.UserDAO;
import com.revature.bank.dao.UserDAOImpl;
import com.revature.bank.pojo.Account;
import com.revature.bank.pojo.User;


/*
 * a user Interface for the Bank class
 */
public class Driver {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	private static boolean running = true;  //while true, the program will continually loop
	
	
	/*
	 * Initializes a bank and calls the main menu
	 */
	public static void main(String[] args) {
		
		while(running) {
			
			try {
				
				welcome();
			}//try
			catch(IOException ioe) {
				
				ioe.printStackTrace();
			}
		}//while
	}//main()
	
	
	/*
	 * prints a welcome message to the end user.  allows user to login,
	 * create a user, or exit the program.
	 */
	private static void welcome() throws IOException {
		
		boolean invalidInput = true;
		System.out.println("\n" +"Welcome to banking app2.0" + "\n\n" +
		
							"1:  login" + "\n" + 
							"2:  new user" + "\n" + 
							"3:  exit" + "\n" + 
							"____________________________________");
		String input = "";
		while(invalidInput) {
			
			input = readNonEmptyString();
			switch(input) {
			
			case "1":
				login();
				invalidInput = false;
				break;
				
			case "2":
				newUser();
				invalidInput = false;
				break;
				
			case "3":
				exit();
				invalidInput = false;
				break;

			default:
				System.out.println("\n" + "****Invalid menu option.  Here are the options again:" + "\n\n" +
			
									"1:  login" + "\n" + 
									"2:  new user" + "\n" + 
									"3:  exit" + "\n" + 
									"____________________________________");
				break;
			}//switch	
		}//while
	}//welcome()
	
	
	/*
	 * end user can create a new user
	 */
	private static void newUser() throws IOException {
		
		UserDAO udao = new UserDAOImpl();
		HashSet<String> userNames = null;
		try {								//grab usernames from database
			
			userNames = new HashSet<String>(udao.getAllUserNames());  
		}//try
		catch(SQLException e) {
			
			//do nothing
		}//catch
		
		
		boolean invalidInput = true;
		String userName = "";
		String password = "";
		while(invalidInput) {   //program loops until a valid username is entered
			
			System.out.println("enter a user name");
			userName = readNonEmptyString();
			try {
				
				if(userNames.contains(userName)) {  
					
					System.out.println("****that name is already in use.  Try another****");
				}//if
				else {
					
					System.out.println("enter a password");
					password = readNonEmptyString();
					invalidInput = false;
				}//else
			}//try
			catch(NullPointerException e) {
				
				System.out.println("enter a password");
				password = readNonEmptyString();
				invalidInput = false;
				
			}//catch
		}//while
		
		User newUser = new User(userName, password);
		newUser = udao.addUser(newUser);
		System.out.println("\naccont created\nlogging in...\n");
		manageAccounts(newUser);           //user is automatically logged after creation
		
	}//newUser()
	
	
	/*
	 * allows user to login
	 */
	private static void login() throws IOException {
		
		UserDAO udao = new UserDAOImpl();
		try{
			
			udao.getAllUserNames();		//grab all usernames from database
		}//try
		catch(SQLException e) {
			
			System.out.println("****there are no user****");    //return if there are no users
			return;
		}//catch
		boolean invalidUser = true;
		String userName = "";
		String password = "";
		int count = 0;
		User user = null;
		
		while(invalidUser) {   //program loops until valid username and password are entered

			System.out.println("enter a user name");
			userName = readNonEmptyString();
			System.out.println("enter a password");
			password = readNonEmptyString();
			
			try {
				
				user = udao.getUser(userName, password);
				invalidUser = false;		
			}//try
			
			catch(SQLException e) {
				
				System.out.println("****invalid username or password****");
				count++;
				if(count > 2) {		//if user fails to login on third attempt, 
									//they are prompted to return to the main menu
					System.out.println("****you have failed " + count + " login attempts" + "\n" +
										"    exit to main menu?  (y/n)" + "\n" + 
										"____________________________________");
					boolean invalidInput = true;
					String input = "";
					
					while(invalidInput) {
						
						
						input = readNonEmptyString();
						switch(input) {
						
						case "y":
						case "Y":
							System.out.println("exiting to main menue...");
							return;
						case "n":
						case "N":
							invalidInput = false;
							break;
							
						default:
							System.out.println("****invalid input.  type (y/n)****");
							break;
						
						}//switch
					}//while
				}//if
			}//catch
		}//while)
		System.out.println("\nlogging in...\n");
		manageAccounts(user);
	}//login()
	
	
	/*
	 * users can make a transaction (select account and go to transaction screen),
	 * create an account, change password, and logout
	 */
	private static void manageAccounts(User user) throws IOException{
		
		boolean managing = true;
		while(managing) {
			
			boolean invalidInput = true;
			System.out.println("\n" + "choose from one of theese options:" + "\n\n" +
					
								"1:  make a transaction" + "\n" + 
								"2:  creat an account" + "\n" + 
								"3:  change password" + "\n" +
								"4:  logout" + "\n" + 
								"____________________________________");
			String input = "";
			while(invalidInput) {
				
				
				input = readNonEmptyString();
				switch(input) {
				
				case "1":
					accountView(user);
					invalidInput = false;
					break;
					
				case "2":
					createAccount(user);
					invalidInput = false;
					break;
					
				case "3":
					System.out.println("enter new password");
					String newPassword = readNonEmptyString();
					user.setPassword(newPassword);
					UserDAO udao = new UserDAOImpl();
					try {
						udao.updateUser(user);
						System.out.println("password changed");
					} //try
					catch (SQLException e) {
						
						e.toString();
					}//catch
					invalidInput = false;
					break;
					
				case "4":
					System.out.println("logging out...");
					managing = false;
					invalidInput = false;
					break;
					
				default:
					System.out.println("\n" + "****Invalid menu option.  Here are the options again:" + "\n\n" +
				
										"1:  make a transaction" + "\n" + 
										"2:  creat an account" + "\n" + 
										"3:  change password" + "\n" +
										"4:  logout" + "\n" + 
										"____________________________________");
					break;
				}//switch
			}//while
		}//while
				
		
	}//manageAccounts()
	
	
	/*
	 * user creates a new account for the input user
	 */
	private static void createAccount(User user) throws IOException {
		
		AccountDAO adao = new AccountDAOImpl();
		ArrayList<Account> accounts = null;
		try {						//get all accounts of the input user
			
			accounts = new ArrayList<Account>(adao.getAccountsByUserID(user.getUserID()));
		}//try
		catch(SQLException e) {
			
			//do nothing
		}//catch
		
		boolean invalidInput = true;
		String name = "";
		double balance = 0;
		while(invalidInput) {   //program loops until a valid account name is entered
			
			System.out.println("enter an account name");
			name = readNonEmptyString();
			boolean nameInUses = false;
			try {
				
				for(int i = 0; i < accounts.size(); i++)
					
					if(accounts.get(i).getAccountName().equals(name)) {  
						
						nameInUses = true;
						
					}//if
				//for
				if(nameInUses) {
					
					System.out.println("****that name is already in use.  Try another****");
				}//if
				else {
					
					invalidInput = false;
				}//else
			}//try
			catch(NullPointerException e) {
			
				invalidInput = false;
			}//catch
		}//while
		System.out.println("enter starting ballance");
		balance = readDouble();
		invalidInput = false;
		Account newAccount = new Account(name, user.getUserID(), balance);
		adao.addAccount(newAccount);
		System.out.println("new account created...");
	}//createAccount()
	
	
	/*
	 * user views and picks an account
	 */
	private static void accountView(User user) throws IOException{
	    
		AccountDAO adao = new AccountDAOImpl();
		ArrayList<Account> accounts = null;
		try {
			
			accounts = new ArrayList<Account>(adao.getAccountsByUserID(user.getUserID()));
		}//try
		catch(SQLException e) {
			
			System.out.println("****user has no accounts****");
			return;		
		}//catch
		int index = accountViewHelper(accounts);
		if(index >= 0) {
			
		    manageTransactions(accounts.get(index));
		}//if
	}//accountView()
	
	
	/*
	 * helper method that takes a list of Accounts as a paramiter and promts the user
	 * to select one via the console.  The index of the selected Account is returned.
	 */
	private static int accountViewHelper(List<Account> accounts) throws IOException{
		
	    System.out.println("Select and account");
	    
	    for(int i = 0; i < accounts.size(); i++) {
	    	
	    	System.out.println((i+1) + " :  " + accounts.get(i).getAccountName());
	    }//for
	    
	    System.out.println("cancel : back to menu");
	    boolean invalidInput = true;
	    int index = -1;
	    
	    while(invalidInput) {   //program loops until valid input is taken
	    	
	    	
	    	String input = readNonEmptyString();
	    	if(input.equals("cancel")) {
	    		
	    		System.out.println("canceling...");   //canels selection
	    		invalidInput = false;
	    	}//if
	    	else {
	    		
	    		try {
	    			
	    			index = Integer.parseInt(input);
	    			if(index > 0 && index <= accounts.size()) {   //valid numeral input
		    			
	    				index--;
		    			invalidInput = false;
		    		}//if
		    		else {    //invalid numeral input
		    			
		    			System.out.println("****invalid input****");
		    		}//else
	    		}//try
	    		catch(NumberFormatException nfe) {
	    			
	    			System.out.println("****invalid input****");
	    		}//catch	
	    	}
	    }//while
	    
	    return index;
	}//accountViewHelper
	
	
	/*
	 * allows user to get balance, withdraw and deposit, and transfer money
	 */
	private static void manageTransactions(Account account) throws IOException {
		
		AccountDAO adao = new AccountDAOImpl();
		boolean managing = true;
		while(managing) {
			
			boolean invalidInput = true;
			System.out.println("\n" + "choose from one of theese options:" + "\n\n" +
					
								"1:  balance" + "\n" + 
								"2:  withdraw" + "\n" + 
								"3:  deposite" + "\n" +
								"4:  transfer money" + "\n" +
								"5:  back to user menu" + "\n" + 
								"____________________________________");
			String input = "";
			while(invalidInput) {
				
				
				input = readNonEmptyString();
				switch(input) {
				
				case "1":
					System.out.println("your balance is:  $" + account.getBalance());
					invalidInput = false;
					break;
					
				case "2":
					System.out.println("enter amount to withdraw");
					double withdraw = readDouble();
					
					if(account.getBalance() >= withdraw) {
						
						account.setBalance(account.getBalance() - withdraw);
						try {
							
							adao.updateAccount(account);
						}//try
						catch (SQLException e) {
							
							e.toString();
						}//catch
						System.out.println("despensing money:  $" + withdraw + "\n\n" +
								"new balance:  $" + account.getBalance());
					}//if
					else {
						
						System.out.println("****insoficant funds****");
					}//else
					invalidInput = false;
					break;
					
				case "3":
					System.out.println("enter amount to deposit");
					double deposit = readDouble();
					account.setBalance(account.getBalance() + deposit);
					try {
						
						adao.updateAccount(account);
					}//try 
					catch (SQLException e1) {
						
						e1.toString();
					}//catch
					System.out.println("deposit succesfull" + "\n\n" +
										"new balance:  $" + account.getBalance());
					invalidInput = false;
					break;
					
				case "4":
					System.out.println("enter amount to transfer");
					double transfer= readDouble();
					
					if(account.getBalance() >= transfer) {
						
						ArrayList<Account> accounts = null;
						try {									//get a list of accounts belonging to the user
							
							accounts = new ArrayList<Account>(adao.getAccountsByUserID(account.getUserID()));
						}//try
						catch(SQLException e) {
							
							System.out.println("somethng is wrong.  This menu option should not" + "\n" +
												"have been avalible to a user with no accounts.");
							e.toString();
						}//catch
						
					    int index = accountViewHelper(accounts);   //select a recipient account
					    if(index < 0) {  // invalid result from accountViewHelper()
					    	
					    	System.out.println( "1:  balance" + "\n" + 
												"2:  withdraw" + "\n" + 
												"3:  deposite" + "\n" +
												"4:  transfer money" + "\n" +
												"5:  back to user menu" + "\n" + 
												"____________________________________");
					    	break;
					    }
					    Account recipient = accounts.get(index);  
					    
					    if(account.getAccountName().equals(recipient.getAccountName())) {  //two were the same
					    	
					    	System.out.println("cannot transfer money to the same account" + "\n" +
								    			"1:  balance" + "\n" + 
												"2:  withdraw" + "\n" + 
												"3:  deposite" + "\n" +
												"4:  transfer money" + "\n" +
												"5:  back to user menu" + "\n" + 
												"____________________________________");
					    	break;
					    }//if
						
						account.setBalance(account.getBalance() - transfer);
						try {
							
							adao.updateAccount(account);
							recipient.setBalance(recipient.getBalance() + transfer);
							adao.updateAccount(recipient);
						}//try
						catch (SQLException e) {
							
							e.toString();
						}//catch
						
						
						System.out.println("transfering money:  $" + transfer + "...\n\n" +
								"new balances:" + "\n" + 
								"    [" + account.getAccountName() + " : " +account.getBalance() + "]\n" +
								"    [" + recipient.getAccountName() + " : " +recipient.getBalance() + "]");
					}//if
					else {
						
						System.out.println("****insoficant funds****");
					}//else
					invalidInput = false;
					break;
					
				case "5":
					System.out.println("returning...");
					invalidInput = false;
					managing = false;
					break;
					
				default:
					System.out.println("\n" + "****Invalid menu option.  Here are the options again:" + "\n\n" +
				
										"1:  balance" + "\n" + 
										"2:  withdraw" + "\n" + 
										"3:  deposite" + "\n" +
										"4:  transfer money" + "\n" +
										"5:  back to user menu" + "\n" + 
									"____________________________________");
					break;
				}//switch	
			}//while
		}//while
		
	}//accountManagement()
	
	
	/*
	 * saves data to file and exits the program
	 */
	private static void exit() throws IOException {
		
		System.out.println("\n" + "---------terminating----------");
		running = false;
	}
	
	/*
	 * Continually reads the next line from the console until
	 * it can be formated as an integer and returned
	 */
	private static String readNonEmptyString() throws IOException{
		
		boolean invalidInput = true;
		String returnString = "";
		while(invalidInput) {
			
			System.out.print(">> ");
			returnString = stdin.readLine();
			if(returnString.equals("")){
				
				System.out.println("****Invald input.  cannot be empty string****");
			}//if
			else {
				
				invalidInput = false;
			}//else
		}//while
		return returnString;
	}//readInt()
	
	
	/*
	 * Continually reads the next line from the console until
	 * it can be formated as a float and returned
	 */
	private static double readDouble() {
		
		boolean invalidInput = true;
		double returnDouble = 0;
		while(invalidInput) {
			
			try {
				
				System.out.print(">> ");
				returnDouble = Double.parseDouble(stdin.readLine());
				invalidInput = false;
				
			}//try
			catch (NumberFormatException nfe) {
				
				System.out.println("****Invald input.  Must enter a decimal number");
			}//catch
			catch (IOException e) {
				
				e.printStackTrace();
			}
		}//while
		return returnDouble;
	}//readFloat()
}//Driver

