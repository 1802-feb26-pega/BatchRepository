package com.ex.Threads;

public class RunThreads {
 public static void main(String[] args) {
	 System.out.println("Starting main method...");
	 ImplementThread te = new ImplementThread();
	 te.start();
	 
	 while(ImplementThread.myCount <= 10) {
		 try {
				System.out.println("Main thread number: " + (++ImplementThread.myCount));
				Thread.sleep(1000);
			} catch(InterruptedException iex) {
				System.out.println("Exception Thread: " + iex.getMessage());
			}
	 }
	 System.out.println("Finished!");
}
}
