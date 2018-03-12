package com.clazz;

public class GarbageCollection {
	public static void main(String[] args) {
		String a = new String("a;sdlkf");
		String b = new String("Something");
		
		a = null;
		
		//request JVM to run Garbage Collector
		System.gc();
		
		b = null;
		
		//another way to collect the garbage
		Runtime.getRuntime().gc();
		
		//finalize() is called on an object once before it is collected
		
		@Override
		protected void finalize() throws Throwable {
			System.out.println("Garbage Collector is called on " + this);
		}
	}
}
