package com.revature.driver;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueDemo
{
	public static void main(String[] args)
	{
		Deque<String> queueNames = new ArrayDeque<>();
		 
		queueNames.offer("Katherine");
		queueNames.offer("Bob");
		 
		queueNames.addFirst("Jim");
		queueNames.addLast("Tom");
		 
		System.out.println(queueNames);
		
		queueNames.offer("Bill");
		queueNames.offer("Kim");
		queueNames.offer("Lee");
		queueNames.offer("Peter");
		queueNames.offer("Sam");
		 
		System.out.println("Queue before: " + queueNames);
		System.out.println("First comes: " + queueNames.pollFirst());
		System.out.println("Last comes: " + queueNames.pollLast());
		System.out.println("Queue after: " + queueNames);
		
		System.out.println("first: " + queueNames.getFirst());
		System.out.println("last: " + queueNames.peekLast());
	}
}
