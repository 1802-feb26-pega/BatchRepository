package com.clazz;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Project1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Which case should we try?");
		Scanner scan = new Scanner(System.in);
		int switchNum = scan.nextInt();
		//scan.close();
		switch(switchNum) {
			case 1 : 
				System.out.println("What number shall we take the square root of?");
				Scanner scan1 = new Scanner(System.in);
				double sqrRoot = scan1.nextDouble();
				System.out.println(Math.sqrt(sqrRoot));
				scan1.close();
				break;
			case 2 :
				LocalDateTime dats = LocalDateTime.now();
				System.out.println(dats.toLocalDate());
				break;
			case 3 : 

				System.out.println("What string shall I split?");
				
				String splitter = scan.next();
				String[] splat = splitter.split("");
				System.out.println(splat);
				scan.close();
				break;
			default :
				System.out.println("Didn't work. Run me again!");
				break;
		}
	
	}

}
