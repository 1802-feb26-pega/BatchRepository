package com.ex.gc;

public class GarbageCollection {
	
	public static void main(String[] args) {
		String a = new String("kdljflasjdfk");
		String b = new String("kjdskalf");
		
		a = null;
		
		//request JVM to run garbage collector
		System.gc();
		
		b = null;
		
		//another way to request garbage collector
		Runtime.getRuntime().gc();
		
		/*while(true) {
			System.out.print("hello");
		}*/
		
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Garbage collector is called on " + this);
	}
}
