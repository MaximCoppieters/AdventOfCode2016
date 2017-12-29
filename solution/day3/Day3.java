package solution.day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import skeleton.AdventOfCode;

public class Day3 extends AdventOfCode {
	public Day3() throws FileNotFoundException {
		super();
	}
	
	public Day3(String test) throws FileNotFoundException {
		super("test");
	}

	@Override
	public void part1() {
		int validCount=0;
		String lines[] = this.getInput().split("\n");
		int side1;
		int side2;  
		int side3;
		String triangleValues[];
		Triangle currentTriangle;
		
		for(String line : lines) {
			triangleValues = line.trim().replaceAll(" +", " ").split(" ");
			side1 = Integer.parseInt(triangleValues[0]);
			side2 = Integer.parseInt(triangleValues[1]);
			side3 = Integer.parseInt(triangleValues[2]);
			
			currentTriangle = new Triangle(side1,side2,side3);
			
			if(currentTriangle.isValid()) {
				validCount++;
			}
		}
		
		System.out.println(validCount);
	}

	@Override
	public void part2() {
		int validCount=0;
		String[] lines = this.getInput().split("\n");
		int side1;
		int side2;  
		int side3;
		String triangleValues[];
		
		List<String[]> inputValues = new ArrayList<>();
		Triangle currentTriangle = new Triangle();
		
		int valueCount = 0;
		
		for(String line : lines) {
			triangleValues = line.trim().replaceAll(" +", " ").split(" ");
			inputValues.add(triangleValues);	
		}
		
		for(int x=0; x < inputValues.get(0).length; x++) {
			for(int y=0; y < inputValues.size(); y++) {
				if(valueCount > 2) {
					valueCount = 0;
					currentTriangle = new Triangle();
				}
				if(valueCount == 0) {
					currentTriangle.setSide1(Integer.parseInt(inputValues.get(y)[x]));
					System.out.println(inputValues.get(y)[x]);
				} else {
					if(valueCount == 1) {
							currentTriangle.setSide2(Integer.parseInt(inputValues.get(y)[x]));
					} else {
						if(valueCount == 2) {
							currentTriangle.setSide3(Integer.parseInt(inputValues.get(y)[x]));
						}
					}
				}
				valueCount++;
				if(currentTriangle.getSide1() != 0 && currentTriangle.getSide2() != 0 
						&& currentTriangle.getSide3() != 0) {
					if(currentTriangle.isValid()) {
						validCount++;
					}
				}
			}
		}
		
		System.out.println(validCount);
		
	}
}
