package com.revature.FullStack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class User extends Operations
{
	String name, actype, ac, contact, pass;
	float amount;
	String amt;
	Scanner scanner = new Scanner(System.in);
	char acnumber[] = new char[6];
	char pin[] = new char[5];
	
	
	
	public void NewUser () throws IOException
	{
		int c= 0;
		String line;
		String lread;
		char nm[] = new char[6];
		
		System.out.println("\t\tEnter your details\n");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		try {
			
			PrintWriter outs =new PrintWriter(new FileWriter("userdetails.txt", true));
			BufferedWriter bw = new BufferedWriter(new FileWriter("statement.txt", true));
			
			do
			{
				c=0;
				System.out.println("Please enter your full name :");
				name = scanner.nextLine().trim();
				
				if(name.length()<3)
				{
					System.out.println("\n--Name should be greater than 2 charaters");
					c++;
				}
				else if(name.matches(".*?[\\p{Punct}&&[^_]].*") || name.matches("[a-zA-Z]"))
						{
							System.out.println("\n--Enter a valid name");
							c++;
						}
				
			}while(c==1);
			
			
			System.out.println("----------------------------------------------------------------------------------------------------");
			
			do
			{
				
				c= 0;
				System.out.println("Enter your contact number");
				contact = scanner.next();
				
				BufferedReader br = new BufferedReader(new FileReader("userdetails.txt"));
				
				if(contact.matches("(.*)[a-zA-Z]+(.*)") || contact.matches(".*?[\\p{Punct}&&[^_]].*"))
						{
							c++;
							System.out.println("/nEnter a valid contact number");
						
						}
				else if(contact.length()!=10)
				{
				
					c++;
					System.out.println("/nContact number should be of 10 digits");
				
				}
				else
				{
					while((lread=br.readLine()) !=null)
					{
						c=0;
						if(lread.contains(contact + ""))
						{
							c++;
							System.out.println("Contact number already exists!");
							break;
							
						}
					}
				}
					
				}while(c==1);
				

			System.out.println("----------------------------------------------------------------------------------------------------");
			do
			{
				c=0;
				System.out.println("Enter your initial amount:");
				amt=scanner.next();
				
				if(amt.matches("(.*)[a-zZ-a]+(.*)") || contact.matches(".*?[\\p{Punct}&&[^_]].*"))
				{
					c++;
					System.out.println("Enter a valid amount");
					
					
					
				}
				else
				{
					amount=Float.parseFloat(amt);
					if(amount<100)
					{
						System.out.println("Initial amount should be greater than 100");
						c++;
					}
					
					
				}
			
			}while(c==1);
			
			System.out.println("----------------------------------------------------------------------------------------------------");
			do
			{
				System.out.println("Enter your account type(Saving or current) :");
				ac = scanner.next();
				actype=ac.toLowerCase();
				if(!(actype.equals("saving")|| actype.equals("current")));
				{
					System.out.println("Account type should be saving or current");
				}
				
			}while(!(actype.equals("saving")|| actype.equals("current")));
			
			if(actype.equals("saving"))
			{
				actype = "saving";
			}
			
			System.out.println();
			System.out.println("Amount created successfully");
			System.out.println("----------------------------------------------------------------------------------------------------");
			
			Random rnd = new Random();
			
			String username;
			name.getChars(1,3,nm,0);
			username = String.valueOf(nm).trim();
			
			if(username.length()<2)
			{
				username=username+(10000+rnd.nextInt(90000));
			}
			else if(username.length()<3)
			{
				username=username+(1000+rnd.nextInt(9000));
			}
			else
			{
				username=username+(100+rnd.nextInt(900));
			}
			if(username.contains(" "))
				username = username.replace(" ", "a");
		
		
		
			int acnt;
			do
			{
				
				acnt = 10000+ rnd.nextInt(90000);
				c=0;
				BufferedReader br = new BufferedReader(new FileReader("userdetails.txt"));
				
				while((lread=br.readLine()) !=null)
				{
					if(lread.contains(acnt + " "))
					{
						c++;
						break;
					}
				}
				br.close();
				
			}while(c==1);
			
			int pass = 1000 + rnd.nextInt(9000);
			
			System.out.println("Username : "+username);
			System.out.println("Pin number : " +pass);
			System.out.println("Account number : " +acnt);	
			System.out.println("Initial balance : " +amount);
			System.out.println("----------------------------------------------------------------------------------------------------");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
			String date = sdf.format(new Date());
			
			bw.write(username+ " "+acnt+"   "+amount+"    " + date);
			
			bw.newLine();
			bw.close();
				
			outs.println(username+ " "+pass+"  "+actype+"  "+contact+" "+amount);
			outs.close();
				
			BufferedWriter wr = new BufferedWriter(new FileWriter("name.txt", true));
			wr.write(username+" "+pass+"  "+name);	
			wr.newLine();
			wr.close();
		
			System.out.println("Thank you");
		}
		catch (PatternSyntaxException pse)
		{
			System.out.println("Pattern exception caught");
		}
		catch (InputMismatchException ise)
		{
			System.out.println("You have entered incorrect input");
		}
	
		}


	char account[] = new char[6];
	
	public void ExistingUser() throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String name = null, pass = null, amt = null, cont = null, actype = null;
		System.out.println("Enter user name: ");
		String uname = scan.next();
		System.out.println("Enter your pin number");
		String upass = scan.next();
		
		String userpass =uname+" " +upass+" ";
		char cnt = ' ';
		int choice;
		String line, line2, fullname;
		char full[] = new char[100];
		
		try
		{
			
			BufferedReader ins= new BufferedReader(new FileReader("userdetails.txt"));
			
			int count = 0;
			while((line=ins.readLine())!=null)
			{
				
				if(line.contains(userpass))
				{
					System.out.println("Logged in successfully!");
					System.out.println("----------------------------------------------------------------------------------------------------");
					BufferedReader nfm = new BufferedReader(new FileReader("name.txt"));
					
					while ((line2 =nfm.readLine())!=null)
					{
							if(line2.contains(userpass))
							{
								line2.getChars(13, line2.length(), full, 0);
								fullname = String.valueOf(full).trim();
								System.out.println("Welcome" + " " +fullname);
							}
					}
					nfm.close();
					
					
					line.getChars(12, 18, account, 0);
					String sc = String.valueOf(account).trim();
					String userac = uname+" "+ ac;
					
					do
					{
						System.out.println("----------------------------------------------------------------------------------------------------");
						System.out.println("Please enter your choice: ");
						System.out.println("1.Deposit");
						System.out.println("2.Withdraw");
						System.out.println("3.Balance Inquery");
						System.out.println("4.Mini Statement");
						choice = scan.nextInt();
						System.out.println("----------------------------------------------------------------------------------------------------");
						
						switch(choice)
						{
						case 1: ins.close();
						deposit(userpass);
						break;
						
						case 2: ins.close();
						withdraw(userpass);
						break;
						
						case 3: ins.close();
						inquery(userpass);
						break;
						
						case 4: ins.close();
						statement(userac);
						break;
						default:
							System.out.println("You have entered an incorrect input");
							System.out.println("----------------------------------------------------------------------------------------------------");
							break;
						}
						
						System.out.println("Do you want to continue? (Y/N): ");
						cnt= scan.next().charAt(0);
						
					}while(cnt=='Y' || cnt =='y');		
					if(cnt!='Y' || cnt !='y')
					{
						System.out.println("----------------------------------------------------------------------------------------------------");
						System.out.println("Thank you!");
						System.out.println("----------------------------------------------------------------------------------------------------");
						
					}
					count =1;
					break;
				}				
					
			}			
					
						
					
			if(count==0)	
			{
				System.out.println("You have entered an incorrect username/password");
				System.out.println("----------------------------------------------------------------------------------------------------");
				
				
			}
						
				ins.close();
					
		}			
		catch(FileNotFoundException fnfe)
		{
			System.out.println("Found filenotfoundexception");
		}
		catch (InputMismatchException e)
		{
			
			System.out.println("You have entered incorrect input!");
		}
		catch (Exception e) {
			System.out.println("Caught random exception");
			e.printStackTrace();
		}
		
	}
		void DeleteAccount()
		{
			String oldFileName = "userdetails.txt";
			String tmpFileName = "out.txt";
			System.out.println("Enter user name: ");
			String uname = scanner.next();
			System.out.println("Enter your pin number: ");
			String upin = scanner.next();
			
			String combo = uname+" "+upin+" ";
			String data;
			int cnt= 0;
			String ac = null;
			String userac = null;
			BufferedReader ins = null;
			BufferedWriter outs = null;
			
			try
			{
				ins = new BufferedReader(new FileReader(oldFileName));
				outs = new BufferedWriter(new FileWriter(tmpFileName));
				
				while((data=ins.readLine())!=null)
						{
							if(data.contains(combo))
							{
								data.getChars(11, 17, account, 0);
								ac=String.valueOf(account).trim();
								
								cnt++;
								continue;
								
								
							}
							outs.write(data);
							outs.newLine();
					
						}
				
				userac=uname+" "+ac;
				
				if(cnt==0)
				{
					
					System.out.println("You have entered an incorrect username or password");
					System.out.println("----------------------------------------------------------------------------------------------------");
				}
				else
				{
					System.out.println("Account deleted successfully");
					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.println("Thank you");
					System.out.println("----------------------------------------------------------------------------------------------------");
				}
			}
			catch (Exception e) {}
			finally
			{
					try
					{
						if (ins != null)
							ins.close();
					}
					catch (IOException e) {}
			}
			
			
			File oldFile = new File(oldFileName);
			oldFile.delete();
			
			File newFile = new File(tmpFileName);
			newFile.renameTo(oldFile);
		
			}}	
	
	