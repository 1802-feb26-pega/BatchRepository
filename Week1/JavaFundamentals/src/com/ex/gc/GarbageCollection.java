package com.ex.gc;

public class GarbageCollection {
	
	public static void main(String[] args) {
		String a = new String("adsjflaskjf");
		String b = new String("sflkajs");
		a = null;
	
		// request jvm to run garbage collector
		System.gc();
		
		b = null;
		
		//another way to request garbage collector
		Runtime.getRuntime().gc();
		
		
	}
	
	//@see java.lang.Object#finalize()
	@Override
	protected void finalize() throws Throwable{
		System.out.println("Garbage collector is called on " + this);
	}
}
