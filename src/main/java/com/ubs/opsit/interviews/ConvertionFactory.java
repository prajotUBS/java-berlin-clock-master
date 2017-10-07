package com.ubs.opsit.interviews;

import java.sql.Time;

public class ConvertionFactory {

	
	private ConvertionFactory() {
		super();
	}
	
	private static final ConvertionFactory convertionFactory = new ConvertionFactory();

	public static final ConvertionFactory getInstance() {
		return convertionFactory;
	}

	public StringBuffer convert(int timeLiteral, Timers timer) {
		StringBuffer outBuf = new StringBuffer();
		if(timer.equals(Timers.SECONDS)){
			outBuf = new StringBuffer((timeLiteral%2 == 0 ? LampState.YELLOW.stateRep : LampState.OFF.stateRep));
		}else{
			outBuf = usualLogic(timeLiteral, timer);
		}
		/*if(Timers.COREMINUTES.equals(timer)){
			outBuf.append(" ");
		}*/
		if(!Timers.RESIDUALMINUTES.equals(timer)){
			return outBuf.append("\r\n");
		}
		
		return outBuf;
	}

	protected StringBuffer usualLogic(int timeLiteral, Timers timer) {
		
		int timeLiteralBreak = timer.isDivide ? timeLiteral / timer.weightAge : timeLiteral % timer.weightAge;

		StringBuffer timeLiteralRep = new StringBuffer();
		onBars(timeLiteralBreak, timeLiteralRep, timer);
		offBars(timer, timeLiteralBreak, timeLiteralRep);

		if(timeLiteralRep.length()>timer.bars){
			throw new RuntimeException("Unsupported for timer configuration " + timer);
		}
		return timeLiteralRep;
	}

	protected void offBars(Timers timer, int timeLiteralBreak, StringBuffer timeLiteralRep) {
		for (int i = timeLiteralBreak; i < timer.bars; i++) {
			timeLiteralRep.append(LampState.OFF.stateRep);
		}
		
		if(timeLiteralRep.length()>timer.bars){
			throw new RuntimeException("Unsupported for timer configuration " + timer);
		}
	}

	protected void specialOnBars(int timeLiteralBreak, StringBuffer timeLiteralRep, Timers timer) {
		if(!Timers.COREMINUTES.equals(timer)){
			throw new UnsupportedOperationException("UnExpected call to method");
		}
		int[] quarters = timer.getQuarters(timer);
		boolean flag = false;
		for (int i = 0; i < timeLiteralBreak; i++) {
			for(int quater : quarters){
				if(i == quater-1){
					timeLiteralRep.append(LampState.RED.stateRep);
					flag = true;
					break;
				}
			}
			if(!flag)
				timeLiteralRep.append(LampState.YELLOW.stateRep);
			else{
				flag = false;
			}
		}
		if(timeLiteralRep.length()>timer.bars){
			throw new RuntimeException("Unsupported for timer configuration " + timer);
		}
	}

	protected void onBars(int timeLiteralBreak, StringBuffer timeLiteralRep, Timers timer) {
		if (timer.equals(Timers.COREMINUTES))
		{
			specialOnBars(timeLiteralBreak, timeLiteralRep, timer);
			return;
		}
		for (int i = 0; i < timeLiteralBreak; i++) {
			timeLiteralRep.append(timer.stateOn.stateRep);
		}

		if(timeLiteralRep.length()>timer.bars){
			throw new RuntimeException("Unsupported for timer configuration " + timer);
		}
		}

}
