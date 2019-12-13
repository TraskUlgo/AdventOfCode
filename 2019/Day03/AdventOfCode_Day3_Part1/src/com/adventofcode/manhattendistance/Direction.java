package com.adventofcode.manhattendistance;

public enum Direction {
	UP("U"),
	DOWN("D"),
	LEFT("L"),
	RIGHT("R");
	
	private final String stringRepresentation;
	Direction(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
	
	public String stringRepresentation() {
		return stringRepresentation;
	}
}
