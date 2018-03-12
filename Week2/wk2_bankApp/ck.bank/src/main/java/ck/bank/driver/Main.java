package ck.bank.driver;

import java.util.List;
import java.util.Scanner;

import ck.bank.dao.AccountDao;
import ck.bank.dao.AccountDaoImpl;
import ck.bank.dao.ClientUI;
import ck.bank.dao.MemberUI;
import ck.bank.dao.UserDao;
import ck.bank.dao.UserDaoImpl;
import ck.bank.pojos.User;
import ck.bank.dao.UserIO;

public class Main {
	public static UserIO io = new UserIO();
	public static Scanner sc = new Scanner(System.in);
	public static UserDao uDao = new UserDaoImpl();
	public static AccountDao aDao = new AccountDaoImpl();
	
	public static void main(String[] args)
	{
		//UserIO io = new UserIO();
		
		//List<User> users = uDao.getAllUsers();// keep this
		List<User> users;
//
//		/*
//		for(User u : users)
//		{
//			u.displayAccountInformation();
//			u.checkBalance();
//		}	
//		
//		System.out.println("====================\n====================");
//		User bb = new User("j","K","L",4.0);
//		users.add(bb);
//		
//		for(User u: users)
//		{
//			u.displayAccountInformation();
//			u.checkBalance();
//		}
//		
//		io.writeAllUsers(users);
//		System.out.println("\n\nwritten to file");
//		*/
//		
		//initialize top level client UI - make a selection to create new account, login with an existing account, or exit
		ClientUI cui = new ClientUI();
		int topSelection;
		User loggedIn;
		boolean userExit = false; //maybe put the topUI in a loop until the user manually exits?
		
		topSelection = cui.topUI();

		switch(topSelection)
		{
		case 1:
			//create new user
			cui.createNewUser();
			System.out.println("User created successfully");
			//start member ui
			break;
		case 2:
			//login for existing user
			//need to find user via username
			//users = uDao.getAllUsers();	//dont think we need this anymore
			loggedIn = cui.userLogin();
			if(loggedIn != null)
			{
				System.out.println("\n\nLogin successful.\nHello, " + loggedIn.getUsername());
				MemberUI mui = new MemberUI(loggedIn);
				mui.topUI();
				//call memberUI for logged in user
			}
			//set as currentUser
			//start memberUI
			break;
		case 3:
			//exit - print message/do nothing
			System.out.println("\n\nExiting program. Thank you.\n\n");
			break;
		default:
			System.out.println("Something went wrong");
		}//switch
//
//		System.out.println("Writing accounts.txt");
//		io.writeAllUsers(users);
		
		
		
		
		

		
		
		users = uDao.getAllUsers();
		
		for(User u : users)
		{
			System.out.println(u.toString());
		}
		
		
		
		
		
		
		
		
		
		
		
		sc.close();
	}//main method
}