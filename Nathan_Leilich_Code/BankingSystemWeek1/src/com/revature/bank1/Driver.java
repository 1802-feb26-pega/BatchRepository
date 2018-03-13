package com.revature.bank1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
		
		Bank bank = new Bank();
		while(running) {
			
			try {
				
				welcome(bank);
			}//try
			catch(IOException ioe) {
				
				ioe.printStackTrace();
			}
		}//while
	}//main()
	
	
	/*
	 * prints a welcome message to the end user.  allows user to login,
	 * create an account, or exit the program.  to options are also 
	 * available for testing purposes: exit with serializing and admin tools
	 */
	private static void welcome(Bank bank) throws IOException {
		
		boolean invalidInput = true;
		System.out.println("\n" +"Welcome to the banking app" + "\n\n" +
		
							"1:  login" + "\n" + 
							"2:  new user" + "\n" + 
							"3:  exit" + "\n\n" +
							"10:  exit without serializing" + "\n" +
							"20:  admin tools");
		String input = "";
		while(invalidInput) {
			
			input = stdin.readLine();
			switch(input) {
			
			case "1":
				login(bank);
				invalidInput = false;
				break;
				
			case "2":
				newUser(bank);
				invalidInput = false;
				break;
				
			case "3":
				exit(bank);
				invalidInput = false;
				break;
				
			case "10":
				System.out.println("\n" + "---------terminating----------");
				invalidInput = false;
				running = false;
				break;
				
			case "20":
				adminTools(bank);
				invalidInput = false;
				break;
			default:
				System.out.println("\n" + "Invalid menu option.  Here are the options again:" + "\n\n" +
			
									"1:  login" + "\n" + 
									"2:  new user" + "\n" + 
									"3:  exit" + "\n\n" +
									"10:  exit without serializing" + "\n" +
									"20:  admin tools");
				break;
			}//switch	
		}//while
	}//welcome()
	
	
	/*
	 * end user can create a new account
	 */
	private static void newUser(Bank bank) throws IOException {
		
		boolean invalidInput = true;
		String userName = "";
		String password = "";
		float balance = 0.0f;
		while(invalidInput) {   //program loops until a valid username is entered
			
			System.out.println("enter a user name");
			userName = stdin.readLine();
			if(bank.userNameInUse(userName)) {  //entered username was not unique
				
				System.out.println("that name is already in use.  Try another");
				continue;
			}//if
			
			System.out.println("enter a password");
			password = stdin.readLine();
			System.out.println("enter a staring balance");
			balance = readFloat();
			bank.login(userName, password);
			invalidInput = false;
		}//while
		
		bank.newUser(userName, password, balance);
		boolean loginSuccessful = bank.login(userName, password);
		
		if(loginSuccessful) { 
			
			System.out.println("\n" + "account creation sucsessful.  loging in..." + "\n");
			accountManagement(bank);
		}//if
		else {  //if something unexpected happens, newly made user is removed for good measure
			
			System.out.println("login failed");
			bank.removeUser(userName);
		}//else
	}//newUser()
	
	
	/*
	 * allows user to login
	 */
	private static void login(Bank bank) throws IOException {
		
		boolean invalidInput = true;
		String userName = "";
		String password = "";
		int count = 0;
		while(invalidInput) {   //program loops until valid username and password are entered
							  	
			System.out.println("enter a user name");
			userName = stdin.readLine();
			System.out.println("enter a password");
			password = stdin.readLine();
			
			if(bank.login(userName, password)) { 	//login successful
				
				invalidInput = false;
				System.out.println("logging in");
			}//if
			else {		//login unsuccessful 
				
				System.out.println("invalid username or password");
				count++;
				if(count > 2) {		//if user fails to login on third attempt, 
									//they are prompted to return to the main menu
					System.out.println("you have failed " + count + " login attempts" + "\n" +
										"exit to main menu?  (y/n)");
					boolean secondInputInvalid = true;
					String input = "";
					while(secondInputInvalid) {
						
						input = stdin.readLine();
						switch(input) {
						
						case "y":
						case "Y":
							System.out.println("exiting to main menue...");
							return;
						case "n":
						case "N":
							secondInputInvalid = false;
							break;
							
						default:
							System.out.println("invalid input.  type (y/n)");
							break;
						
						}//switch
					}//while
				}//if
			}//else
		}//while)
		accountManagement(bank);
	}//login()
	
	
	/*
	 * allows user to get balance, withdraw and deposte, or change passwords
	 */
	private static void accountManagement(Bank bank) throws IOException {
		
		boolean managing = true;
		while(managing) {
			
			boolean invalidInput = true;
			System.out.println("\n" + "choose from one of theese options:" + "\n\n" +
					
								"1:  balance" + "\n" + 
								"2:  withdraw" + "\n" + 
								"3:  deposite" + "\n" +
								"4:  change password" + "\n" +
								"5:  logout");
			String input = "";
			while(invalidInput) {
				
				input = stdin.readLine();
				switch(input) {
				
				case "1":
					System.out.println("your balance is:  $" + bank.getBalence());
					invalidInput = false;
					break;
					
				case "2":
					System.out.println("enter amount to withdraw");
					float withdraw = readFloat();
					withdraw = bank.withdraw(withdraw);
					if(withdraw == -1.0f) {
						
						System.out.println("insoficant funds");
					}//if
					else {
						
						System.out.println("despensing money:  $" + withdraw + "\n\n" +
											"new balance:  $" + bank.getBalence());
					}//else
					invalidInput = false;
					break;
					
				case "3":
					System.out.println("enter amount to deposite");
					float deposite = readFloat();
					bank.deposit(deposite);
					System.out.println("deposite succesfull" + "\n\n" +
										"new balance:  $" + bank.getBalence());
					invalidInput = false;
					break;
					
				case "4":
					System.out.println("enter new password");
					String newPassword = stdin.readLine();
					bank.changePassword(newPassword);
					System.out.println("password change sucsessful");
					invalidInput = false;
					break;
					
				case "5":
					System.out.println("loging out");
					invalidInput = false;
					managing = false;
					bank.logout();
					break;
					
				default:
					System.out.println("\n" + "Invalid menu option.  Here are the options again:" + "\n\n" +
				
										"1:  balance" + "\n" + 
										"2:  withdraw" + "\n" + 
										"3:  deposite" + "\n" +
										"4:  change password" + "\n" +
										"5:  logout");
					break;
				}//switch	
			}//while
		}//while
		
	}//accountManagement()
	
	
	
	/*
	 * testing tools for the benifite of testing.  these options 
	 * would not be avalible to end users in a commerial app.
	 * 
	 * Options inclue:  printing all user information, removing 
	 * users by user name, and removeing all users.
	 * 
	 * Note that in the main menu, there is an option to exit without
	 * serializing
	 */
	private static void adminTools(Bank bank) throws IOException {
		
		boolean administrating = true;
		while(administrating) {
			
			boolean invalidInput = true;
			System.out.println("\n" +"there are testing tools that would" + "\n" +
								"be unavalible to the user" + "\n\n" +
			
								"1:  data dump" + "\n" + 
								"2:  remove user" + "\n" + 
								"3:  clear all users" + "\n" +
								"4:  return to main menu");
		
			String input = "";
			while(invalidInput) {
				
				input = stdin.readLine();
				switch(input) {
				
				case "1":
					bank.dataDump();
					invalidInput = false;
					break;
					
				case "2":
					System.out.println("enter username");
					String userName = stdin.readLine();
					if(bank.userNameInUse(userName)) {
						
						bank.removeUser(userName);
						System.out.println("user [" + userName + "] removed");
					}//if
					else {
						
						System.out.println("there is no user by that name" + "\n\n");
					}//else 
					invalidInput = false;
					break;
					
				case "3":
					System.out.println("data cleared");
					bank.clearData();
					invalidInput = false;
					break;
					
				case "4":
					invalidInput = false;
					administrating = false;
					break;
				default:
					System.out.println("\n" + "Invalid menu option.  Here are the options again:" + "\n\n" +
				
										"1:  data dump" + "\n" + 
										"2:  remove user" + "\n" + 
										"3:  clear all users" + "\n" +
										"4:  return to main menu");
					break;
				}//switch	
			}//while
		}//while
	}//adminTools()
	
	
	/*
	 * saves data to file and exits the program
	 */
	private static void exit(Bank bank) throws IOException {
		
		bank.exit();
		System.out.println("\n" + "---------terminating----------");
		running = false;
	}
	
	/*
	 * Continually reads the next line from the console until
	 * it can be formated as an integer and returned
	 */
	private static int readInt() {
		
		boolean invalidInput = true;
		int returnInt = 0;
		while(invalidInput) {
			
			try {
				
				returnInt = Integer.parseInt(stdin.readLine());
				invalidInput = false;
				
			}//try
			catch (NumberFormatException nfe) {
				
				System.out.println("Invald input.  Must enter an Integer");
			}//catch
			catch (IOException e) {
				
				e.printStackTrace();
			}
		}//while
		return returnInt;
	}//readInt()
	
	
	/*
	 * Continually reads the next line from the console until
	 * it can be formated as a float and returned
	 */
	private static float readFloat() {
		
		boolean invalidInput = true;
		float returnFloat = 0;
		while(invalidInput) {
			
			try {
				
				returnFloat = Float.parseFloat(stdin.readLine());
				invalidInput = false;
				
			}//try
			catch (NumberFormatException nfe) {
				
				System.out.println("Invald input.  Must enter a decimal number");
			}//catch
			catch (IOException e) {
				
				e.printStackTrace();
			}
		}//while
		return returnFloat;
	}//readFloat()
}//Driver