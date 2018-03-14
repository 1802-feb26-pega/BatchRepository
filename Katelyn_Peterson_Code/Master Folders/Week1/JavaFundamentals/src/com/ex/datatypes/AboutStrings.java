package com.ex.datatypes;

public class AboutStrings {
	/* In Java, String is basically an object that represents sequence of
	 * char values
	 * 
	 * Strings are immutable - once initialized they do not change -- reference
	 * just points to another string 
	 * 
	 * StringBuffer and StringBuilder are MUTABLE
	 * StringBuffer is the thread-safe/synchronized version of stringbuilder
	 */
	
	public static void main(String[] args) {
		
		String str = "hello"; //creates a string in string pool
		StringBuffer buff = new StringBuffer("hello");
		StringBuilder build = new StringBuilder("hello");
		
		String h = str.concat(" world").concat(" test").concat(" anothertest");
	//	str += " world";
		buff.append(" world").append(" test");
		build.append(" world");
		
		System.out.println(str + " " + buff + " " + build);
		
		
		
	}

}
