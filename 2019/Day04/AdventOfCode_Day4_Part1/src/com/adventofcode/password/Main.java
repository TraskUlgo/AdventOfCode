package com.adventofcode.password;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	private static Scanner in = new Scanner(System.in);
	private static int minimum = 0;
	private static int maximum = 0;
	
	public static void getUserInput() {
		System.out.println("Type in minimum of the range of numbers to check");
		try {
			minimum = in.nextInt();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		System.out.println("Type in maximum of the range of numbers to check");
		try {
			maximum = in.nextInt();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	
	public static boolean checkAdjacentDigits(int number) {
		List<Integer> digits = new ArrayList<>();
		while (number > 0) {
			digits.add(0, number % 10);
			number = number / 10;
		}
		for(int i = 0; i < digits.size()-1; i++) {
			if(digits.get(i) == digits.get(i+1)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkDigitsNeverDecrease(int number) {
		List<Integer> digits = new ArrayList<>();
		while (number > 0) {
			digits.add(0, number % 10);
			number = number / 10;
		}
		for(int i = 0; i < digits.size()-1; i++) {
			if(digits.get(i) > digits.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	
	public static int numberOfPasswordCandidates() {
		int numberOfPasswordCandidates = 0;
		for(int i = minimum; i <= maximum; i++) {
			if(checkAdjacentDigits(i) && checkDigitsNeverDecrease(i)) {
				numberOfPasswordCandidates ++;
			}
		}
		return numberOfPasswordCandidates;
	}
	
	public static void main(String[] args) {
		getUserInput();
		System.out.println("Number of password candidates = " + numberOfPasswordCandidates());
		in.close();
	}

}
