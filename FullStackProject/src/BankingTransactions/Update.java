package BankingTransactions;
import java.util.Scanner;


public class Update extends HomeMenu{
	Scanner scan = new Scanner(System.in);
	
	
		public void UpdateUser()
		{
			int choice = 0;
			System.out.println("Enter your choice for updating");
			System.out.println("1.Account Holder Name");
			System.out.println("2.Contact Number");
			System.out.println("3.Pin number");
			System.out.println("4.Exit");
			choice = scan.nextInt();
			
			switch(choice)
			{
			case 1: Name();
			break;
			case 2:Contact();
			break;
			case 3:Pin();
			break;
			case 4:
				break;
				
				default:
					System.out.println("You have entered an incorrect choice");
					break;
			}
		}
	
		void Name()
		{
			System.out.println("Name function");
		}
	
		void Contact()
		{
			System.out.println("Contact function");
		}
		
		void Pin()
		{
			System.out.println("Pin function");
		}
		
	}
