package com.ubs.opsit.interviews;

public enum Timers {

	COREHOURS("CH", 5, 4, true, LampState.RED), 
	RESIDUALHOURS("RH", 5, 4, false, LampState.RED), 
	COREMINUTES("CM", 5, 11, true), 
	RESIDUALMINUTES("RM", 5, 4, false, LampState.YELLOW), 
	SECONDS("S");

	String timeLiteral;
	int weightAge;
	int bars;
	boolean isDivide;
	LampState stateOn;

	private Timers(String timeLiteral) {
		this.timeLiteral = timeLiteral;
	}

	private Timers(String timeLiteral, int weightAge, int bars, boolean isDivide) {
		this.timeLiteral = timeLiteral;
		this.weightAge = weightAge;
		this.bars = bars;
		this.isDivide = isDivide;
	}

	private Timers(String timeLiteral, int weightAge, int bars, boolean isDivide, LampState stateOn) {
		this.timeLiteral = timeLiteral;
		this.weightAge = weightAge;
		this.bars = bars;
		this.isDivide = isDivide;
		this.stateOn = stateOn;
	}

	public int[] getQuarters(Timers timer) {
		if (timer.equals(COREMINUTES)) {
			return new int[] { 3, 6, 9 };
		}
		return new int[] {};
	}
}
