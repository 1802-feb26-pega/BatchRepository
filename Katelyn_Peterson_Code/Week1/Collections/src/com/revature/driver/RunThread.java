package com.revature.driver;

class ThreadExample extends Thread
{
	public static int myCount = 0;
	
	public void run()
	{
		while(ThreadExample.myCount <= 10)
		{
			try
			{
				System.out.println("ThreadExample thread: " + (++ThreadExample.myCount));
				Thread.sleep(1000);
			}
			catch(InterruptedException iex)
			{
				System.out.println("Exception in thread: " + iex.getMessage());
			}
		}
	}
}

public class RunThread
{
	public static void main(String[] args)
	{
		System.out.println("Starting main thread...");
		
		ThreadExample te = new ThreadExample();
		te.start();
		
		while(ThreadExample.myCount <= 10)
		{
			try
			{
				System.out.println("Main Thread: " + (++ThreadExample.myCount));
				Thread.sleep(1000);
			}
			catch(InterruptedException iex)
			{
				System.out.println("Exception in thread: " + iex.getMessage());
			}
		}
		
		System.out.println("End of the main thread!");
	}
}