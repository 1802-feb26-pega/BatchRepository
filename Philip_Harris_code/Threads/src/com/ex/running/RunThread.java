package com.ex.running;

//Example of using Runnable

class ImplementRunable implements Runnable {
	
	public static int myCount = 0;

	public void run() {
		while(ImplementRunable.myCount <= 10) {
			try {
				System.out.println("Expl Thread: " + (++ImplementRunable.myCount));
				Thread.sleep(10000);
			}catch(InterruptedException  iex){
				System.out.println("Mistakes were made: " + iex.getMessage());
			}
		}
	}
}
public class RunThread{
	public static void main(String[] args) {
		System.out.println("Starting MAIN THREAD");

	ImplementRunable ire = new ImplementRunable();
	Thread t = new Thread(ire);
	t.start();
	
	while(ImplementRunable.myCount <= 10) {
		try{
			System.out.println("Main Thread: " + (++ImplementRunable.myCount));		
			Thread.sleep(10000);
		}catch(InterruptedException  iex){
			System.out.println("Mistakes were made: " + iex.getMessage());
		}
	}
	System.out.println("End of the Main Thread");
	}
	
}


