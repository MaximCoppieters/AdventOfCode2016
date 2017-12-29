package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import solution.day1.Day1;
import solution.day1.Location;

class Day1Test {
	Day1 day1;
	
	@Test
	void checkIfChangeDirectionTurnsLeft() throws FileNotFoundException {
		day1 = new Day1();
		
		assertTrue(day1.checkIfLeftOrRight('L'));
		assertFalse(day1.checkIfLeftOrRight('R'));
	}
	
	@Test
	void isDistanceRightFromInstruction() throws FileNotFoundException{
		String instruction = "L12";
		day1 = new Day1();
		
		int distance = day1.getDistance(instruction);
		
		assertEquals(12, distance);
	}
	
	@Test
	void checkIfDirectionChangesProperly() throws FileNotFoundException {
		day1 = new Day1();
		
		boolean isLeft = true;
		char direction = 'W';
		direction = day1.changeDirection(direction, isLeft);
		
		assertEquals('S', direction);
		
		isLeft = false;
		
		direction = day1.changeDirection(direction, isLeft);
		
		assertEquals('W', direction);
	}


}
