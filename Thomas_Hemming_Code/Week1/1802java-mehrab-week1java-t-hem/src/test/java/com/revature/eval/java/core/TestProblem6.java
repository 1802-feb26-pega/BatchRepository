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
	 * Question 19
	 ******************************************************************/
	@Test
	public void testThatAValidCanadianSocialInsuranceNumberIsIdentifiedAsValidV1() {
		assertTrue(evaluationService.isLuhnValid("046 454 286"));
	}

	@Test
	public void testThatAnInvalidCanadianSocialInsuranceNumberIsIdentifiedAsInvalid() {
		assertFalse(evaluationService.isLuhnValid("046 454 287"));
	}

	@Test
	public void testThatAnInvalidCreditCardIsIdentifiedAsInvalid() {
		assertFalse(evaluationService.isLuhnValid("8273 1232 7352 0569"));
	}

	@Test
	public void testThatAddingANonDigitCharacterToAValidStringInvalidatesTheString() {
		assertFalse(evaluationService.isLuhnValid("046a 454 286"));
	}

	@Test
	public void testThatStringContainingPunctuationIsInvalid() {
		assertFalse(evaluationService.isLuhnValid("055-444-285"));
	}
 }
