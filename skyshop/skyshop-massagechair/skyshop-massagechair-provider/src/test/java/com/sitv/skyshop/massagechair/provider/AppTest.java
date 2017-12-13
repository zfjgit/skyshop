package com.sitv.skyshop.massagechair.provider;

import com.sitv.skyshop.common.utils.Utils;

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

		String s = Utils.digest("201712150001111015A2017-12-15 11:1700", "SHA-1");
		System.out.println(s);

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
