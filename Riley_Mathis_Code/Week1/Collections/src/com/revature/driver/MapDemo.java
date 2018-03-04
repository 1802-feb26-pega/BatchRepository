package com.revature.driver;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
	public static void main(String[] args) {
		Map<Integer, String> m = new HashMap<Integer, String>();
		
		m.put(1, "hello");
		m.put(0,  "hi");
		
		System.out.println(m.get(0));
		System.out.println(m);
	}
}
