package solution.day9;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skeleton.AdventOfCode;

public class Day9 extends AdventOfCode {

	public Day9() throws FileNotFoundException {
		super();
	}
	
	public Day9(String test) throws FileNotFoundException {
		super(" ");
	}

	@Override
	public void part1() {
		StringBuilder input = new StringBuilder(this.getInput());
		int currentIndex = 0;
		String nextMarker;
		int amountOfCharacters;
		int amountToMultiply;
		int endOfNextMarkerIndex;
		
		while(getNextMarkerIndex(input.toString(), currentIndex) != -1) {
			currentIndex = getNextMarkerIndex(input.toString(), currentIndex);
			nextMarker = getNextMarker(input.toString(), currentIndex);
			amountOfCharacters = getAmountOfCharacters(nextMarker);
			amountToMultiply = getAmountToMultiply(nextMarker);
			endOfNextMarkerIndex = getEndOfNextMarkerIndex(input.toString(), currentIndex);
			
			for(int amount=0; amount < amountToMultiply - 1; amount++) {
				// -1 because there is already 1 set of characters present!
				multiplyCharacters(input, amountOfCharacters, endOfNextMarkerIndex);
			}
			
			removeCurrentMarker(input, currentIndex);
			
			currentIndex = currentIndex + amountOfCharacters * amountToMultiply;
		}
		
		System.out.println(input.toString().trim().length());
		System.out.println(input);
	}

	@Override
	public void part2() {
		String input = this.getInput().trim();
		String marker;
		int amountToMultiply = 0;
		int amountOfCharacters = 0;
		boolean inMarker = false;
		long total = 0;
		
		Map<Integer, Integer> weightByIndex = new HashMap<>();
		
		for (int index = 0; index < input.length(); index++) {
			weightByIndex.put(index, 1);
		}
		
		for(int index : weightByIndex.keySet()) {
			if(input.charAt(index) == '(') {
				marker = getNextMarker(input, index);
				amountToMultiply = getAmountToMultiply(marker);
				amountOfCharacters = getAmountOfCharacters(marker);
				inMarker = true;
			} else {
				if(input.charAt(index) == ')'){
					increaseWeightsByMarkerValues(amountToMultiply, amountOfCharacters,
							weightByIndex, index + 1);
					inMarker = false;
				} else {
					if(inMarker == false) {
						total+= weightByIndex.get(index);
					}
				}
			}
		}
		
		System.out.println(total);
	}
	
	
	public void increaseWeightsByMarkerValues(int multiplier, int amount, 
			Map<Integer, Integer> weightByIndex, int index) {
		int currentValue;
		
		for(int i=index; i < index + amount; i++) {
			currentValue = weightByIndex.get(i);
			weightByIndex.put(i, currentValue * multiplier);
		}
	}

	public int getEndOfNextMarkerIndex(String input, int fromIndex) {
		return input.indexOf(")", fromIndex) + 1;
	}
	
	public int getNextMarkerIndex(String input, int fromIndex) {
		return input.indexOf("(", fromIndex);
	}
	
	public String getNextMarker(String input, int index) {
		if(input.indexOf(")") == -1) {
			System.out.println("fout");
			return "";
		}
		return input.substring(input.indexOf('(', index) + 1, input.indexOf(')', index));
	}
	
	public int getAmountOfCharacters(String marker) {
		return Integer.parseInt(marker.substring(0, marker.indexOf('x')));
	}


	public int getAmountToMultiply(String marker) {
		return Integer.parseInt(marker.substring(marker.indexOf('x')+1));
	}

	public void multiplyCharacters(StringBuilder input, int amount, int index) {
		String selection = input.substring(index, index + amount);
		System.out.println(selection);
		
		input.insert(index, selection);
	}
	

	public void removeCurrentMarker(StringBuilder input, int index) {
		input.delete(index, input.indexOf(")", index) + 1);
		
	}
	
}
