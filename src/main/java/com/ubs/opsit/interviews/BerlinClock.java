package com.ubs.opsit.interviews;

public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		BerlinTimeBuilder berlinTimeBuilder = new BerlinTimeBuilder();
		return berlinTimeBuilder.build(aTime);
	}

}
