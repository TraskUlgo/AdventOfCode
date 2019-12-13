package com.adventofcode.stepstointersection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	private List<List<MoveInstruction>> instructions;
	private List<Cable> cables;
	
	public Controller() {
		instructions = new ArrayList<>();
		cables = new ArrayList<>();
	}
	
	private List<Point> getIntersections(Cable cableA, Cable cableB) {
		List<Point> intersections = new ArrayList<>();
		intersections.addAll(cableA.getCablePath());
		intersections.retainAll(cableB.getCablePath());
		return intersections;
	}
	
	private int calculateManhattenDistance(Point origin, Point destination) {
		return Math.abs((int)origin.getX() - (int)destination.getX()) + Math.abs((int)origin.getY() - (int)destination.getY());
	}
	
	private int calcFewestSteps(Cable cableA, Cable cableB) {
		List<Point> intersections = getIntersections(cableA, cableB);
		List<Integer> steps = new ArrayList<>();
		for(Point intersection : intersections) {
			steps.add(cableA.getCablePath().indexOf(intersection) + cableB.getCablePath().indexOf(intersection));
		}
		
		Integer originSteps = 0;
		steps.remove(originSteps);
		int shortestSteps = steps.get(0);
		for(int i : steps) {
			shortestSteps = Math.min(shortestSteps, i);
		}
		
		return shortestSteps;	
	}
	
	public void run(String fileName) throws Exception {
		
		//Create cables from red instructions
		instructions = IO.readInstructions(fileName);
		for(List<MoveInstruction> cableInstructions : instructions) {
			Cable cable = new Cable();
			cable.calculateCablePath(cableInstructions);
			cables.add(cable);
		}
		
		//calculate Manhatten distance between first 2 cables
		List<Point> intersections = getIntersections(cables.get(0), cables.get(1));
		List<Integer> manhattenDistances = new ArrayList<>();
		for(Point intersection : intersections) {
			manhattenDistances.add(calculateManhattenDistance(Cable.ORIGIN, intersection));
		}
		Integer originDistance = 0;
		manhattenDistances.remove(originDistance);
		int shortestMDistance = manhattenDistances.get(0);
		for(int i : manhattenDistances) {
			shortestMDistance = Math.min(shortestMDistance, i);
		}
		
		int shortestSteps = calcFewestSteps(cables.get(0), cables.get(1));
		
		System.out.println("Shortest cable intersection " + shortestMDistance + " away from origin.");
		System.out.println("Shortest steps to intersection = " + shortestSteps);
	}

}
