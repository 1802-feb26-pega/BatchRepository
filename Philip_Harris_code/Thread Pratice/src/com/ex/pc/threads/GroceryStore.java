package com.ex.pc.threads;

import java.util.LinkedList;
import java.util.Queue;

public class GroceryStore {

	static Queue<Integer> line = new LinkedList<Integer>();
	static final int BUFFERSIZE = 5;
	static GroceryStore g = new GroceryStore();
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 ProducerThread pjob= new ProducerThread(g);
		 ConsumerThread cjob = new ConsumerThread(g);
		 Thread one = new Thread(pjob);
		 Thread two = new Thread(cjob);
		
		line.add(1);
		line.add(100);
		line.add(10);
		
		one.start();
		two.start();			

	}
	
	public void checkOutLine(Boolean flag) {
		if(flag) {
			int item = (int) (Math.random() * 50 + 1);
			line.add(item);
			System.out.println("ProducerThread created :" + line.peek());
		}
		else {
			System.out.println("ConsumerThread used :" + line.remove());
			
		}
	}
}
