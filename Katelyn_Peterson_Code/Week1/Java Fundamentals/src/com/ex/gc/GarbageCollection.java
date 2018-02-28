package com.ex.gc;

public class GarbageCollection
{
	public static void main(String[] args)
	{
		String a = new String("oaijgoija");
		String b = new String("kahfish");
		
		String c = "hello";
		
		a = null;
		
		String d = "aojgiaj";
		
		String e = "nonsense";
		
		a = "ajhgliu";
		
		// request JVM to run garbage collector
		System.gc();
		
		b = null;
		
		// another way to request garbage collector
		Runtime.getRuntime().gc();
		
		System.out.println("at end of main");
		System.out.println("-------------");
		
		for (int i = 0; i < 500; i = i + 5)
		{
			String[] temp = new String [1000];
		}
			
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 * 
	 * finalize function is called on an object once
	 * before it is garbage collected
	 */
	@Override
	protected void finalize() throws Throwable
	{
		System.out.println("Garbage collector is called on " + this);
	}
}
