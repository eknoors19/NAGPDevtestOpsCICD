package com.redbus.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;

import java.io.IOException;

import org.testng.annotations.Test;

import com.redbus.base.BaseClass;

public class CalculatorTest extends BaseClass{

	public CalculatorTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	@Test
	public void testAdd() {
		int total = 8;
		int sum = add(4, 4);
		assertEquals(sum, total);
		System.out.println("Add test passed");
	}

	@Test
	public void testFailedAdd() {
		int total = 9;
		int sum = add(10, 5);
		assertNotSame(sum, total);
		System.out.println("Add test failed");
	}

	@Test
	public void testSub() {
		int total = 0;
		int sub = sub(4, 4);
		assertEquals(sub, total);
		System.out.println("Sub test passed");
	}
}
