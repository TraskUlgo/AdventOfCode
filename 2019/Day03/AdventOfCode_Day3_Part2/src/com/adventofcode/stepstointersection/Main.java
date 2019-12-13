package com.adventofcode.stepstointersection;

import com.adventofcode.stepstointersection.Controller;

public class Main {
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			System.exit(0);
		}
		
		Controller controller = new Controller();
		controller.run(args[0]);
	}

}
