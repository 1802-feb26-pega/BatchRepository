package com.revature.driver;

public class ImplementsRunnableExample implements Runnable {

	public static int myCount = 0;
	
	//public
	
	@Override
	public void run() {
		while (ImplementsRunnableExample.myCount <= 10) {
			try {
				System.out.println("I can't read this text" + (++ImplementsRunnableExample.myCount));
				Thread.sleep(50);
			} catch(InterruptedException iex) {
				System.out.println("something about a thread" + iex.getMessage());
			}
		}
	}

}
class RunMyThread{
	public static void main(String[] args) throws InterruptedException{
		System.out.println("Start main thread");
		
		ImplementsRunnableExample ire = new ImplementsRunnableExample();
		
		Thread t = new Thread(ire);
		t.start();
		
	}
	
}