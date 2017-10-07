package com.ubs.opsit.interviews;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BerlinTimeBuilder extends BerlinTemplate{

	
	
	public static DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern("kk:mm:ss");
	public String build(String time) {
		LocalTime.parse(time, DATETIMEFORMATTER);
		String output = createBerlinClockTime(time);
		//System.out.println(output);
		return output;
	}
	
	
}
