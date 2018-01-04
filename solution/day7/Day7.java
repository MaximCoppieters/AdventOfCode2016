package solution.day7;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String supernetSequences;
		String hypernetSequences;
		String view;
		int linesSupportingTLS = 0;
		boolean isFoundInLine;

		for(String line : lines) {
			view = "";
			hypernetSequences = getSequences(line, "\\[(.+?)\\]");
			supernetSequences = deleteEverythingInBrackets(line);
			isFoundInLine = false;
			
			for(int i=0; i < hypernetSequences.length(); ++i) {
				view = createViewFromIndex(hypernetSequences, i);
				if(isViewAbba(view) == true) {
					isFoundInLine = true;
				}
			}
			if(isFoundInLine == true) continue;
			
			for(int i=0; i < supernetSequences.length(); ++i) {
				view = createViewFromIndex(supernetSequences, i);
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
		if(i + 4 < line.length()) {
			return line.substring(i, i + 4);
		} else {
			return line.substring(i);
		}
	}
	
	public String createAbaViewFromIndex(String line, int i) {
		if(i + 3 < line.length()) {
			return line.substring(i, i + 3);
		} else {
			return line.substring(i);
		}
	}

	@Override
	public void part2() {
		String[] lines = this.getInput().split("\n");
		String supernetSequences;
		String view;
		String hypernetSequences;
		List<String> AbasSupernet;
		int linesSupportingSSL = 0;
		
		for(String line : lines) {
			view = "";
			hypernetSequences = getSequences(line, "\\[(.+?)\\]");
			supernetSequences = deleteEverythingInBrackets(line);
			AbasSupernet = new ArrayList<>();
			
			for(int i=0; i < supernetSequences.length(); ++i) {
				view = createAbaViewFromIndex(supernetSequences, i);
				if(isViewAba(view)) {
					AbasSupernet.add(view);
				}
			}
			
			if(isBabInHypernet(AbasSupernet, hypernetSequences)) {
				++linesSupportingSSL;
				System.out.println(line);
			}
		}
		System.out.println(linesSupportingSSL);
		
	}
	
	private String deleteEverythingInBrackets(String line) {
		return line.replaceAll("\\[(.+?)\\]", "    ");
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
	
	public boolean isViewAba(String view) {
		if(view.trim().length() != 3) return false;
		if(view.charAt(0) == view.charAt(1)) return false;
		
		String firstPart = view.substring(0, 2);
		StringBuilder secondPart = new StringBuilder(view.substring(1, 3));
		
		if(firstPart.equals(secondPart.reverse().toString())) {
			return true;
		}
		
		return false;
	}
	
	public boolean isBabInHypernet(List<String> abas, String hypernet) {
		String bab;
		for(String aba : abas) {
			bab = convertAbaToBab(aba);
			if(hypernet.contains(bab)) {
				return true;
			}

		}
		return false;
	}
	
	private String convertAbaToBab(String aba) {
		String bab = "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1);
		return bab;
	}
	
	public String getSequences(String line, String regex) {
		StringBuilder hypernetSequence = new StringBuilder();
		
		List<String> allMatches = new ArrayList<String>();
		 Matcher m = Pattern.compile(regex)
		     .matcher(line);
		 while (m.find()) {
		   allMatches.add(m.group(1));
		 }
		
		for(String match : allMatches) {
			hypernetSequence.append(match + "   ");
		}
		
		return hypernetSequence.toString();
	}
	
	
}
