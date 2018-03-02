package com.revature.driver;

import java.util.PriorityQueue;

public class ProducerConsumer {

	public static void main(String[] args) throws InterruptedException {
		
		PC pc = new PC();
		
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				
				pc.produce();
				
			}
			
		});
		
		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				
				pc.consume();;
				
			}
			
		});
		
		producer.start();
		consumer.start();
		
		producer.join();
		consumer.join();
	}
	
	
	public static class PC{
		
		//Resource Queue
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		//size of queue
		int capacity = 3;
		
		public void produce(){
			int product = 0;
			
			try {
				while(true) {
					synchronized (this) {
						
						//wait if queue is full
						while(queue.size() == capacity)
							wait();
						
						System.out.println("Producer produced: " + product);
						
						//add the product to the queue
						queue.add(product++);
						
						//wake up the consumer
						notify();
						
						Thread.sleep(1000);
					}
				}
			}
			catch(InterruptedException iex) {
				iex.printStackTrace();
			}
		}
		
		public void consume(){
			try {
				while(true) {
					synchronized (this) {
						
						//wait if queue is empty
						while(queue.size() == 0)
							wait();
						
						//retrieve the first product in the queue
						int consumed = queue.poll();
						
						System.out.println("Consumer consumed: " + consumed);
						
						//wake up the Producer
						notify();
						
						Thread.sleep(1000);
					}
				}
			}
			catch(InterruptedException iex) {
				iex.printStackTrace();
			}
		}
		
	}
	
}
