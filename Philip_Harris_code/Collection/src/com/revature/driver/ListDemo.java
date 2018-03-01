package com.revature.driver;

import java.util.*;

public class ListDemo {
	public static void main(String[] args) {
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(10);
		myList.add(1,9);
		myList.add(2,8);
		myList.add(7);
		
		
		List myOtherlist = new ArrayList();
		myOtherlist.add(2);
		myOtherlist.add(3);
		myOtherlist.add(5);
		myOtherlist.add(7);
		myOtherlist.add(11);
		myOtherlist.add(13);
		
		System.out.println(myList);
		myList.addAll(1,myOtherlist);
		System.out.println(myList);
		
		myList.remove(1);
		System.out.println(myList);
		
		myList.remove(new Integer(200));
		((Collection) myList).remove(2);
		System.out.println(myList.get(3));
		myList.set(0, 12);
		System.out.println(myList);
		
		myList.add(0,22);
		System.out.println(myList);
		
		myList.add(13);
		System.out.println(myList);
		System.out.println(myList.indexOf(13));
		System.out.println(myList.lastIndexOf(13));
		
		for(int value: myList) {
			if(value==(13)) {
				System.out.println(value);
			}
			
		}
		
		for(int i = 0; i < myList.size(); i++) {
			if(myList.get(i).equals(13))
				System.out.println(i);
		}
		
	}

}
