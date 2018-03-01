package com.revature.driver;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {
	public static void main(String[] args) throws InterruptedException {
        Queue<Number> queue = new LinkedList<Number>(); // Implementing a Queue
        ArrayDeque<Number> aDeque = new ArrayDeque<Number>(); // Implementing a deque
        
        while (queue.size() < 10) {
        	queue.add(Math.random());
        	aDeque.add(Math.random());
        }
        
        System.out.println(aDeque);
        //System.out.println("The first number that was removed was: " + queue.remove());
        System.out.println("The first number that was removed was: " + aDeque.removeFirst());
        System.out.println("The last number that was removed was: " + aDeque.removeLast());
        
        
        
//        while (!queue.isEmpty()) {
//            System.out.println(queue.remove());
//            Thread.sleep(1000);
//        }
    }
}
