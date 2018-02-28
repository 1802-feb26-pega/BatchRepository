package com.revature.eval.java.core;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestProblem3 {

	private static final EvaluationService evaluationService = new EvaluationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/*******************************************************************
	 * Question 3
	 ******************************************************************/

	@Test
	public void trianglesWithNoEqualSidesAreNotEquilateral() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(5, 4, 6);
		assertFalse(triangle.isEquilateral());
	}

	@Test
	public void verySmallTrianglesCanBeEquilateral() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.5, 0.5);
		assertTrue(triangle.isEquilateral());
	}

	@Test
	public void isoscelesTrianglesMustHaveAtLeastTwoEqualSides() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(2, 3, 4);
		assertFalse(triangle.isIsosceles());
	}

	@Test
	public void verySmallTrianglesCanBeIsosceles() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.4, 0.5);
		assertTrue(triangle.isIsosceles());
	}

	@Test
	public void trianglesWithAllSidesEqualAreNotScalene() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(4, 4, 4);
		assertFalse(triangle.isScalene());
	}

	@Test
	public void verySmallTrianglesCanBeScalene() {
		EvaluationService.Triangle triangle = new EvaluationService.Triangle(0.5, 0.4, 0.6);
		assertTrue(triangle.isScalene());
	}
}