package com.neighborpil.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AssertTest {

	@Test
	void test() {
		boolean condition = true;
		assertEquals(true, condition);
		assertTrue(condition);
		assertFalse(false);
		assertNotNull(new Object());
		assertNull(null);
//		assertArrayEquals(expected, actual);
	}

}
