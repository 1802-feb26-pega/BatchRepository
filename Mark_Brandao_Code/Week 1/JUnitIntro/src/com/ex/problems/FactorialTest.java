package com.ex.problems;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FactorialTest {

	
	Factorial ff;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("before class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("after class");
	}

	@Before
	public void setUp() throws Exception {
		ff = new Factorial();
		System.out.println("created new Factorial object");
	}

	@After
	public void tearDown() throws Exception {
		ff = null;
		System.out.println("cleared Factorial object in teardown");
	}

	@Test
	public void test() { // 5!
		int expected = 120;
		int actual = ff.factorial(5);
		assertEquals(actual, expected);
		assertFalse(ff.factorial(5) == 0);
		assertEquals(24, ff.factorial(4));
		assertEquals(720, ff.factorial(6));
	}
	
	@Test
	public void test2() {
		assertTrue(ff.factorial(0) == 1);
		assertTrue(ff.factorial(1) == 1);
	}

}
