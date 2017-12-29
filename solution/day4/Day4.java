package solution.day4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import skeleton.AdventOfCode;

public class Day4 extends AdventOfCode{
	public Day4() throws FileNotFoundException {
		super();
	}

	@Override
	public void part1() {
		Map<Character, AtomicInteger> charCount;
		String[] rooms = this.getInput().split("\n");
		String checksum;
		int sectorId;
		String encryptedName;
		int sumSectorId = 0;
		
		for(String room : rooms) {
			encryptedName = room.substring(0, room.indexOf('['));
			checksum= room.substring(room.indexOf('[') + 1, room.indexOf(']'));
			sectorId = getSectorId(encryptedName);
			charCount = new TreeMap<>();
			
			for(char letter : encryptedName.toCharArray()) {
				if(letter == '-' || (letter <= '9' && letter >= '0')) {
					continue;
				}
				
				if(charCount.containsKey(letter) == false) {
					charCount.put(letter, new AtomicInteger(1));
				} else {
					charCount.get(letter).incrementAndGet();
				}
			}
			

			if(isRoomValid(charCount, checksum)) {
				sumSectorId += sectorId;
			}
		}
		
		System.out.println(sumSectorId);
	}

	@Override
	public void part2() {
		Map<Character, AtomicInteger> charCount;
		String[] rooms = this.getInput().split("\n");
		String checksum;
		int sectorId;
		String encryptedName;
		int sumSectorId = 0;
		
		for(String room : rooms) {
			encryptedName = room.substring(0, room.indexOf('['));
			checksum= room.substring(room.indexOf('[') + 1, room.indexOf(']'));
			sectorId = getSectorId(encryptedName);
			charCount = new TreeMap<>();
			
			List<Character> encryptedNameRotated = addToList(encryptedName);
			rotateCharactersThroughAlphabet(encryptedNameRotated, sectorId);
			
			String realRoomName = getRealRoomName(encryptedNameRotated);
			
			if(realRoomName.indexOf("northpole") != -1) {
				System.out.println(sectorId);
			}
		}
		
		
	}
	
	private String getRealRoomName(List<Character> encryptedNameRotated) {
		StringBuilder name = new StringBuilder();
		for(char character : encryptedNameRotated) {
			name.append(character);
		}
		return name.toString();
	}

	public int getSectorId(String encryption) {
		return Integer.parseInt(encryption.substring(encryption.length()-3));
	}
	
	public boolean isRoomValid(Map<Character, AtomicInteger> charCount, String checksum) {
		List<Integer> charCountValues = new ArrayList<>();
		
		for(Map.Entry<Character, AtomicInteger> entry : charCount.entrySet()) {
	        charCountValues.add(entry.getValue().intValue());
	    }
		
		Collections.sort(charCountValues);
		Collections.reverse(charCountValues);
		
		for(int i=0; i < checksum.length(); i++) {
			if(charCount.get(checksum.charAt(i)) == null) return false;
			if(charCount.get(checksum.charAt(i)).intValue() != charCountValues.get(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Character> addToList(String encrypted){
		List<Character> characters = new ArrayList<>();
		for(char character : encrypted.toCharArray()) {
			if(character == '-' || (character <= '9' && character >= '0')) {
				continue;
			}
			characters.add(character);
		}
		
		return characters;
	}
	
	public void rotateCharactersThroughAlphabet(List<Character> encryptedNameRotated, int sectorId) {
		List<Character> alphabet = new ArrayList<>();
		List<Character> alphabetRotated;
		
		int positionAlphabet;
		char character;
		
		for(char letter = 'a'; letter <= 'z'; letter++) {
			alphabet.add(letter);
		}
		
		for(int i=0; i < encryptedNameRotated.size(); i++) {
			alphabetRotated = new ArrayList<>(alphabet);
			positionAlphabet = alphabetRotated.indexOf(encryptedNameRotated.get(i));
			Collections.rotate(alphabetRotated, -sectorId); // NEGATIVE
			encryptedNameRotated.set(i, alphabetRotated.get(positionAlphabet));
		}
	}
	
}
