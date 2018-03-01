package com.revature.threads;

class ThreadExample extends Thread {
	//same as implementing runnable
	public static int myCount = 0;
	
	@Override
	public void run() {
		while (ThreadExample.myCount <= 10) {
			try {
				Thread.sleep(500L);
				System.out.println("ThreadExample thread: " + (++ThreadExample.myCount));
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				System.out.println("Exception in thread: " + e.getMessage());
			}
		}
	}
}

public class RunThread {
	public static void main(String[] args) {
		System.out.println("Starting main method...");
		ThreadExample te = new ThreadExample(); //extended thread.
		te.start(); // .start() actually makes a new thread. .run() only runs the method.
		while (ThreadExample.myCount <= 10) {
			try {
				Thread.sleep(100);
				System.out.println("Main thread: " + (++ThreadExample.myCount));
				Thread.sleep(100L);
			} catch (InterruptedException e) {
				System.out.println("Exception in thread: " + e.getMessage());
			}
		}
		System.out.println("End of Main Thread.");
	}
}
