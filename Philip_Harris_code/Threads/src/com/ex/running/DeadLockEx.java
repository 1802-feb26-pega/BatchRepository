package com.ex.running;

public class DeadLockEx {
	
	public static void main(String[] args) throws InterruptedException{
		Object obj = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();
		
		Thread t = new Thread(new SynchroThread(obj,obj2));
		Thread t1 = new Thread(new SynchroThread(obj,obj2));
		Thread t2 = new Thread(new SynchroThread(obj,obj3));
		t.start();
		Thread.sleep(1000);
		t1.start();
		Thread.sleep(1000);
		t2.start();
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String name = Thread.currentThread().getName();
		System.out.println(name + " acquiring lock on " + obj1);
		
		synchronized(obj1){
			System.out.println(name + " Acquired lock on " + obj1);
			work();
		
			System.out.println(name + " acquiring lock on " + obj2);
		
			synchronized(obj2) {
				System.out.println(name + " Acquired lock on " + obj2);
				work();
			}
			System.out.println(name + " released lock " + obj2);
		}
		System.out.println(name + " released lock on " + obj1);
		System.out.println(name + " finished out execution");
	}

	private void work() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
		}catch(InterruptedException iex) {
			iex.printStackTrace();
		}
		
	}
}