package com.ex.datatypes;

public class AboutStrings {
	/*
	 * In Java, String is bascially and object that represents sequence of char values
	 * 
	 * Strings are inmmutable - once initialized they do nto change -- referecne 
	 * just points to another string
	 * 
	 * StringBuffer and StringBUilder are MUTABLE
	 * StringBuffer is the thread-safe/synchronized version of stringbuilder
	 */
	public static void main(String[] args) {
		
			String str = "Hello"; // Creates a string in string pool
			StringBuffer buff = new StringBuffer("hello");
			StringBuilder build = new StringBuilder("hello");
			
			 str.concat(" world").concat(" test").concat( " anothertest");
			//str += " world";
			buff.append(" world").append(" test");
			build.append(" world");
					
			System.out.println(str + " " + buff + " " + build);
			
		
			
	}
}
