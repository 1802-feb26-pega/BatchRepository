package BankingTransactions;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class Operations {

	float damt = 0.0f, wamt = 0.0f, bal = 0.0f;
	
	String data;
	char fbal[] = new char [20];
	char user[] = new char[20];
	char account[] = new char[20];
	int l =0;
	Scanner scan = new Scanner(System.in);
	
	public void deposit(String nmpass) throws IOException
	{
		String oldFileName = "userdetails.txt";
		String tmpFileName = "out.txt";
		BufferedReader ins = null;
		BufferedWriter outs = null;
		
		try
		{
			
			ins = new BufferedReader(new FileReader(oldFileName));
			outs = new BufferedWriter(new FileWriter(tmpFileName));
			BufferedWriter bw = new BufferedWriter(new FileWriter("statement.txt", true));
			
			while((data =ins.readLine())!=null)
			{
					if(data.contains(nmpass))
					{
						l = data.length();
						data.getChars(40, l-1, fbal, 0);
						String b = String.valueOf(fbal).trim();
						
						bal=Float.parseFloat(b);
						
						data.getChars(0, 6, user, 0);
						String u = String.valueOf(user).trim();
						data.getChars(12, 18, account, 0);
						String ac = String.valueOf(account).trim();
						
						try
						{
							System.out.println("Please enter your amount: ");
							String dm = scan.next();
							damt=Float.parseFloat(dm);
							
							if (damt<20.0f)
							{
								System.out.println("Amount should be greater than or equal to $20");
								System.out.println("----------------------------------------------------------------------------------------------------");
							}
							else
							{
								bal = bal +damt;
								System.out.println("Transaction completed successfully!");
								System.out.println("Total Balance: " + bal);
								System.out.println("----------------------------------------------------------------------------------------------------");
								
								String c = String.valueOf(bal);
								data = data.replace(b, c);
								SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
								String date = sdf.format(new Date());
								
								bw.write(u+"  " + ac+ "  "+damt+ "    deposit"+ "   "+bal+ "   "+date);
								bw.newLine();
								bw.close();
								
							}
						}
						catch (NumberFormatException nfe)
						{
							System.out.println("\nYou have entered an incorrect input");
							System.out.println("----------------------------------------------------------------------------------------------------");
						}
								
					}
					outs.write(data);
					outs.newLine();
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("\nYou have entered an incorrect input");
			System.out.println("----------------------------------------------------------------------------------------------------");
		}
		catch(IOException ioe)
		{
			return;
		}
		finally
		{
				try {
							if(ins != null)
							ins.close();
					}
					catch(Exception e)
				{
				
				}
		}
		
		File oldFile = new File(oldFileName);
		oldFile.delete();
		File newFile = new File(tmpFileName);
		newFile.renameTo(oldFile);
	}
		
			
	public void withdraw(String nmpass)
	{
		String oldFileName = "userdetails.txt";
		String tmpFileName = "out.txt";
		BufferedReader ins = null;
		BufferedWriter outs = null;
		
		try {
		
			ins = new BufferedReader(new FileReader(oldFileName));
			outs = new BufferedWriter(new FileWriter(tmpFileName));
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("statement.txt", true));
			
			while((data = ins.readLine())!= null)
				if(data.contains(nmpass))
				{
					l = data.length();
					data.getChars(40, l-1, fbal, 0);
					String b = String.valueOf(fbal).trim();
					
					bal = Float.parseFloat(b);
					
					data.getChars(6,0, user, 0);
					String u = String.valueOf(user).trim();
					data.getChars(12, 18, account, 0);
					String ac = String.valueOf(account).trim();
					
					try
					{
						System.out.println("Please enter your withdraw amount");
						String wt = scan.next();
						wamt = Float.parseFloat(wt);
						float less = bal - wamt;
						
						if(wamt <20.0f)
						{
							System.out.println("Withdrawl amount should be at least $20");
							System.out.println("----------------------------------------------------------------------------------------------------");
						}
						else if(less<100.0f)
						{
						System.out.println("Insufficient balance");
						System.out.println("----------------------------------------------------------------------------------------------------");
					
						}
						else
						{
							bal=bal-wamt;
							System.out.println("Transaction completed successfully");
							System.out.println("Total Balance: "+bal);
							System.out.println("----------------------------------------------------------------------------------------------------");
							String c = String.valueOf(bal);
							
							data=data.replace(b, c);
							SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
							String date = sdf.format(new Date());
						
							bw.write(u+"  "+ac+"   "+wamt+ "    Withdraw"+"    "+bal+"    "+date);
						
							bw.newLine();
							bw.close();
						
						}	
					
					
					}
					catch (NumberFormatException nfe)
					{
						System.out.println("\nYou have entered an incorrect choice");
						System.out.println("----------------------------------------------------------------------------------------------------");
					}
					
				
				
				}
		
					outs.write(data);
					outs.newLine();
		
	}
		catch(InputMismatchException ime)
		{
			System.out.println("\nYou have entered incorrect input");
			System.out.println("----------------------------------------------------------------------------------------------------");
		
		}
		catch (Exception e)
		{
			
		}
		finally
		{
				try 
				{
				
					if(ins != null)
					ins.close();
				}
				catch(IOException e) {}
		
		
				try 
				{
					if (outs != null)
						outs.close();
					
					
				}
				catch(IOException e) {}
				
				
				
				
		}
		
			File oldFile = new File(oldFileName);
			oldFile.delete();
			
			File newFile = new File(tmpFileName);
			newFile.renameTo(oldFile);
	}
		
		
		public void inquery(String nmpass) throws IOException
		{
			
			try 
			{
			
				BufferedReader ins = new BufferedReader(new FileReader("userdetails.txt"));
				while((data=ins.readLine()) != null)
				
				{
					if (data.contains(nmpass))
				
					{
						l = data.length();
						data.getChars(40,l-1, fbal, 0);
						String b = String.valueOf(fbal).trim();
						bal = Float.parseFloat(b);
						System.out.println("Got here");
						System.out.println("\nTotal balance: "+bal);
						System.out.println("----------------------------------------------------------------------------------------------------");
						break;
					}
				}
				ins.close();
			}
			
			catch(NumberFormatException nfe)
			{
			System.out.println("caught number format exception");
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("Caught fnfe exception");
			}
			catch(InputMismatchException ime)
			{
				System.out.println("Caught mismatch exception");
			}
			catch(Exception e) {}
				
		}
		
		
		
		public void statement(String nmact) throws IOException
		{
			
		
				try 
				{
					BufferedReader ins = new BufferedReader(new FileReader("statement.txt"));
					System.out.println("User |"+"A/c No.  |"+" Transaction |"+" Total Bal. "+"|  Data & Time");
					System.out.println("----------------------------------------------------------------------------------------------------");
					System.out.println();
					while((data=ins.readLine())!=null)
					
					{
						if (data.contains(nmact))
						{
							System.out.println(data);
						}
					}
					System.out.println("----------------------------------------------------------------------------------------------------");
					ins.close();
					
			}	catch(NumberFormatException nfe)
				{
				System.out.println("caught number format exception");
				}
				catch(IOException ioe)
				{
				System.out.println("Caught io exception");
				}
				catch(Exception e) {
					System.out.println("Caught random exception");
				}
				}
				
		
				}
		
				
				

		
		
		
			
			
			
		
		

	
				
				
				
				
				
				
			
			
			
			
			
			
			
			
			
			
			
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

