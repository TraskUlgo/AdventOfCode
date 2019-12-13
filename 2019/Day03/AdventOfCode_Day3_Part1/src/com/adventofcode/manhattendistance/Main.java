package com.adventofcode.manhattendistance;

public class Main {
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.exit(0);
		}
		
		Controller controller = new Controller();
		controller.run(args[0]);
	}

}
