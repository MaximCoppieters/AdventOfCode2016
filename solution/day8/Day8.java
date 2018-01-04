package solution.day8;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import skeleton.AdventOfCode;

public class Day8 extends AdventOfCode{

	private boolean[][] screen = new boolean[6][50];
	
	public Day8() throws FileNotFoundException {
		super();
	}
	
	public Day8(String test) throws FileNotFoundException {
		super("test");
	}

	@Override
	public void part1() {
		String lines[] = this.getInput().split("\n");
		int widthRectangle;
		int heightRectangle;
		String columnOrRow;
		boolean onRow;
		int amountToRotate;
		int index;
		String data[];
		int pixelsLit;
		
		for(String line : lines) {
			if(line.indexOf("rect") != -1) {
				widthRectangle = Integer.parseInt(line.substring(line.indexOf(" ") +1, line.indexOf("x")));
				heightRectangle = Integer.parseInt(line.substring(line.indexOf("x") +1));
				rect(widthRectangle, heightRectangle);
			} else {
				data = line.split(" ");
				columnOrRow = data[1];
				index = Integer.parseInt(data[2].substring(data[2].indexOf('=')+1));
				amountToRotate = Integer.parseInt(data[4]);
				if(columnOrRow.equals("row")) {
					onRow = true;
				} else {
					onRow = false;
				}
				rotate(index, onRow, amountToRotate);
			}
		}
		
		pixelsLit = checkScreenTrueCount();
		
		System.out.println(pixelsLit);
	}

	private int checkScreenTrueCount() {
		int count = 0;
		
		for(int y=0; y < screen.length; y++) {
			for(int x=0; x < screen[y].length; x++) {
				if(screen[y][x] == true) {
					++count;
				}
			}
		}
		return count;
	}

	@Override
	public void part2() {
		String lines[] = this.getInput().split("\n");
		int widthRectangle;
		int heightRectangle;
		String columnOrRow;
		boolean onRow;
		int amountToRotate;
		int index;
		String data[];
		int pixelsLit;
		
		for(String line : lines) {
			if(line.indexOf("rect") != -1) {
				widthRectangle = Integer.parseInt(line.substring(line.indexOf(" ") +1, line.indexOf("x")));
				heightRectangle = Integer.parseInt(line.substring(line.indexOf("x") +1));
				rect(widthRectangle, heightRectangle);
			} else {
				data = line.split(" ");
				columnOrRow = data[1];
				index = Integer.parseInt(data[2].substring(data[2].indexOf('=')+1));
				amountToRotate = Integer.parseInt(data[4]);
				if(columnOrRow.equals("row")) {
					onRow = true;
				} else {
					onRow = false;
				}
				rotate(index, onRow, amountToRotate);
			}
		}
		
		draw();
	}
	
	public void rect(int width, int height) {
		for(int y=0; y < height; y++) {
			for(int x=0; x < width; x++) {
				screen[y][x] = true;
			}
		}
	}
	
	public void rotate(int index, boolean onRow, int amount) {
		List<Boolean> selection = new ArrayList<>();
		
		if(onRow == true) {
			int y = index;
			for(int x=0; x < screen[y].length; x++) {
				selection.add(screen[y][x]);
			}
			Collections.rotate(selection, amount);
			for(int x=0; x < screen[y].length; x++) {
				screen[y][x] = selection.get(x);
			}
			
		} else {
			int x = index;
			for(int y=0; y < screen.length; y++) {
				selection.add(screen[y][x]);
			}
			Collections.rotate(selection, amount);
			for(int y=0; y < screen.length; y++) {
				screen[y][x] = selection.get(y);
			}
		}
	}

	public boolean[][] getScreen() {
		return screen;
	}
	
	public void draw() {
		for(int y=0; y < screen.length; y++) {
			for(int x=0; x < screen[y].length; x++) {
				if(screen[y][x]) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
	}
	
	

}
