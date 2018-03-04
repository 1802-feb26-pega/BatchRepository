package com.revature.driver;

public class DeadlockExample {

	public static void main(String[] args) throws InterruptedException {
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();
		
		Thread t1 = new Thread(new SynchroThread(obj1, obj2));
		Thread t2 = new Thread(new SynchroThread(obj2, obj3));
		Thread t3 = new Thread(new SynchroThread(obj1, obj3));
		
		t1.start();
		Thread.sleep(1000);
		
		t2.start();
		Thread.sleep(1000);
		
		t3.start();
		Thread.sleep(1000);
		
	}
}

class SynchroThread implements Runnable{
	private Object obj1;
	private Object obj2;
	public SynchroThread(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}
	public void run(){
	
		String name = Thread.currentThread().getName();
		
		System.out.println(name+ " acquiring lock on "+ obj1);
		
		synchronized (obj1) {
			System.out.println(name + " acquired lock on "+ obj1);
			work();
			
			System.out.println(name + " acquiring lock on "+ obj2);
			
			synchronized(obj2) {
				System.out.println(name + " acquired lock on "+ obj2);
				work();
			}
			System.out.println(name+" released lock on "+ obj2);
		}
		
		System.out.println(name+" released lock on "+obj1);
		System.out.println(name+" finished our execution!");
		
		
		
	}
	private void work() {
		try {
			Thread.sleep(1000);
		} catch(InterruptedException iex) {
			iex.printStackTrace();
		}
	}
}