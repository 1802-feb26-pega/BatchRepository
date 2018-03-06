package ck.bankApp;

import java.util.List;

public class Main {

	public static void main(String[] args)
	{
		UserIO io = new UserIO();
		List<User> users = io.readAllUsers();

		for(User u : users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}	
		
		System.out.println("====================\n====================");
		User bb = new User("j","K","L",4.0);
		users.add(bb);
		
		for(User u: users)
		{
			u.displayAccountInformation();
			u.checkBalance();
		}
		
		io.writeAllUsers(users);
		System.out.println("\n\nwritten to file");
	}
}
