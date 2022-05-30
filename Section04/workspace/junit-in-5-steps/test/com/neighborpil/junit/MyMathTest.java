package com.neighborpil.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyMathTest {

	MyMath myMath = new MyMath();
	
	@BeforeEach
	public void before() {
		System.out.println("Before");
	}
	
	@AfterEach
	public void after() {
		System.out.println("After");
	}
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("BeforeAll");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("AfterAll");
	}
	
	// MyMath.sum
	// 1, 2, 3 => 6
	@Test
	public void sum_with3numbers() {
		// Check that the result is 6
		// check result == 6
		assertEquals(6, myMath.sum(new int[] {1,2,3}));
		System.out.println(myMath.sum(new int[] {1,2,3}));
	}
	
	@Test
	public void sum_with1numbers() {
		assertEquals(3, myMath.sum(new int[] {3}));
		System.out.println(myMath.sum(new int[] {3}));
	}


}
