package com.ex.Threads;

class ImplementRunnable implements Runnable {
	
	public static int myCount = 0;
	
	public ImplementRunnable() { }
	
	public void run() {
		
		while(ImplementRunnable.myCount <= 10) {
			try {
				System.out.println("Exp1 Thread: " + (++ImplementRunnable.myCount));
				Thread.sleep(100);
			} catch(InterruptedException iex) {
				System.out.println("Exception is: " + iex.getMessage());
			}
		}
	}
}

public class RunMyThread {
	
	public static void main(String[] args) {
		System.out.println("Starting main thread....");
		
		ImplementRunnable ire = new ImplementRunnable();
		
		Thread t = Thread(ire);
		t.start();
		
		while(ImplementRunnable.myCount <=10) {
			try {System.out.println("Main Thread: " + (++ImplementRunnable.myCount));
				Thread.sleep(100);
			} catch(InterruptedException iex) {
				System.out.println("Exception is: " + iex.getMessage());
			}
		}
		System.out.println("End of main thread");
		}

	private static Thread Thread(ImplementRunnable ire) {
		// TODO Auto-generated method stub
		return null;
	}
	}
}
