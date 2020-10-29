package edu.uncc.rcavana1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * AStarSearch program main class.
 * 
 * @author Drew Cavanaugh (rcavana1@uncc.edu)
 * @since 10/29/2020
 */
public final class AStarSearch {
	public final static int DEFAULT_GRID_SIZE = 15;
	
	private static Agent _agent;
	private static World _world;
	private static Scanner _scan;
	
	/**
	 * The entry point for the program.
	 * @param args Command line arguments. Accepts 0 or 1 int argument specifying board size.
	 */
	public static void main(String[] args) {
		int size = DEFAULT_GRID_SIZE;
		
		if (args.length == 1) {
			try {
				size = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				System.out.println(String.format("Runtime error! Did not understand argument: %s\r\n", args[0]));
				exit(true);
			}
		} else if (args.length > 1) {
			System.out.println("Runtime error! Too many arguments specified.\r\n");
			exit(true);
		}
		System.out.println("A-Star Search AI program");
		System.out.println("by Drew Cavanaugh (rcavana1@uncc.edu)");
		System.out.println(String.format("World size: %dx%d\r\n", size, size));
		_scan = new Scanner(System.in);
		_world = new World(size);
		_agent = new Agent(_world);
		displayWorld();
		inputStartingPosition();
		inputEndingPosition();
		_agent.findPath();
		displayPathToGoal();
		displayWorld();
		exit(false);
	}
	
	private static void displayWorld() {
		System.out.println("World layout:\t(X indicates non-traversable tile)");
		System.out.println(_world.toString());
	}
	
	private static void inputStartingPosition() {
		boolean error = false;
		int _startRow;
		int _startCol;
		
		do {
			System.out.println("Enter starting position...");
			try {
				System.out.print(String.format("\tRow (1-%d): ", _world.getSize()));
				_startRow = _scan.nextInt();
				System.out.print(String.format("\tColumn (1-%d): ", _world.getSize()));
				_startCol = _scan.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Input error, please try again.\r\n");
				error = true;
				continue;
			}
			try {
				_world.setStartingPosition(_startRow - 1, _startCol - 1);
			} catch (IllegalStateException ex) {
				System.out.println(ex.getMessage());
				error = true;
			}
		} while (error);
	}
	
	private static void inputEndingPosition() {
		boolean error = false;
		int _endRow;
		int _endCol;
		
		do {
			System.out.println("Enter ending position...");
			try {
				System.out.print(String.format("\tRow (1-%d): ", _world.getSize()));
				_endRow = _scan.nextInt();
				System.out.print(String.format("\tColumn (1-%d): ", _world.getSize()));
				_endCol = _scan.nextInt();
			} catch (InputMismatchException ex) {
				System.out.println("Input error, please try again.\r\n");
				error = true;
				continue;
			}
			try {
				_world.setEndingPosition(_endRow - 1, _endCol - 1);
			} catch (IllegalStateException ex) {
				System.out.println(ex.getMessage());
				error = true;
			}
		} while (error);
	}
	
	private static void displayPathToGoal() {
		if (_agent.hasShortestPath()) {
			System.out.println("Path to the ending position:");
			for (Node node : _agent.getShortestPath()) {
				System.out.println(String.format("\t%s", node.toString()));
			}
		}
	}
	
	/**
	 * Exits the program.
	 * If an error is specified, displays the usage prompt.
	 * @param isError Whether this program is exiting in an error state.
	 */
	private static void exit(boolean isError) {
		if (isError) {
			System.out.println("Usage: java AStarSearch [worldSize]");
			System.out.println("\tWhere [worldSize] is the optional size of the world. Default is 15.\r\n");
			System.exit(-1);
		} else {
			System.exit(0);
		}
	}
}
