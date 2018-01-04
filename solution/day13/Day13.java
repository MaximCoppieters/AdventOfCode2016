package solution.day13;

import java.awt.Point;
import java.io.FileNotFoundException;

import skeleton.AdventOfCode;

public class Day13 extends AdventOfCode {

	
	
	public Day13() throws FileNotFoundException {
		super();
	}
	
	public Day13(String test) throws FileNotFoundException {
		super(" ");
	}
	

	@Override
	public void part1() {
		Maze labyrinth = new Maze(50,50);
		Point player = new Point(1,1);
		
		labyrinth.initialize();
		
		labyrinth.drawMaze();
	}

	@Override
	public void part2() {
			
	}

}
