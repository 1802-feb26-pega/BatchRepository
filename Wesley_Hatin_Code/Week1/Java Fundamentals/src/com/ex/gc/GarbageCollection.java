package com.ex.gc;

public class GarbageCollection {
	//can't get this to actually work.
	
	public static void main(String[] args) {
		String a = new String("asdflkjds");
		String b = new String("l;kfdgl");
		
		a = null;
		
		//request JVM to run garbage collector
		for(int i=0; i<100; i++) {
			System.gc();
		}
		
		
		b = null;
		
		//another way to request garbage collector
		Runtime.getRuntime().gc();
		
		System.out.println("at end of main");
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Garbage collector is called on " + this);
	}

}
