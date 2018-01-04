package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import solution.day9.Day9;

class Day9Test {

	@Test
	void getNextMarkerIndexGivesCorrectIndex() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "Aba(1x5)BC";
		
		int index = d.getNextMarkerIndex(input, 0);
		
		
		assertEquals(3, index, "Marker index is wrong");
	}
	
	@Test
	void getNextMarkerIndexGivesCorrectIndexFromIndex() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "A(1x5)BC(5x8)asas";
		
		int index = d.getNextMarkerIndex(input, 7);
		
		assertEquals(8, index, "Marker index is wrong");
	}
	
	
	@Test
	void getNextMarkerFindsFirstMarkerFromOne() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "A(1x5)BC";
		int index = 1;
		
		String marker = d.getNextMarker(input, index);
		
		assertEquals("1x5", marker, "Marker selection is wrong");
	}
	
	@Test
	void getNextMarkerFindsFirstMarkerFromTwo() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "A(2x2)BCD(3x2)EFG";
		int index = 1;
		
		String marker = d.getNextMarker(input, index);
		
		assertEquals("2x2", marker, "Marker selection is wrong");
	}
	
	@Test
	void getNextMarkerFindsSecondMarkerFromTwo() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "A(2x2)BCD(3x2)EFG";
		int index = 6;
		
		String marker = d.getNextMarker(input, index);
		
		assertEquals("3x2", marker, "Marker selection is wrong");
	}
	
	@Test
	void removeCurrentMarkerRemovesMarkerFromStringBuilder() throws FileNotFoundException {
		Day9 d = new Day9();
		StringBuilder input = new StringBuilder("(8x10)BCDEFG");
		int index = 0;
		
		d.removeCurrentMarker(input, index);
		
		assertEquals("BCDEFG", input.toString());
	}
	
	@Test
	void removeCurrentMarkerRemovesMarkerFromIndex() throws FileNotFoundException {
		Day9 d = new Day9();
		StringBuilder input = new StringBuilder("AAZ(2x2)BCDEFG");
		int index = 3;
		
		d.removeCurrentMarker(input, index);
		
		assertEquals("AAZBCDEFG", input.toString());
	}
	
	@Test
	void getNextMarkerDoesntThrowExceptionWhenEmpty() throws FileNotFoundException {
		Day9 d = new Day9();
		String input = "ADVENT";
		int index = 6;
		
		String marker = d.getNextMarker(input, index);
	}
	
	@Test
	void getAmountOfCharactersFromMarkerIsCorrectWithOneCharacter() throws FileNotFoundException {
		Day9 d = new Day9();
		String marker = "3x2";
		
		int amountOfCharacters = d.getAmountOfCharacters(marker);
		
		assertEquals(3, amountOfCharacters, "Amount is incorrect");
	}
	
	@Test
	void getAmountOfCharactersFromMarkerIsCorrectWithMultipleCharacters() throws FileNotFoundException {
		Day9 d = new Day9();
		String marker = "20x2";
		
		int amountOfCharacters = d.getAmountOfCharacters(marker);
		
		assertEquals(20, amountOfCharacters, "Amount is incorrect");
	}

	
	@Test
	void getAmountToMultiplyIsCorrectForMultipleCharacters() throws FileNotFoundException {
		Day9 d = new Day9();
		String marker = "20x255";
		
		int amountOfCharacters = d.getAmountToMultiply(marker);
		
		assertEquals(255, amountOfCharacters, "Amount is incorrect");
	}
	
	@Test
	void multiplyCharactersMultipliesTheRightAmount() throws FileNotFoundException {
		Day9 d = new Day9();
		int amount = 3;
		int times = 1;
		
		StringBuilder input = new StringBuilder("XYZ");
		
		d.multiplyCharacters(input, amount, 0);
		
		assertEquals("XYZXYZ", input.toString(), "Amount is incorrect");
	}
	
	@Test
	void multiplyCharactersMultipliesTOnlyTheSelection() throws FileNotFoundException {
		Day9 d = new Day9();
		int amount = 3;
		int times = 1;
		
		StringBuilder input = new StringBuilder("XYZblabla");
		
		d.multiplyCharacters(input, amount, 0);
		
		assertEquals("XYZXYZblabla", input.toString(), "Amount is incorrect");
	}
	
	@Test
	void multiplyCharactersMultipliesFromTheRightIndex() throws FileNotFoundException {
		Day9 d = new Day9();
		int amount = 3;
		int times = 1;
		int index = 4;
		
		StringBuilder input = new StringBuilder("testXYZblabla");
		
		d.multiplyCharacters(input, amount, index);
		
		assertEquals("testXYZXYZblabla", input.toString(), "Amount is incorrect");
	}

}
