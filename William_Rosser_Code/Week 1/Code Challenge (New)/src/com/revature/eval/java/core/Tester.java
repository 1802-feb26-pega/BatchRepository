package com.revature.eval.java.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
		
	}

}
