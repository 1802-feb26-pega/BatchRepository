package com.ex.problems;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactorialTest {

	Factorial fact;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("AfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Set Up - created new factorial object.");
		fact = new Factorial();
	}

	@After
	public void tearDown() throws Exception {
		fact = null;
		System.out.println("Cleared object and teardown");
	}

	@Test // Marks as a JUnit test.
	public void test() { // 5!
		int expected = 120;
		int actual = fact.factorial(5);
		assertEquals(actual, expected);	
	}
	
	@Test
	public void test2() {
		assertFalse(fact.factorial(5) == 0);
	}

	@Test
	public void test3() {
		assertEquals(24, fact.factorial(4));
	}
	
	@Test
	public void test4() {
		assertEquals(fact.factorial(0),1);
	}
}
