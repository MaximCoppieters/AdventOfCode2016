package tests;


import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

import solution.day4.Day4;

class Day4Test {
	
	
	@Test
	void test() throws FileNotFoundException {
		Day4 day4 = new Day4();
		
		List<Character> encryptedNameRotated = day4.addToList("qzmt-zixmtkozy-ivhz");
		day4.rotateCharactersThroughAlphabet(encryptedNameRotated, 343);
		
		for(char letter : encryptedNameRotated) {
			System.out.print(letter);
		}
		
	}

}
