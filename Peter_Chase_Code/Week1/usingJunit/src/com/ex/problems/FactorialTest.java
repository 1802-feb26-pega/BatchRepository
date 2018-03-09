package com.ex.problems;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactorialTest {
	private Factorial fact = new Factorial();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Created new factorial object");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Cleared object in teardown");
	}

	@Test
	public void testFactorial() {
		int expected = 120;
		int actual = fact.factorial(5);
		assertEquals(24, fact.factorial(4));
	}

	@Test
	public void testFactorial2() {
		int expected = 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1;
		int actual = fact.factorial(8);
		assertTrue(expected == actual);
	}
}
