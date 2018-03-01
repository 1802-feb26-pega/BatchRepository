package com.revature.driver;

import java.util.HashSet;
import java.util.Set;

public class SetDemo
{
	public static void main(String[] args)
	{
		Set<String> mySet = new HashSet<>();
		mySet.add("Hello");
		mySet.add("Hi");
		mySet.add("Bonjour!");
		mySet.add("Hello");
		
		String s = "Hello";
		String s2 = new String("Hello");
		String s3 = "Hello";
		
		System.out.println(s.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		
		System.out.println(s == s2);
		System.out.println(s == s3);
		System.out.println(s2 == s3);
		
		System.out.println(s.equals(s2));
		System.out.println(s.equals(s3));
		System.out.println(s2.equals(s3));
		
		System.out.println(mySet);
	}
}
