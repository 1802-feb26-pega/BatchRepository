package com.revature.eval.java.core;

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
		
		System.out.println(es.getScrabbleScore("f"));
		System.out.println(es.getScrabbleScore("zoo"));
		System.out.println(es.getScrabbleScore("street"));
		System.out.println(es.getScrabbleScore("quirky"));
		System.out.println(es.getScrabbleScore("OxyphenButazone"));
		
		
		
		
	}

}
