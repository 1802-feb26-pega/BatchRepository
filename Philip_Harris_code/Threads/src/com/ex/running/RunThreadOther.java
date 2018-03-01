package com.ex.running;


//Example using extends THread

class  RunThreadRun extends Thread{
	public static int myCount =0;
	public void run() {
		while(myCount <= 10) {
			try {
				System.out.println("Expl Thread: " + (++myCount));
				Thread.sleep(1000);
			}catch(InterruptedException  iex){
				System.out.println("Mistakes were made: " + iex.getMessage());
			}
		}
	}
}
public class RunThreadOther{
	public static void main(String[] args) {
		RunThreadRun rtr = new RunThreadRun();
		rtr.start();
		while(rtr.myCount <= 10) {
			try{
				System.out.println("Main Thread: " + (++RunThreadRun.myCount));		
				Thread.sleep(1000);
			}catch(InterruptedException  iex){
				System.out.println("Mistakes were made: " + iex.getMessage());
			}
		}
		System.out.println("End of the Main Thread");
		}
		
	}
