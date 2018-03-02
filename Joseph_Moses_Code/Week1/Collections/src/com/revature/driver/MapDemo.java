package com.revature.driver;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

	public static void main(String[] args) {
		
		Map<Integer, String> myMap = new HashMap<Integer, String>();
		
		myMap.put(1, "hi");
		myMap.put(2, "hey");
		myMap.put(3, "hello");
		myMap.put(4, "hi");
		myMap.put(5, "hi");
		
		System.out.println(myMap);
		
		System.out.println(myMap.get(3));
		
		System.out.println(myMap.size());
		
		System.out.println(myMap.keySet());
		
		System.out.println(myMap.entrySet());
		
		System.out.println(myMap.values());
		
		System.out.println(myMap.remove(4));
		System.out.println(myMap);
		
		myMap.replace(5, "Hola");
		System.out.println(myMap);
		
		
	}
	
}
