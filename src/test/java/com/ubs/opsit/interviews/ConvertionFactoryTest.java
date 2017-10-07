package com.ubs.opsit.interviews;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConvertionFactoryTest {

	ConvertionFactory convertionFactory;
	@Before
	public void setUp() throws Exception {
		convertionFactory = ConvertionFactory.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		convertionFactory = null;
	}

	@Test
	public void testConvert() {
		StringBuffer convert = convertionFactory.convert(24, Timers.SECONDS);
		Assert.assertEquals("Y"+"\r\n", convert.toString());

		convert = convertionFactory.convert(1, Timers.SECONDS);
		Assert.assertEquals("O"+"\r\n", convert.toString());

		convert = convertionFactory.convert(1, Timers.COREMINUTES);
		Assert.assertEquals("OOOOOOOOOOO"+"\r\n", convert.toString());

		convert = convertionFactory.convert(1, Timers.COREMINUTES);
		Assert.assertEquals("OOOOOOOOOOO"+"\r\n", convert.toString());
	}

	@Test
	public void testUsualLogic() {
		StringBuffer usualLogic = convertionFactory.usualLogic(24, Timers.COREMINUTES);
		Assert.assertEquals("YYRYOOOOOOO", usualLogic.toString());
		
		usualLogic = convertionFactory.usualLogic(50, Timers.COREMINUTES);
		Assert.assertEquals("YYRYYRYYRYO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(15, Timers.COREHOURS);
		Assert.assertEquals("RRRO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(15, Timers.RESIDUALHOURS);
		Assert.assertEquals("OOOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(22, Timers.RESIDUALHOURS);
		Assert.assertEquals("RROO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(22, Timers.RESIDUALMINUTES);
		Assert.assertEquals("YYOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(60, Timers.RESIDUALMINUTES);
		Assert.assertEquals("OOOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(0, Timers.RESIDUALMINUTES);
		Assert.assertEquals("OOOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(0, Timers.RESIDUALHOURS);
		Assert.assertEquals("OOOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(0, Timers.COREHOURS);
		Assert.assertEquals("OOOO", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(24, Timers.COREHOURS);
		Assert.assertEquals("RRRR", usualLogic.toString());

		usualLogic = convertionFactory.usualLogic(24, Timers.RESIDUALHOURS);
		Assert.assertEquals("RRRR", usualLogic.toString());
	}

	@Test
	public void testOffBars() {
		StringBuffer usualLogic = new StringBuffer();
		convertionFactory.offBars(Timers.COREMINUTES, 2, usualLogic);
		Assert.assertEquals("OOOOOOOOO", usualLogic.toString());
		
		usualLogic = new StringBuffer();
		convertionFactory.offBars(Timers.COREMINUTES, 10, usualLogic);
		Assert.assertEquals("O", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.offBars(Timers.RESIDUALHOURS, 4, usualLogic);
		Assert.assertEquals("", usualLogic.toString());

	}

	@Test
	public void testSpecialOnBars() {
		StringBuffer usualLogic = new StringBuffer();
		convertionFactory.specialOnBars(2, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("YY", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.specialOnBars(5, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("YYRYY", usualLogic.toString());

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSpecialOnBarsException() {
		StringBuffer usualLogic = new StringBuffer();
		convertionFactory.specialOnBars(2, usualLogic, Timers.COREHOURS);
		Assert.assertEquals("YY", usualLogic.toString());
	}
	
	@Test
	public void testOnBars() {
		StringBuffer usualLogic = new StringBuffer();
		convertionFactory.onBars(2, usualLogic, Timers.COREHOURS);
		Assert.assertEquals("RR", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(4, usualLogic, Timers.COREHOURS);
		Assert.assertEquals("RRRR", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(10, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("YYRYYRYYRY", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(5, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("YYRYY", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(1, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("Y", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(0, usualLogic, Timers.COREMINUTES);
		Assert.assertEquals("", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(0, usualLogic, Timers.RESIDUALHOURS);
		Assert.assertEquals("", usualLogic.toString());
		
		usualLogic = new StringBuffer();
		convertionFactory.onBars(3, usualLogic, Timers.RESIDUALHOURS);
		Assert.assertEquals("RRR", usualLogic.toString());

		usualLogic = new StringBuffer();
		convertionFactory.onBars(3, usualLogic, Timers.RESIDUALMINUTES);
		Assert.assertEquals("YYY", usualLogic.toString());

	}

	@Test(expected = RuntimeException.class)
	public void testExceptionOnBars() {
		StringBuffer usualLogic = new StringBuffer();
		convertionFactory.onBars(7, usualLogic, Timers.RESIDUALMINUTES);		
	}

		
}
