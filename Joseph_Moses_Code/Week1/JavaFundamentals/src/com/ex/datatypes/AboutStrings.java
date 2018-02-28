package com.ex.datatypes;

public class AboutStrings {

	/*
	 * In Java, String is basically an object that represents a sequence of
	 * char values
	 * 
	 * Strings are immutable - once initialized the do not change -- reference
	 * just points to another string
	 * 
	 * StringBuffer and StringBuilder are MUTABLE
	 * StringBuffer is the thread-safe/synchronized version of StringBuilder
	 */
	public static void main(String[] args) {
		
		String str = "hello"; //creates a string in string pool
		StringBuffer buff = new StringBuffer("hello");
		StringBuilder build = new StringBuilder("hello");
		
		str.concat(" world").concat(" test").concat(" anothertest");
		buff.append(" world").append(" test");
		build.append(" world");
		
		System.out.println(str + " " + buff + " " + build);
	}
}
