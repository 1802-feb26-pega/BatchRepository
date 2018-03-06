package com.ex.problems;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactorialsTest {
	
	Factorials fact;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("After Class");
	}

	@Before
	public void setUp() throws Exception {
		fact = new Factorials();
		System.out.println("Created new factoria object");
	}

	@After
	public void tearDown() throws Exception {
		fact = null;
		System.out.println("cleard object in teardown()");
	}

	@Test
	public void test() { //5!
		int expected = 120;
		int actual = fact.factorial(5);
		assertEquals(actual,expected);
		assertFalse(fact.factorial(5) == 0);
		assertEquals(24, fact.factorial(4));				
	}
	public void test2() {
		
	}

}
