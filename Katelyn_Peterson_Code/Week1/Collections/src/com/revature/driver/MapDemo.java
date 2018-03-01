package com.revature.driver;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MapDemo
{
	public static void main(String[] args)
	{
		HashMap<String, Integer> hm = new HashMap<>();
		LinkedHashMap<String, Integer> lm = new LinkedHashMap<>();
		TreeMap<String, Integer> tm = new TreeMap<>();
		
		hm.put("Hello", 13);
		hm.put("World", 6);
		hm.put("Java", 2);
		
		System.out.println("hm " + hm);
		
		hm.remove("Java");
		System.out.println("hm " + hm);
		
		lm.put("Hello", 13);
		lm.put("World", 6);
		lm.put("Java", 2);
		
		System.out.println("lm " + lm);
		
		lm.remove("Java");
		System.out.println("lm " + lm);
		
		tm.put("Hello", 13);
		tm.put("World", 6);
		tm.put("Java", 2);
		
		System.out.println("tm " + tm);
		
		tm.remove("Java");
	}
}
