package solution.day2;

import java.io.FileNotFoundException;

import skeleton.AdventOfCode;

public class Day2 extends AdventOfCode {
	private int x=0;
	private int y=0;
	
	
	public Day2() throws FileNotFoundException {
		super();
	}
	
	public Day2(String test) throws FileNotFoundException {
		super("test");
	}

	@Override
	public void part1() {
		int[][] keyPad = {
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		String lines[] = this.getInput().split("\\r?\\n");
		char[] instructions;
		int xBeforeMovement;
		int yBeforeMovement;
		StringBuilder code = new StringBuilder();
		x = 1;
		y = 1;	
		
		for(String line : lines) {
			instructions = line.toCharArray();
				for(char instruction : instructions) {
					xBeforeMovement = x;
					yBeforeMovement = y;
					
					moveInDirection(instruction);
					
					if(isOutOfBounds(keyPad)) {
						x = xBeforeMovement;
						y = yBeforeMovement;
					}
				}
			code.append(keyPad[y][x]);
		}
		
		System.out.println(code);
	}

	@Override
	public void part2() {
		
		
	}
	
	public void moveInDirection(char instruction) {
		switch(instruction) {
		case 'U':
			y--;
			break;
		case 'L':
			x--;
			break;
		case 'D':
			y++;
			break;
		case 'R':
			x++;
			break;
		}
	}
	
	public boolean isOutOfBounds(char[][]keyPad) {
		if(x >= keyPad.length || y >= keyPad.length) {
			return true;
		}
		if(x < 0 || y < 0) {
			return true;
		}
		return false;
	}
	
	public boolean isOutOfBounds(int[][]keyPad) {
		if(x >= keyPad.length || y >= keyPad.length) {
			return true;
		}
		if(x < 0 || y < 0) {
			return true;
		}
		return false;
	}
}
