package com.ex.Threads;
import java.lang.Thread;
class ImplementThread extends Thread {
	
	public static int myCount = 0;
	public void run() {
		while(ImplementThread.myCount <= 10) {
			try {
				System.out.println("Outside thread number: " + (++ImplementThread.myCount));
				Thread.sleep(1000);
			} catch(InterruptedException iex) {
				System.out.println("Exception Thread: " + iex.getMessage());
			}
		}
	}
}