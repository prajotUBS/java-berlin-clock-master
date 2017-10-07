package com.ubs.opsit.interviews;

public abstract class BerlinTemplate {

	public String createBerlinClockTime(String localDateTime){
		ConvertionFactory convertor = ConvertionFactory.getInstance();

		//Step 1. Get Second
		StringBuffer berlinClockTime = convertor.convert(getSeconds(localDateTime), Timers.SECONDS);
		//Step 2. Append Core Hours
		berlinClockTime.append(convertor.convert(getHours(localDateTime), Timers.COREHOURS));
		//Step 3. Append Residual Hours
		berlinClockTime.append(convertor.convert(getHours(localDateTime), Timers.RESIDUALHOURS));
		//Step 4. Append Core Minutes
		berlinClockTime.append(convertor.convert(getMinutes(localDateTime), Timers.COREMINUTES));
		//Step 5. Append Residual Minutes
		berlinClockTime.append(convertor.convert(getMinutes(localDateTime), Timers.RESIDUALMINUTES));
		
		return berlinClockTime.toString();

		
	}

	protected Integer getSeconds(String localDateTime) {
		return Integer.valueOf(localDateTime.substring(6, 8));
	}
	protected Integer getMinutes(String localDateTime) {
		return Integer.valueOf(localDateTime.substring(3, 5));
	}
	protected Integer getHours(String localDateTime) {
		return Integer.valueOf(localDateTime.substring(0, 2));
	}
}
