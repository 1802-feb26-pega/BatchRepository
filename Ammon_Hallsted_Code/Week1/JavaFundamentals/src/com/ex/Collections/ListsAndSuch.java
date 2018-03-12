package com.ex.Collections;

import java.util.List;
import java.util.ArrayList;

public class ListsAndSuch {

		public static void main(String[] args) {
			
			List myList = new ArrayList();
			myList.add(10);
			myList.add(1, 9);
			myList.add(2, 8);
			myList.add(7);
			
			List otherList = new ArrayList();
			otherList.add(2);
			otherList.add(3);
			otherList.add(5);
			otherList.add(7);
			otherList.add(11);
			otherList.add(13);
			
			System.out.println(myList);
			myList.addAll(1, otherList);
			System.out.println(myList);
			
			myList.remove(1);
			System.out.println(myList);
			
			//getting an index
			System.out.println(myList.get(3));
			
			//Setting places as something
			myList.set(0,12);
			System.out.println(myList);
			
			//Printing an index of
			System.out.println(myList.indexOf(13));
		}
}

