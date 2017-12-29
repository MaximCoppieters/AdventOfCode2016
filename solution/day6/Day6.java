package solution.day6;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import skeleton.AdventOfCode;

public class Day6 extends AdventOfCode{
	
	public Day6() throws FileNotFoundException {
		super();
	}
	
	public Day6(String test) throws FileNotFoundException {
		super("");
	}

	@Override
	public void part1() {
		String lines[] = this.getInput().split("\n");
		Map<Character, Integer> charOccurences;
		char highestValueChar = '&';
		int highest;
		List<Character> mostFrequentCharacters = new ArrayList<>();;
		
		char[][] characterArray = new char[lines.length][lines[0].length()];
		
		fillCharacterArray(characterArray, lines);
		
		for(int x=0; x < characterArray[0].length; x++) {
			charOccurences = new HashMap<>();
			addCharactersAndTrackCount(charOccurences, characterArray, x);
			highest = 0;
			
			for(Map.Entry<Character, Integer> entry : charOccurences.entrySet()) {
				if(highest < entry.getValue()) {
					highest = entry.getValue();
					highestValueChar = entry.getKey();
				}
			}
			
			mostFrequentCharacters.add(highestValueChar);
		}
		for(char character : mostFrequentCharacters) {
			System.out.print(character);
		}
	}

	@Override
	public void part2() {
		String lines[] = this.getInput().split("\n");
		Map<Character, Integer> charOccurences;
		char lowestValueChar = '&';
		int lowest;
		List<Character> mostFrequentCharacters = new ArrayList<>();;

		char[][] characterArray = new char[lines.length][lines[0].length()];
		
		fillCharacterArray(characterArray, lines);
		
		for(int x=0; x < characterArray[0].length; x++) {
			charOccurences = new HashMap<>();
			addCharactersAndTrackCount(charOccurences, characterArray, x);
			lowest = 500;
			
			for(Map.Entry<Character, Integer> entry : charOccurences.entrySet()) {
				if(lowest > entry.getValue()) {
					lowest = entry.getValue();
					lowestValueChar = entry.getKey();
				}
			}
			
			mostFrequentCharacters.add(lowestValueChar);
		}
		for(char character : mostFrequentCharacters) {
			System.out.print(character);
		}
	}
	
	public void addCharactersAndTrackCount(Map<Character, Integer> occurences,
			char[][] characterArray, int index) {
		char character;
		for(int y=0; y < characterArray.length; y++) {
			character = characterArray[y][index];
			if(occurences.containsKey(character) == false) {
				occurences.put(character, 1);
			} else {
				occurences.put(character, occurences.get(character)+1);
			}
		}
	}
	
	public void fillCharacterArray(char[][] characterArray, String[] lines) {
		for(int y=0; y < lines.length; y++) {
			for(int x=0; x < lines[y].length(); x++) {
				characterArray[y][x] = lines[y].charAt(x);
			}
		}
	}
}
