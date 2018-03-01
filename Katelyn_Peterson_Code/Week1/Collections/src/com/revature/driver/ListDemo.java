package com.revature.driver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListDemo
{

	public static void main(String[] args)
	{
		List myList = new ArrayList();
		
		myList.add(10);
		myList.add(1, 9);
		myList.add(2, 8);
		myList.add(7);
		
		List myOtherList = new ArrayList();
		myOtherList.add(2);
		myOtherList.add(3);
		myOtherList.add(5);
		myOtherList.add(7);
		myOtherList.add(11);
		myOtherList.add(13);
		
		System.out.println(myList);
		myList.addAll(1, myOtherList);
		System.out.println(myList);
		
		((Collection)myList).remove(2);
		myList.remove(new Integer(200));
		System.out.println(myList);
		
		System.out.println(myList.get(3));
		
		myList.set(0, 12);
		System.out.println(myList);
		
		myList.set(0, 22);
		System.out.println(myList);
		
		System.out.println(myList.indexOf(13));
		
		myList.add(13);
		System.out.println(myList);
		System.out.println(myList.indexOf(13));
		System.out.println(myList.lastIndexOf(13));
		
		
	}

}
