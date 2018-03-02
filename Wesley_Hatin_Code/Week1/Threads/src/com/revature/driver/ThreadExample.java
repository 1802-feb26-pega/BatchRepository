package com.revature.driver;

public class ThreadExample extends Thread{
	
	public static int myCount = 0;
	
	public void run() {
		while(ThreadExample.myCount<=10) {
			try {
				System.out.println("ThreadExample Thread: " + (++ThreadExample.myCount));
				Thread.sleep(50);
			} catch (InterruptedException iex) {
				System.out.println("Exceptions thread: " + iex.getMessage());
			}
		}
	}

}

class MyThread{
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting the thread...");
		ThreadExample te = new ThreadExample();
		te.start();
		
		while (ThreadExample.myCount <= 10) {
			try {
				System.out.println("Main Thread: " + (++ThreadExample.myCount));
				Thread.sleep(70);
				
			} catch (InterruptedException iex) {
				System.out.println("Exceptions thread: " + iex.getMessage());
			}
		}
		System.out.println("Finishing the thread...");
	}
}
