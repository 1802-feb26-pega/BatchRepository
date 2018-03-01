package com.ex.gc;

public class GarbageCollection {

	
	public static void main(String[] args) {
		String a = new String("asd;fkjsdf");
		String b = new String("qwerty");
		
		String c = "Hello";
		
		a = null;
		
		//Request JVM to run GC
		System.gc();
		
		b = null;
		
		String d = "asdfads";
		
		String f = "aposidfns";
		
		a = "asfadf";
		
		
		//another way to request GC
		Runtime.getRuntime().gc();
		
		System.out.println("at the end of main");
		System.out.println("-------------------");

		
			for(int i = 0; i < 5088990; i++) {
				System.out.println(i);
				System.out.print(i);
			}
	}
	
	
	/*
	 * (non-javadoc)
	 * @see java.lang.Object#finalize
	 * 
	 * Finalize methos is called on an onbect once before it is Gargabe colected
	 * 
	 */
	@Override
	protected void finalize()throws Throwable{
			System.out.println("Garbage Collector is called on " + this);
	}
	
}	

