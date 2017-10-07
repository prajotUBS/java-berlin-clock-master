package com.ubs.opsit.interviews;

import java.time.format.DateTimeParseException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BerlinTimeBuilderTest {

	BerlinTimeBuilder berlinTimeBuilder;
	@Before
	public void setUp() throws Exception {
		berlinTimeBuilder = new BerlinTimeBuilder();
	}

	@After
	public void tearDown() throws Exception {
		berlinTimeBuilder = null;
	}

	@Test(expected = DateTimeParseException.class)
	public void testBuildException() {
		berlinTimeBuilder.build("23:23:96");
	}

	@Test(expected = DateTimeParseException.class)
	public void testBuildException2() {
		berlinTimeBuilder.build("25:23:00");
	}

	@Test(expected = DateTimeParseException.class)
	public void testBuildException3() {
		berlinTimeBuilder.build("11:73:00");
	}

	@Test
	public void testBuild() {
		String output = berlinTimeBuilder.build("20:53:01");
		Assert.assertEquals("O\r\nRRRR\r\nOOOO\r\nYYRYYRYYRYO\r\nYYYO", output);
		
		output = berlinTimeBuilder.build("00:53:20");
		Assert.assertEquals("Y\r\nOOOO\r\nOOOO\r\nYYRYYRYYRYO\r\nYYYO", output);

		System.out.println("============");
		output = berlinTimeBuilder.build("00:00:00");
		Assert.assertEquals("Y\r\nOOOO\r\nOOOO\r\nOOOOOOOOOOO\r\nOOOO", output);
		
		System.out.println("============");
		output = berlinTimeBuilder.build("24:00:00");
		Assert.assertEquals("Y\r\nRRRR\r\nRRRR\r\nOOOOOOOOOOO\r\nOOOO", output);
		
		System.out.println("============");
		output = berlinTimeBuilder.build("13:17:01");
		Assert.assertEquals("O\r\nRROO\r\nRRRO\r\nYYROOOOOOOO\r\nYYOO", output);
		
	}
}
