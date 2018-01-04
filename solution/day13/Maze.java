package solution.day13;

import java.awt.Point;

public class Maze {
	
	private static final int INPUT_VALUE = 1362;
	private boolean[][] labyrinth;
	
	
	public Maze(int width, int length) {
		labyrinth = new boolean[length][width];
	}
	
	public boolean pointIsWall(Point point) {
		return labyrinth[point.x][point.y];
	}
	
	public void initialize() {
		for(int y=0; y < labyrinth.length; y++) {
			for(int x=0; x < labyrinth[y].length; x++) {
				labyrinth[y][x] = calculateIfWall(x, y);
			}
		}
	}
	
	
	private boolean calculateIfWall(int x, int y) {
		int sum = resultOfOfficeFormula(x, y);
		if(Integer.bitCount(sum) % 2 != 0) {
			return true;
		}
		return false;
	}

	public int resultOfOfficeFormula(int x, int y) {
		return x*x + 3*x + 2*x*y + y + y*y + INPUT_VALUE;
	}
	
	public void drawMaze() {
		for(int y=0; y < labyrinth.length; y++) {
			for(int x=0; x < labyrinth[y].length; x++) {
				if(labyrinth[y][x] == true) {
					System.out.print("#");
				} else {
					if(y == 1 && x == 1) {
						System.out.print("S");
					} else {
						if(y == 39 && x == 31) {
							System.out.print("X");
						} else {
							System.out.print(".");
						}
					}
				}
			}
			System.out.println();
		}
	}
}
