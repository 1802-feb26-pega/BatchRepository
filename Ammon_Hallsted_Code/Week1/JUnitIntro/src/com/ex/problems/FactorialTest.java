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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		fact = new Factorial();
		System.out.println("Created new Factorial object.");
	}

	@After
	public void tearDown() throws Exception {
		fact = null;
		System.out.println("Cleared object in teardown()");
		
	}

	@Test
	public void test() {//using 5
		int expected = 120;
		int actual = fact.factorial(5);
		assertEquals(actual,expected);
		assertFalse(fact.factorial(5) == 0);
		}
}
