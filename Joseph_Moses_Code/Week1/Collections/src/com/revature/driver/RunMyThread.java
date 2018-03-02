package com.revature.driver;

class ImplementRunnableExample implements Runnable {

	public static int myCount = 0;
	
	public ImplementRunnableExample() {}
	
	@Override
	public void run() {
		while(ImplementRunnableExample.myCount <= 10) {
			try {
				System.out.println("Exp1 Thread: " + (++ImplementRunnableExample.myCount));
				Thread.sleep(100);
			}
			catch(InterruptedException iex){
				System.out.println("Exception in thread: " + iex.getMessage());
			}
		}

	}

}

public class RunMyThread{
	
	public static void main(String[] args) {
		System.out.println("Starting main thread...");
		
		ImplementRunnableExample ire = new ImplementRunnableExample();
		
		Thread t = new Thread(ire);
		t.start();
		
		while(ImplementRunnableExample.myCount <= 10) {
			try {
				System.out.println("Main Thread: " + (++ImplementRunnableExample.myCount));
				Thread.sleep(100);
			}
			catch(InterruptedException iex) {
				System.out.println("Exception in main thread: " + iex.getMessage());
			}
		}
		
		System.out.println("End of the main thread.");
	}
	
}
