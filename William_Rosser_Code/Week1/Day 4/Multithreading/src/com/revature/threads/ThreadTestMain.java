package com.revature.threads;

public class ThreadTestMain {

	public static void main(String[] args) {
		Thread myThread = new Thread() {
			public void run() {
				System.out.println("Thread is running.");
			}
		};
		Thread myOtherThread = new Thread(new MyOtherThread());
	
		myThread.start();
		myOtherThread.start();
		Runnable thread3 = new MyOtherThread();
		thread3.run();
	}
}
