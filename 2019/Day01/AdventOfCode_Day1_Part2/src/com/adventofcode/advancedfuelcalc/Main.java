package com.adventofcode.advancedfuelcalc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
	
	static int calculateFuelForMass(int mass) {
		return mass/3-2;
	}
	
	static int calculateTotalFuelForModule(int moduleMass) {
		int fuelMass = calculateFuelForMass(moduleMass);
		int totalFuel = fuelMass;
		while(calculateFuelForMass(fuelMass) > 0) {
			fuelMass = calculateFuelForMass(fuelMass);
			totalFuel += fuelMass;
		}
		return totalFuel;
	}
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.exit(0);
		}
		
		String fileName = args[0];
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			System.out.println(
					stream
					.flatMapToInt(x -> IntStream.of(Integer.parseInt(x)))
					.map(x -> calculateTotalFuelForModule(x))
					.sum()
			);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
