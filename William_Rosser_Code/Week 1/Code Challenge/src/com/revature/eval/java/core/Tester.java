package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.Month;

public class Tester {

	public static void main(String[] args) {
		EvaluationService es = new EvaluationService();
		
		//#1
//		System.out.println(es.reverse(""));
//		System.out.println(es.reverse("!yrgnuh m'I"));
		
		//#2
//		System.out.println(es.acronym("Portable Network Graphics"));
//		System.out.println(es.acronym("Complementary, metal-oxide semiconductor"));
	
		//#3
//		EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
//		System.out.println(triangle.isEquilateral());
//		//equilateral == false
//		triangle = new EvaluationService.Triangle(0.5, 0.5, 0.5);
//		System.out.println(triangle.isEquilateral());
//		// equilateral == true
//		triangle = new EvaluationService.Triangle(2, 3, 4);
//		System.out.println(triangle.isIsosceles());
//		//isosceles = false
//		triangle = new EvaluationService.Triangle(0.5, 0.4, 0.5);
//		System.out.println(triangle.isIsosceles());
//		//isosceles = true
//		triangle = new EvaluationService.Triangle(4, 4, 4);
//		System.out.println(triangle.isScalene());
//		//scalene = false
//		triangle = new EvaluationService.Triangle(0.5, 0.4, 0.6);
//		System.out.println(triangle.isScalene());
//		//scalene = true
		
		//#4
//		System.out.println(es.getScrabbleScore("f"));
//		System.out.println(es.getScrabbleScore("zoo"));
//		System.out.println(es.getScrabbleScore("street"));
//		System.out.println(es.getScrabbleScore("quirky"));
//		System.out.println(es.getScrabbleScore("OxyphenButazone"));
		
		//#5
//		System.out.println(es.cleanPhoneNumber("(223)-456.7890"));
//		System.out.println(es.cleanPhoneNumber("1(223)  456-7890"));
//		System.out.println(es.cleanPhoneNumber("+1(223) 456-7890"));
//		try {
//			System.out.println(es.cleanPhoneNumber("(223) 156-7890"));
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
//		try {
//			System.out.println(es.cleanPhoneNumber("65654684654684168118"));
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
		
		//#6
//		String test = "word,word\npie cat dog dog cat"
//				+ "";
//		Map<String, Integer> map = es.wordCount(test);
//		for (String s : map.keySet()) {
//			System.out.print(s + " ");
//			System.out.println(map.get(s));
//		}
		
		//#7
//		List<String> sortedList = Collections.unmodifiableList(Arrays.asList("1", "3", "4", "6", "8", "9", "11"));
//
//		EvaluationService.BinarySearch<String> searchStr = new EvaluationService.BinarySearch<>(sortedList);
//		System.out.println(searchStr.indexOf("3"));
//		
//		List<Integer> sortedList2 = Collections.unmodifiableList(Arrays.asList(1, 3, 4, 6, 8, 9, 11, 27));
//		EvaluationService.BinarySearch<Integer> searchInt = new EvaluationService.BinarySearch<>(sortedList2);
//		
//		System.out.println(searchInt.indexOf(11));
		
		//#8
//		System.out.println(es.toPigLatin("quick fast run"));
		
		//#9
		//System.out.println(es.isArmstrongNumber(9474));
		
		//#10
//		System.out.println(es.calculatePrimeFactorsOf(901255L));
//		System.out.println(es.calculatePrimeFactorsOf(12));
		
		//#11
		
//		EvaluationService.RotationalCipher rs = new EvaluationService.RotationalCipher(13);
//		System.out.println(rs.rotate("Gur dhvpx oebja sbk whzcf bire gur ynml qbt."));
		
		//#12
//		System.out.println(es.calculateNthPrime(10001));
		
		//#13
//		EvaluationService.AtbashCipher ac = new EvaluationService.AtbashCipher();
//		System.out.println(ac.encode("The quick brown fox jumps over the lazy dog."));
		
		//#14
//		System.out.println(ac.decode("gsvjf rxpyi ldmul cqfnk hlevi gsvoz abwlt"));
		
		//#15
//		System.out.println(es.isValidIsbn("3-598-21508-8"));
//		System.out.println(es.isValidIsbn("3-598-21508-9"));
//		System.out.println(es.isValidIsbn("3-598-21507-X"));
//		System.out.println(es.isValidIsbn("3-598-21507-A"));
		
		//#16
//		System.out.println(es.isPangram("a quick movement of the enemy will jeopardize five gunboats"));
//		System.out.println(es.isPangram("the quick brown fox jumps over the lazy dog"));
		
//		//#17
//		LocalDate time = LocalDate.of(1996,Month.APRIL, 9);
//		System.out.println(es.getGigasecondDate(time));
	
		//#18
//		int set[] = {5,6,8};
//		System.out.println(es.getSumOfMultiples(150, set));

		//#19
		//System.out.println(es.isLuhnValid("046 454 286"));
	
		//#20
		System.out.println(es.solveWordProblem("What is 21 divided by -7?"));
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
