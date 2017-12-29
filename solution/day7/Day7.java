package solution.day7;

import java.io.FileNotFoundException;

import skeleton.AdventOfCode;

public class Day7 extends AdventOfCode{

	public Day7() throws FileNotFoundException {
		super();
	}
	
	public Day7(String test) throws FileNotFoundException {
		super("test");
	}

	@Override
	public void part1() {
		final long startTime = System.currentTimeMillis();
		String[] lines = this.getInput().split("\n");
		String part1;
		String part2;
		String hypernetSequence;
		String view;
		int linesSupportingTLS = 0;
		boolean isFoundInLine;

		for(String line : lines) {
			view = "";
			part1 = line.substring(0, line.indexOf('['));
			hypernetSequence = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
			part2 = line.substring(line.indexOf(']') + 1);
			isFoundInLine = false;
			
			for(int i=0; i < hypernetSequence.length(); ++i) {
				view = createViewFromIndex(hypernetSequence, i);
				if(isViewAbba(view) == true) {
					isFoundInLine = true;
				}
			}
			if(isFoundInLine == true) continue;
			
			for(int i=0; i < part1.length(); ++i) {
				view = createViewFromIndex(part1, i);
				if(isViewAbba(view)) {
					isFoundInLine = true;
				}
			}
			
			for(int i=0; i < part2.length(); ++i) {
				view = createViewFromIndex(part2, i);
				if(isViewAbba(view)) {
					isFoundInLine = true;
				}
			}
			
			if(isFoundInLine) {
				++linesSupportingTLS;
			}
		}
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(linesSupportingTLS);
	}

	public String createViewFromIndex(String line, int i) {
		if(i + 4 <= line.length()) {
			return line.substring(i, i + 4);
		} else {
			return line.substring(i);
		}
	}

	@Override
	public void part2() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isViewAbba(String view) {
		if(view.trim().length() != 4) return false;
		if(view.charAt(0) == view.charAt(1)) return false;
		
		String firstPart = view.substring(0, 2);
		StringBuilder secondPart = new StringBuilder(view.substring(2, 4));
		
		if(firstPart.equals(secondPart.reverse().toString())) {
			return true;
		}
		
		return false;
	}

}
