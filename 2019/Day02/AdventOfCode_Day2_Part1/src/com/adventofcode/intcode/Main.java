package com.adventofcode.intcode;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	private static final int ADD = 1;
	private static final int MULTIPLY = 2;
	private static final int END = 99;
	
	private static String readFileAsString(String fileName) throws Exception{ 
	    String data = new String(Files.readAllBytes(Paths.get(fileName))); 
	    return data; 
	}
	
	private static boolean executeIntcode(int[] intcode) {
		int nextInstruction = 0;
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
		return true;
	}
	
	private static void changeIntcodeValues(int[] intcode, int position, int value) {
		intcode[position] = value;
	}
	
	private static int[] getUserInput() {
		Scanner in = new Scanner(System.in);
		int[] input = new int[2];
		System.out.println("position: ");
		input[0] = in.nextInt(); 
		System.out.println("new Value: ");
		input[1] = in.nextInt(); 
		return input;
	}
	
	private static boolean askForInput() {
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to change a value in the Intcode? [Y/N]");
		while(true) {
			String input = in.nextLine();
			if(input.toLowerCase().equals("n")) {
				return false;
			}
			else if(input.toLowerCase().equals("y")) {
				return true;
			}
			else {
				System.out.println("ERROR. please type Y or N.");
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.exit(0);
		}
		
		//Read supplied file and convert into int array
		String[] intcodeString = readFileAsString(args[0]).split(",");
		int[] intcode = new int[intcodeString.length];
		for(int i=0; i<intcodeString.length; i++) {
			intcode[i] = Integer.parseInt(intcodeString[i]);
		}
		
		//Get User Input
		while(askForInput()) {
			int[] input = getUserInput();
			changeIntcodeValues(intcode, input[0], input[1]);
		}
		
		//execute intcode
		if(executeIntcode(intcode)) {
			System.out.println("Intcode executed successfully.");
			System.out.println("Result: ");
			for(int value : intcode) {
				int position = 0;
				System.out.println(position + ": " + value);
				position++;
			}
		}
		else {
			System.out.println("ERROR. Something went wrong. Could not execute intcode.");
		}
	}
}
