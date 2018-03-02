package com.revature.producerConsumer;

import java.util.Iterator;
import java.util.PriorityQueue;
//import java.util.concurrent.Semaphore;

/**
 * <h1> ProdConsSolution </h1> 
 * The ProdConsSolution class demonstrates my current solution to the Producer/Consumer problem.
 * 
 * @author Katelyn Peterson
 * @version 1.0
 * @since 03-02-2018
 *
 */

public class ProdConsSolution
{
	// Semaphore Variables - attempted to use Semaphores, but I need to understand them better before I use
    //static Semaphore sem = new Semaphore(0);
    //static Semaphore mutex = new Semaphore(1);
	
	public static void main(String[] args) throws InterruptedException
	{
        // 1. Variables
		TestSolution sampleQueue = new TestSolution();
        
		// 2. Input
        new Thread(new Consumer(sampleQueue)).start();
        
        // 3. Calculation & 4. Output
        for (int x = 0; x < 1000; ++x)
        {
            new Thread(new Producer(sampleQueue, x)).start();
        }
	}
	
	/**
	 * <h1> Producer </h1> 
	 * The Producer class attempts to add data to the queue.
	 * 
	 * @author Katelyn Peterson
	 * @version 1.0
	 * @since 03-02-2018
	 *
	 */
	static class Producer implements Runnable
	{
		// Variables
		TestSolution sampleQueue;
		Integer newNumber;
		
		// Constructor
		Producer(TestSolution sampleQueue, Integer newNumber)
		{
			this.sampleQueue = sampleQueue;
			this.newNumber = newNumber;
		}
		
		// Functions(Methods)
		public void run()
		{
			try
			{
				// Attempt to add a new number to the queue
				sampleQueue.adds(newNumber);
			} 
			catch (InterruptedException e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <h1> Consumer </h1> 
	 * The Consumer class attempts to retrieve data from the queue.
	 * 
	 * @author Katelyn Peterson
	 * @version 1.0
	 * @since 03-02-2018
	 *
	 */
	static class Consumer implements Runnable
	{
		// Variables
		TestSolution sampleQueue;
		
		// Constructor
	    public Consumer(TestSolution sampleQueue)
	    {
	        this.sampleQueue = sampleQueue;
	    }
		
	    // Functions(Methods)
		public void run()
		{
			try
			{
				// Attempt to retrieve (and remove) a number from the queue
				sampleQueue.retrieve();;
			} 
			catch (InterruptedException e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <h1> TestSolution </h1> 
	 * The TestSolution class handles the heavy lifting, both adding and removing items from the queue 
	 * as well as signaling when to wait and when to act.
	 * 
	 * @author Katelyn Peterson
	 * @version 1.0
	 * @since 03-02-2018
	 *
	 */
	static class TestSolution
	{
		// Variables
		PriorityQueue<Integer> sampleQueue = new PriorityQueue<>();
	    private int maxSize = 6;
	
	    synchronized void retrieve() throws InterruptedException
	    {
	        while (true)
	        {
	            while (sampleQueue.isEmpty())
	            {
	            	wait();
	            	//mutex.release();
	            }
                //sem.acquire(1);
                //mutex.acquire();
	            
	            sampleQueue.remove();
	            
	            System.out.print("Current Queue Contents: [");
	            
	            Iterator<Integer> it = sampleQueue.iterator();
	            while (it.hasNext())
	            {
	            	System.out.print(it.next() + ", ");
	            }
	            
	            System.out.print("]");
	            System.out.println();
	            notifyAll();
	        }
	    }
	
	    synchronized void adds(Integer newNumber) throws InterruptedException
	    {
	        while (sampleQueue.size() == maxSize)
	        {
	        	wait();
	        	//mutex.release();
	        }
            //sem.acquire(1);
            //mutex.acquire();
	        
	        sampleQueue.add(newNumber);
	        
	        System.out.print("Current Queue Contents: [");
	        
	        Iterator<Integer> it = sampleQueue.iterator();
	        while (it.hasNext())
	        {
	        	System.out.print(it.next() + ", ");
	        }
	        
	        System.out.print("]");
	        System.out.println();
	        notifyAll();
	    }
	}
}