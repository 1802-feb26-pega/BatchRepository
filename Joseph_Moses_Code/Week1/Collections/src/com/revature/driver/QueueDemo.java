package com.revature.driver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {

	public static void main(String[] args) {
		
		Queue<Integer> myQueue = new PriorityQueue<>();
		
		myQueue.add(10);
		myQueue.add(9);
		myQueue.add(8);
		myQueue.add(7);
		myQueue.add(6);
		myQueue.add(5);
		
		System.out.println(myQueue.peek()); //returns null if empty
		System.out.println(myQueue);
		
		myQueue.remove(7);
		System.out.println(myQueue);
		
		System.out.println(myQueue.element()); //throws exception if empty
		System.out.println(myQueue);
		
		System.out.println(myQueue.poll());
		System.out.println(myQueue);
		
		Deque<Integer> myDeque = new ArrayDeque<>();
		
		myDeque.add(5);
		myDeque.add(6);
		myDeque.add(7);
		myDeque.add(8);
		myDeque.add(9);
		myDeque.add(10);
		myDeque.add(11);
		myDeque.addFirst(18);
		myDeque.addLast(20);
		
		System.out.println(myDeque.peekFirst());
		System.out.println(myDeque.peekLast()); //return null if empty
		System.out.println(myDeque);
		System.out.println(myDeque.getLast()); //throws exception if empty
		System.out.println(myDeque);
		System.out.println(myDeque.getFirst());
		System.out.println(myDeque);
		System.out.println(myDeque.remove(8));
		System.out.println(myDeque);
		System.out.println(myDeque.pollLast());
		System.out.println(myDeque);
		System.out.println(myDeque.pop());
		System.out.println(myDeque);
		System.out.println(myDeque.pollFirst());
		
		
	}
}
 