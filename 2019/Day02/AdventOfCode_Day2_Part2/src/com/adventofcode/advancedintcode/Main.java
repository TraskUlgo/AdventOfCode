package com.adventofcode.advancedintcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	private static Scanner in = new Scanner(System.in);
	
	private static final int ADD = 1;
	private static final int MULTIPLY = 2;
	private static final int END = 99;
	private static final int NOUNADRESS = 1;
	private static final int VERBADRESS = 2;
	
	private static int desiredOutput = 0;
	private static int[] originalIntcode;
	
	private static String readFileAsString(String fileName) { 
		try {
			String data = new String(Files.readAllBytes(Paths.get(fileName))); 
		    return data; 
		}
	    catch(Exception e) {
	    	System.out.println(e.getMessage());
	    	return null;
	    }
	}
	
	private static boolean executeIntcode(int[] intcode) {
		int nextInstruction = 0;
		try {
			while(intcode[nextInstruction] != END) {
				if(intcode[nextInstruction] == ADD) {
					intcode[intcode[nextInstruction+3]] = intcode[intcode[nextInstruction+1]] + intcode[intcode[nextInstruction+2]];
				}
				else if(intcode[nextInstruction] == MULTIPLY) {
					intcode[intcode[nextInstruction+3]] = intcode[intcode[nextInstruction+1]] * intcode[intcode[nextInstruction+2]];
				}
				else {
					return false;
				}
				nextInstruction += 4;
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	private static void changeIntcodeValues(int[] intcode, int position, int value) {
		try {
			intcode[position] = value;
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static int getUserInput() {
		while(true) {
			try {
				System.out.println("Desired output: ");
				return in.nextInt();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.exit(0);
		}
		
		//Read supplied file and convert into int array
		try {
			String[] intcodeString = readFileAsString(args[0]).split(",");
			int[] intcode = new int[intcodeString.length];
			for(int i=0; i<intcodeString.length; i++) {
				intcode[i] = Integer.parseInt(intcodeString[i]);
				originalIntcode = new int[intcode.length];
				System.arraycopy(intcode, 0, originalIntcode, 0, intcode.length);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Could not produce intcode array from input.");
			System.exit(0);
		}
		
		
		//Get User Input for the desired output
		desiredOutput = getUserInput();
		
		//execute intcode
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				int[] intcode = new int[originalIntcode.length];
				System.arraycopy(originalIntcode, 0, intcode, 0, originalIntcode.length);
				changeIntcodeValues(intcode, NOUNADRESS, i);
				changeIntcodeValues(intcode, VERBADRESS, j);
				if(executeIntcode(intcode)) {
					if(intcode[0] == desiredOutput) {
						System.out.println("SUCCESS. Desired output has been achieved.");
						System.out.println("Noun: " + i);
						System.out.println("Verb: " + j);
						int result = 100 * i + j;
						System.out.println("100 * noun + verb = " + result);
						in.close();
						System.exit(0);
					}
				}
			}
		}
		System.out.println("FAIL. Desired output could not be produced.");
		in.close();
		System.exit(0);
	}
}
