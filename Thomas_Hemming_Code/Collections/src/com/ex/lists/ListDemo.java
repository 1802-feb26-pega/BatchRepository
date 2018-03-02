package com.ex.lists;

import java.util.*;

public class ListDemo {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		List myList = new ArrayList();
		myList.add(10);
		myList.add(1, 9);
		myList.add(2, 8);
		myList.add(7);
		
		List myOtherList = new ArrayList();
		myOtherList.add(1);
		myOtherList.add(2);
		myOtherList.add(3);
		myOtherList.add(4);
		myOtherList.add(5);
		myOtherList.add(6);
		myOtherList.add(12);
		myOtherList.add(18);
		
		myList.addAll(1, myList);
		
		System.out.println(myList);
		System.out.println(myOtherList);
		
		
		
	}
}
