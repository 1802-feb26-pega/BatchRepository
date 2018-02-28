package com.revature.eval;

public class Main
{
	private static final EvaluationService evaluationService = new EvaluationService();
	
	public static void main(String[] args)
	{
		// Test Question One
		/*String test = evaluationService.reverse("robot");
		
		System.out.println(test);*/
		
		// Test Question Two
		/*String testPhrase = evaluationService.acronym("National Aeronautics Space Administration");
		
		System.out.println(testPhrase);*/
		
		// Test Question Three
		/*EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
		
		System.out.println(triangle.isEquilateral());
		System.out.println(triangle.isIsosceles());
		System.out.println(triangle.isScalene());*/
		
		// Test Question Four
		int test = evaluationService.getScrabbleScore("f");
		
		System.out.println(test);
	}

}
