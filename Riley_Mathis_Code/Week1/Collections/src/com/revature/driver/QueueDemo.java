package com.revature.driver;

import java.util.PriorityQueue;

public class QueueDemo {

	public static void main(String[] args) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);
		q.add(6);
		
		System.out.println(q.poll());
		System.out.println(q.peek());
		System.out.println(q.peek());
		System.out.println(q.poll());
		System.out.println(q.poll());
		System.out.println(q.peek());
		
		
	}
}
