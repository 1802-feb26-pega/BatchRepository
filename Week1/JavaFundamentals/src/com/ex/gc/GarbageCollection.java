package com.ex.gc;

public class GarbageCollection {
	
	public static void main(String[] args) {
		String a = new String("oaijgoija");
		String b = new String("kahfiah");
		
		String c = "hello";
	
		
		a = null;
		
		//request JVM to run garbage collector
		System.gc();
		
		b = null;
		
		String d = "aojgiaj";
		
		String f = "ajigusjoj";
		
		a = "ajhgiuavh";
		
		f = d;
		d = b;
		
		//another way to request garbage collector 
		Runtime.getRuntime().gc();
		
		System.out.println("at end of main");
		System.out.println("-----------");
		
		for(int i = 0; i < 500 ; i = i + 5) {
			String[] temp = new String[1000];
			temp[500] = "auhgiua";
			System.out.print(i);
			
			temp = null;
		}
		for(int i = 0; i < 1000 ; i = i + 5) {
			System.out.println(i);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 * 
	 * finalize method is called on an object once
	 * before it is garbage collected
	 * 
	 */
	@Override
	protected void finalize() throws Throwable{
		System.out.println("Garbage collector is called on " + this);
	}
}
