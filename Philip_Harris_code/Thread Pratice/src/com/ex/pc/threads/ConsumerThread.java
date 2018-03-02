package com.ex.pc.threads;


public class ConsumerThread implements Runnable{
	static GroceryStore g;
	static boolean flag = false;
	static ProducerThread p;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		}catch(InterruptedException iex) {
			iex.printStackTrace();
		}
		synchronized(g) {work();}
	}
	
	public ConsumerThread(GroceryStore o) {
		this.g = o;
	}	
	
	
	public static void work() {
		
		int count = 0;
		while(count < 100 ) {
			
			if(g.line.size() == 0) { 
				//g.notEnough();
					try {				
						g.wait();
						System.out.println("Consumer is waiting");
					}
				 catch (InterruptedException e) {
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
