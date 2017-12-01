package com.sitv.skyshop.massagechair.provider;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	private String name = "";

	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);

		name = "123";

		assertTrue(getName(), getName().equals(resetName()));
		assertEquals(getName(), "234");
	}

	public String resetName() {
		this.name = "234";
		return name;
	}

	public String getName() {
		return name;
	}
}
