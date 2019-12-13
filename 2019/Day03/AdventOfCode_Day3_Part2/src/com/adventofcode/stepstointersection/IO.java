package com.adventofcode.stepstointersection;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IO {
	private static String readFileAsString(String fileName) throws Exception{ 
	    String data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	}
	
	public static List<List<MoveInstruction>> readInstructions(String fileName) throws Exception  {
		List<List<MoveInstruction>> instructions = new ArrayList<>();
		String data = readFileAsString(fileName);
		String[] subStrings = data.split("\\r?\\n");
		
		for(String string : subStrings) {
			String[] stringInstructions = string.split(",");
			List<MoveInstruction> cableMoveInstructions = new ArrayList<>();
			for(String instruction : stringInstructions) {
				Direction direction;
				switch(instruction.substring(0, 1)) {
				case "U" : direction = Direction.UP;
				break;
				case "D" : direction = Direction.DOWN;
				break;
				case "R" : direction = Direction.RIGHT;
				break;
				case "L" : direction = Direction.LEFT;
				break;
				default : direction = Direction.UP;
				}
				int distance = Integer.parseInt(instruction.substring(1));
				cableMoveInstructions.add(new MoveInstruction(direction, distance));
			}
			instructions.add(cableMoveInstructions);
		}
		return instructions;
	}

}
