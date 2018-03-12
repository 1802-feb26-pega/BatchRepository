package com.ex.Collections;

import java.util.Set;
import java.util.HashSet;

public class Sets {
	public static void main(String[] args) {
		
		Set<String> mySet = new HashSet<String>();
		
		mySet.add("Hello");
		mySet.add("Hi");
		mySet.add("Bonjour");
		mySet.add("Hello");
		
		String s = "Hello";
		String s2 = new String("Hello");
		String s3 = "Hello";
		
		System.out.println(s.hashCode());
	}
}
