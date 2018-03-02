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

public class TestProblem6 {

	private static final EvaluationService evaluationService = new EvaluationService();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	/*******************************************************************
	 * Question 11
	 ******************************************************************/

	@Test
	public void rotateSingleCharacterWithWrapAround() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(13);
		assertEquals("a", rotationalCipher.rotate("n"));
	}

	@Test
	public void rotateCapitalLetters() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(5);
		assertEquals("TRL", rotationalCipher.rotate("OMG"));
	}

	@Test
	public void rotateNumbers() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(4);
		assertEquals("Xiwxmrk 1 2 3 xiwxmrk", rotationalCipher.rotate("Testing 1 2 3 testing"));
	}

	@Test
	public void rotatePunctuation() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(21);
		assertEquals("Gzo'n zvo, Bmviyhv!", rotationalCipher.rotate("Let's eat, Grandma!"));
	}

	@Test
	public void rotateAllLetters() {
		EvaluationService.RotationalCipher rotationalCipher = new EvaluationService.RotationalCipher(13);
		assertEquals("The quick brown fox jumps over the lazy dog.",
				rotationalCipher.rotate("Gur dhvpx oebja sbk whzcf bire gur ynml qbt."));
	}
}
