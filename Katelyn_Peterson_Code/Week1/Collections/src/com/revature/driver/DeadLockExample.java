package com.revature.driver;

public class DeadLockExample
{
	public static void main(String[] args) throws InterruptedException
	{
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();
		
		
	}
}


class SynchroThread implements Runnable
{
	private Object obj1;
	private Object obj2;
	
	public SynchroThread(Object o1, Object o2)
	{
		this.obj1 = o1;
		this.obj2 = o2;
	}
	
	public void run()
	{
		String name = Thread.currentThread().getName();
	}
}