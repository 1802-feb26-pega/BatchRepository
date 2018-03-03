package com.ex.gc;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GarbageCollection {

	public static void main(String[] args) {
		String a = new String("jadshgakjs");
		String b = new String("jashg");
		
		String c = "hello";
		
		a = null;
		
		//request jvm to run garbage collector
		System.gc();
		
		b = null;
		
		String d = "akjsdg";
		
		String f = "lkjasdgh";
		
		
		//another way to request garbage collector
		Runtime.getRuntime().gc();
		
		System.out.println("at end of main");
		System.out.println("------------");
		
		for(int i = 0; i < 500; i  = i + 5) {
			System.out.print(i);
		}
		

	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 * 
	 * finalize method is called on an object once before it is garbage collected
	 */
	@Override
	protected void finalize() throws Throwable{
		System.out.println("garbage collector is called on " + this);
	}
}
