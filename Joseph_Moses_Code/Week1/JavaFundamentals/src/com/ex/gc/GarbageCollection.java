package com.ex.gc;

import java.util.HashMap;
import java.util.Map;

public class GarbageCollection {

	public static void main(String[] args) {
		/*String a = new String("jadshgakjs");
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
		*/
		
		
		
		System.out.println(toPigLatin("quick fast run"));
		
		
		
		}
		

static String toPigLatin(String string) {

	string = string.toLowerCase();
	String[] input = string.split(" ");
	StringBuilder output = new StringBuilder("");
	String vowels = "aeiouy";
	
	for(String s : input) {
		
		StringBuilder temp = new StringBuilder(s);
		
		int firstVowel = -1;
		
		for(int i = 0; i < s.length(); i++) {
			
			String letter = new Character(s.charAt(i)).toString();
			if(vowels.indexOf(s.charAt(i)) != -1) {
				firstVowel = i;
				break;
			}
		}

		System.out.println(firstVowel);
		
		if(firstVowel == 0) {
			temp.append("ay");
		}
		else {
			
			if(s.charAt(0) == 'q') {
				temp.append(temp.subSequence(0, firstVowel + 1) + "ay");
				temp.delete(0, firstVowel + 1);
			}
			else {
				temp.append(temp.subSequence(0, firstVowel) + "ay");
				temp.delete(0, firstVowel);
			}
		}
		
		if(!s.equals(input[input.length - 1])) {
			temp.append(" ");
		}
		
		output.append(temp.toString());
		
	}
	
	return output.toString();
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
