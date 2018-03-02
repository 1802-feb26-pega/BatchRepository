package com.revature.driver;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
	
	public static void main(String[] args) {
		Queue<Integer> myQueue = new LinkedList<Integer>();
		
		myQueue.add(50);
		myQueue.add(6);
		myQueue.add(3);
		
		System.out.println(myQueue.peek());
		
		//myQueue.
		
		Queue<Integer> myOtherQueue = new LinkedList<Integer>();
		myOtherQueue.add(6);
		
		if(myQueue.containsAll(myOtherQueue)) {
			System.out.println(myQueue + " contains all of the numbers in " + myOtherQueue);
		}
		myQueue.remove(6);
		System.out.println(myQueue);
	}

}
