package com.adventofcode.manhattendistance;

public class MoveInstruction {
	private Direction direction;
	private int distance;
	
	public MoveInstruction(Direction direction, int distance) {
		this.direction = direction;
		this.distance = distance;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public int getDistance() {
		return distance;
	}

}
