package com.ex.pc.threads;

public class ProducerThread implements Runnable {
	static boolean flag = true;
	static GroceryStore g;
	static ConsumerThread c;
	
	@Override
	public void run() {
		// TODO Auto-generated method 
		try {
			Thread.sleep(5000);
		}catch(InterruptedException iex) {
			iex.printStackTrace();
		}
		synchronized(g) {work();}
	}
	
	public ProducerThread(GroceryStore o) {
		this.g = o;
	}
	
	public static void work() {
		int count = 0;
		
		while(count < 100) {
			
			if(g.line.size() == g.BUFFERSIZE) { 
				//g.tooMuch();
				try {
					g.wait();
					System.out.println("Producer is waiting");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				g.checkOutLine(flag);
				g.notify();
			}
			
			count++;
		
		}
		System.out.println(Thread.currentThread().getName() + " Is finished");
		System.out.println("There are " + g.line.size() + " items left");
	}
}
