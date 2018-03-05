/**
 * 
 */
package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phili
 *
 */
public class Execute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//EvaluationService q1 = new EvaluationService();
		
		//System.out.println(q1.reverse("Revature"));
		//System.out.println(q1.acronym("Portable Network Graphics"));
		
		/*
		Triangle t1 = new Triangle(10,10,10);
		Triangle t2 = new Triangle(9,10,10);
		Triangle t3 = new Triangle(1,2,3);
	
		System.out.println("Is Triangle one a Equilateral? "  + t1.isEquilateral());
		System.out.println("Is Triangle one a Isosceles? "  + t1.isIsosceles());
		System.out.println("Is Triangle one a Scalene? "  + t1.isScalene());
		
	
		System.out.println("Is Triangle two a Equilateral? "  + t2.isEquilateral());
		System.out.println("Is Triangle tw0 a Isosceles? "  + t2.isIsosceles());
		System.out.println("Is Triangle two a Scalene? "  + t2.isScalene());
	
		System.out.println("Is Triangle three a Equilateral? "  + t3.isEquilateral());
		System.out.println("Is Triangle three a Isosceles? "  + t3.isIsosceles());
		System.out.println("Is Triangle three a Scalene? "  + t3.isScalene());
		
		*/
		
		EvaluationService q4 = new EvaluationService();
		
		//System.out.println(q4.getScrabbleScore("cabbage"));
		
		//EvaluationService q5 = new EvaluationService();
		//System.out.println(q5.cleanPhoneNumber("613.995.0253"));
		
		//EvaluationService q6 = new EvaluationService();
		//q6.wordCount("To be or not to be: that is the question wherthe 'tis nobler to suffer");
		
		//EvaluationService q8 = new EvaluationService();
		//System.out.println(q8.toPigLatin("latin"));


		//EvaluationService q9 = new EvaluationService();
		//System.out.println("Is " + 153 + " a Armstrong Number: " + q9.isArmstrongNumber(153));
		
		EvaluationService q10 = new EvaluationService();
		List<Integer>  a= q10.calculatePrimeFactorsOf(100);
		for(int x : a)
			System.out.println(x);
		
		//RotationalCipher q11 = new RotationalCipher(5);
		//System.out.println(q11.rotate("omg"));
		//System.out.println(q11.rotate("abcdefghijklmnopqrstuvwxyz"));
		
		//EvaluationService q12 = new EvaluationService();
		//System.out.println("The 100th prime number is: " + q12.calculateNthPrime(100));
		
		//EvaluationService q13 = new EvaluationService();
		//System.out.println(encode("test"));
		//EvaluationService q14 = new EvaluationService();
		//System.out.println(decode(encode("test")));
		
	
		//EvaluationService q16 = new EvaluationService();
		//System.out.println("Is this a pangram? " + q16.isPangram("Jived fox nymph grabs quick waltz."));
		
		
//		EvaluationService q17 = new EvaluationService();
//		Temporal given = null;
//		given = q17.getGigasecondDate(given);

		//q17.getGigasecondDate();
		
		//EvaluationService q18 = new EvaluationService();
		//int[] a = {3,5};
		//System.out.println(q18.getSumOfMultiples(20,a));

		//EvaluationService q19 = new EvaluationService();
		//System.out.println("Is this a valid luhn value: " + q19.isLuhnValid("1 4539  0343 6467"));
		
		//EvaluationService q20 = new EvaluationService();
		//System.out.println(q20.solveWordProblem("What is 25 divided by 5?"));

		
	}	
}

