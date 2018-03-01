package com.revature.threads;

class ImplementingRunnableExample implements Runnable {
	//Same as extending thread
	public static int myCount = 0;
	
	public ImplementingRunnableExample() {

	}
	
	public ImplementingRunnableExample(int newCount) {
		myCount = newCount;
	}
	
	@Override
	public void run() {
		while(ImplementingRunnableExample.myCount <= 10) {
			try {
				System.out.println("Example thread: " + (++myCount));
				Thread.sleep(1000L);
			} catch (InterruptedException iex) {
				System.out.println("Exception in thread: " + iex.getMessage());
			}
		}
		System.out.println("End of example thread.");
	}

}

public class RunMyThread {
	public static void main(String[] args) {
		System.out.println("Starting main thread...");
		
		ImplementingRunnableExample ire = new ImplementingRunnableExample();
		Thread t = new Thread(ire);
		t.start();
		while (ImplementingRunnableExample.myCount <= 10) {
			try {
				System.out.println("Main thread: " + (++ImplementingRunnableExample.myCount));
				Thread.sleep(1000L);
			} catch (InterruptedException iex) {
				System.out.println("Exception in mainthread: " + iex.getMessage());
			}
		}
		System.out.println("End of Main Thread.");
	}
}