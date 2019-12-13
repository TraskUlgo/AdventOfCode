package com.adventofcode.stepstointersection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Cable {
	public static final Point ORIGIN = new Point(0, 0);
	private List<Point> cablePath;
	
	public Cable() {
		cablePath = new ArrayList<>();
		cablePath.add(ORIGIN);
	}
	
	public List<Point> getCablePath() {
		return cablePath;
	}
	
	private List<Point> calculatePointsToDestination(Point origin, MoveInstruction instruction) {
		int x = 0;
		int y = 0;
		List<Point> pointsToDestination = new ArrayList<>();
		
		switch(instruction.getDirection()) {
		case UP : y = instruction.getDistance();
		break;
		case DOWN : y = -instruction.getDistance();
		break;
		case RIGHT : x = instruction.getDistance();
		break;
		case LEFT : x = -instruction.getDistance();
		break;
		}
		
		if(x == 0) {
			if(y > 0) {
				for(int i = 1; i <= y; i++) {
					pointsToDestination.add(new Point((int)origin.getX(), (int)origin.getY()+i));
				}
			}
			else {
				for(int i = -1; i >= y; i--) {
					pointsToDestination.add(new Point((int)origin.getX(), (int)origin.getY()+i));
				}
			}
		}
		else {
			if(x > 0) {
				for(int i = 1; i <= x; i++) {
					pointsToDestination.add(new Point((int)origin.getX()+i, (int)origin.getY()));
				}
			}
			else {
				for(int i = -1; i >= x; i--) {
					pointsToDestination.add(new Point((int)origin.getX()+i, (int)origin.getY()));
				}
			}
		}
		return pointsToDestination;
	}
	
	public void calculateCablePath(List<MoveInstruction> instructions) {
		for(MoveInstruction instruction : instructions) {
			cablePath.addAll(calculatePointsToDestination(cablePath.get(cablePath.size()-1), instruction));
		}
	}
}
