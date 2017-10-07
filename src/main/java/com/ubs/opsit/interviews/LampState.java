package com.ubs.opsit.interviews;

public enum LampState {

	RED("R"),
	YELLOW("Y"),
	OFF("O");
	
	String stateRep;

	private LampState(String stateRep) {
		this.stateRep = stateRep;
	}
	
	public String getStateRep() {
		return stateRep;
	}
}
