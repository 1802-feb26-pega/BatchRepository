package com.revature.eval.java.core;

import com.revature.eval.java.core.EvaluationService.Triangle;

public class Test {

	public static void main(String[] args) {
		
		Triangle triangle = new EvaluationService.Triangle(3, 3, 3);

		System.out.println(" " + triangle.isEquilateral());
		
	}
	
	
}
