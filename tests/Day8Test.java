package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import solution.day8.Day8;

class Day8Test {

	@Test
	void rectFillsTopLeftScreen() throws FileNotFoundException {
		Day8 d = new Day8();
		
		int xDimension = 3;
		int yDimension = 2;
		
		d.rect(xDimension, yDimension);
		
		boolean[][] screen = d.getScreen();
		
		
	}
	
	@Test
	void rotateColumnWorks() throws FileNotFoundException {
		Day8 d = new Day8();
		
		int xDimension = 3;
		int yDimension = 2;
		boolean onRow = false;
		int amount = 1;
		int index = 1;
		
		d.rect(xDimension, yDimension);
		d.rotate(index, onRow, amount);
		
	}
	
	@Test
	void rotateRowWorks() throws FileNotFoundException {
		Day8 d = new Day8();
		
		int xDimension = 3;
		int yDimension = 2;
		boolean onRow = false;
		int amount = 1;
		int index = 1;
		
		d.rect(xDimension, yDimension);
		d.rotate(index, onRow, amount);
		
		onRow = true;
		amount = 4;
		index = 0;
		d.rotate(index, onRow, amount);
		
		d.draw();
	}

}
