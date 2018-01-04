package solution.day11;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import skeleton.AdventOfCode;

public class Day11 extends AdventOfCode {

	public Day11() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Day11(String test) throws FileNotFoundException {
		super(" ");
	}

	@Override
	public void part1() {
		String lines[] = this.getInput().split("\n");
		Map<Integer, RTG> objectByIndex = new HashMap<>();
		Set<String> typesOfObjects = new HashSet<>();
		Elevator elevator = new Elevator();
		RTG[][] building = new RTG[lines.length][10];
		int elevatorFloorNumber = 0;
		boolean isSafe;
		int floorOfGenerator;
		int floorOfMicrochip;
		int positionOfGenerator;
		int positionOfMicrochip;
		
		findObjectsAndGiveIndex(objectByIndex, lines, building);
		getTypesOfObjects(objectByIndex, typesOfObjects);
		drawBuilding(building);
		
		for(String type : typesOfObjects) {
			floorOfGenerator = getFloorOfObject(building, type, "Generator");
			floorOfMicrochip = getFloorOfObject(building, type, "Microchip");
			positionOfGenerator = getPositionOfObject(objectByIndex, type, "Generator");
			positionOfMicrochip = getPositionOfObject(objectByIndex, type, "Microchip");
			
			getObjectAtFloorAndPosition(building, floorOfMicrochip, positionOfMicrochip, elevator);
			getObjectAtFloorAndPosition(building, floorOfGenerator, positionOfGenerator, elevator);
		}
		
	}
	

	private void getObjectAtFloorAndPosition(RTG[][] building, int floorOfObject, int positionOfObject, Elevator elevator) {
		
		elevator.move(floorOfObject);
		RTG object = building[floorOfObject][positionOfObject];

		elevator.setObject(object);
		building[floorOfObject][positionOfObject] = null;
	}

	private int getPositionOfObject(Map<Integer, RTG> objectByIndex, String materialType, String objectType) {
		RTG object;
		for(int position : objectByIndex.keySet()) {
			object = objectByIndex.get(position);
			
			if(object.getClass().getSimpleName().equals(objectType) && object.toString().equals(materialType)) {
				return position;
			}
		}
		throw new IllegalArgumentException();
	}

	private void getTypesOfObjects(Map<Integer, RTG> objectByIndex, Set<String> typesOfObjects) {
		for(RTG object : objectByIndex.values()) {
			typesOfObjects.add(object.toString());
		}
		
	}

	private void drawBuilding(RTG[][] building) {
		for(int i=building.length - 1; i >= 0; i--) {
			System.out.print("F" + i + " ");
			for(int j=0; j < building[i].length; j++) {
				if(building[i][j] == null) {
					System.out.printf("%5s", ".");
				} else {
					System.out.printf("%5s", building[i][j].getType());
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	private void findObjectsAndGiveIndex(Map<Integer, RTG> objectByIndex, String[] lines, RTG[][] building) {
		int position = 0;
		String words[];
		Generator gen;
		Microchip chip;
		
		for(int j=0; j < lines.length; j++) {
			words = lines[j].split(" ");
			for(int i=0; i < words.length; i++) {
				if(words[i].indexOf("gen") != -1) {
					gen = new Generator(words[i-1]);
					objectByIndex.put(position, gen);
					building[j][position] = gen;
					position++;
				} else {
					if(words[i].indexOf("chip") != -1) {
						chip = new Microchip(words[i-1]);
						objectByIndex.put(position, chip);
						building[j][position] = chip;
						position++;
					}
				}
			}
		}
	}
	
	private int getFloorOfObject(RTG[][] building, String materialType, String objectType) {
		for(int i=0; i < building.length; i++) {
			for(RTG object : building[i]) {
				if(object == null) continue;
				
				if(object.getClass().getSimpleName().equals(objectType) && object.toString().equals(materialType)) {
					return i;
				}
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public void part2() {
		// TODO Auto-generated method stub
		
	}

}
