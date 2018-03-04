package com.revature.driver;

class  ImplementsRunnableExample implements Runnable {

	public static int myCount = 0;
	
	public ImplementsRunnableExample() {}
	
	
	public void run() {
		while(ImplementsRunnableExample.myCount <= 10) {
			try {
				System.out.println("Example Thread: " + (++ImplementsRunnableExample.myCount));
				Thread.sleep(1000);
			} catch(InterruptedException iex) {
				System.out.println("Exception in thread: " + iex.getMessage());
			}
		}
	}
}

public class RunMyThread {
	
	public static void main(String[] args) {
		System.out.println("Starting main thread...");
		
		ImplementsRunnableExample ire = new ImplementsRunnableExample();
		
		Thread t = new Thread (ire);
		t.start();
		
		while(ImplementsRunnableExample.myCount <=10) {
			try {
				System.out.println("Main thread: "+ (++ImplementsRunnableExample.myCount));
				Thread.sleep(1000);
			} catch(InterruptedException iex) {
				System.out.println("Exception in main method: "+iex.getMessage());
			}
		}
		
		System.out.println("End of main thread!");
	}
	
}