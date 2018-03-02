package com.revature.driver;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueDemo
{
	public static void main(String[] args)
	{
		Deque<String> sample = new ArrayDeque<>();
		 
		sample.offer("Pidge");
		sample.offer("Allura");
		 
		sample.addFirst("Keith");
		sample.addLast("Lance");
		sample.addLast("Yellow Lion");
		 
		System.out.println(sample);
		
		sample.offer("Hunk");
		sample.offer("Red Lion");
		sample.offer("Blue Lion");
		sample.offer("Green Lion");
		sample.offer("Black Lion");
		 
		System.out.println("Queue before: " + sample);
		System.out.println("First comes: " + sample.pollFirst());
		System.out.println("Last comes: " + sample.pollLast());
		System.out.println("Queue after: " + sample);
		
		sample.removeFirst();
		sample.removeLast();
		System.out.println(sample);
		
		System.out.println("first: " + sample.getFirst());
		System.out.println("last: " + sample.peekLast());
	}
}
