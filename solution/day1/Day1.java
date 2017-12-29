package solution.day1;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skeleton.AdventOfCode;

public class Day1 extends AdventOfCode {
	
	private static List<Character> directions = new ArrayList<>();
	private int x;
	private int y;
	private boolean isLeft;
	private int distance;
	private String[] instructions;
	private char direction;
	private Location currentLocation;
	private Map<Location, Integer> coordinationsVisited = new HashMap<>();
	private boolean found;
	private Location alreadyVisited;
	
	static {
		directions.add('W');
		directions.add('N');
		directions.add('E');
		directions.add('S');
	}
	
	public Day1() throws FileNotFoundException {
		super();
	}
	
	public Day1(String test) throws FileNotFoundException {
		super("test");
	}
	
	
	public void core() {
		instructions = getInstructions();
		direction = 'N';
		x=0;
		y=0;
		found = false;
		
		for(String instruction : instructions) {
			isLeft = checkIfLeftOrRight(instruction.charAt(0));
			direction = changeDirection(direction, isLeft);
			distance = getDistance(instruction);
			
			moveDirection();
						
		}
	}
	
	@Override
	public void part1(){
		core();
		
		int blocksAway = calculateManhattanDistance(x, y);
		System.out.println(blocksAway);
	}

	public int calculateManhattanDistance(int x, int y) {
		return Math.abs(x-0) + Math.abs(y-0);
	}

	@Override
	public void part2(){
		core();
		
		int blocksAway = calculateManhattanDistance(alreadyVisited.getX(), alreadyVisited.getY());
		
		System.out.println(blocksAway);
	}
	
	public String[] getInstructions() {
		return this.getInput().replace(" ", "").split(",");
	}
	
	public char changeDirection(char direction, boolean isLeft) {
		int posInList = 0;
		
		for(int i=0; i < directions.size(); i++) {
			if(directions.get(i) == direction) {
				posInList = i;
			}
		}
		if(isLeft) {
			Collections.rotate(directions, 1);
			return directions.get(posInList);
		} else {
			Collections.rotate(directions, -1);
			return directions.get(posInList);
		}
	}
	
	public boolean checkIfLeftOrRight(char directionLetter) {
		if(directionLetter == 'L') {
			return true;
		}
		return false;
	}
	
	public int getDistance(String instruction) {
		return Integer.parseInt("" + instruction.charAt(1));
	}
	
	public void findFirstVisitedTwice() {
		if(coordinationsVisited.containsKey(currentLocation) && found == false) {
			alreadyVisited = currentLocation;
			found = true;
		} else {
			coordinationsVisited.put(currentLocation, 1);
		}
	}
	
	public void moveDirection() {
		switch(direction) {
		case 'N':
			for(int step=0; step < distance; step++) {
				y++;
				currentLocation = new Location(x, y);
				findFirstVisitedTwice();
			}
			break;
		case 'E':
			for(int step=0; step < distance; step++) {
				x++;
				currentLocation = new Location(x, y);
				findFirstVisitedTwice();
			}
			break;
		case 'S':
			for(int step=0; step < distance; step++) {
				y--;
				currentLocation = new Location(x, y);
				findFirstVisitedTwice();
			}
			break;
		case 'W':
			for(int step=0; step < distance; step++) {
				x--;
				currentLocation = new Location(x, y);
				findFirstVisitedTwice();
			}
		}
	}
	
}
