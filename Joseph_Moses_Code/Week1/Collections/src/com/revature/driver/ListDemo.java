package com.revature.driver;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

	public static void main(String[] args) {
		
		List<Integer> myList = new ArrayList<>();
		myList.add(10);
		myList.add(1, 9);
		myList.add(2, 8);
		myList.add(7);
		
		List<Integer> myOtherList = new ArrayList();
		myOtherList.add(2);
		myOtherList.add(3);
		myOtherList.add(5);
		myOtherList.add(7);
		myOtherList.add(11);
		myOtherList.add(13);
		
		myList.addAll(1, myOtherList);
		System.out.println(myList);
		
		myList.remove(1);
		System.out.println(myList);
		
		System.out.println(myList.get(3));
		myList.set(0, 12);
		System.out.println(myList);
		myList.add(0, 22);
		System.out.println(myList);
		
		myList.add(13);
		System.out.println(myList.indexOf(13));
		
		System.out.println(myList.lastIndexOf(13));
		
		for(int value : myList) {
			if(value == 13) {
				System.out.println(value);
			}
		}
		
	}
}
