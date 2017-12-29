package skeleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public abstract class AdventOfCode {
	private String inputPath;
	private String classname;
	private String input;
	
	public AdventOfCode() throws FileNotFoundException {
		setClassName();
		setInputPath();
		setInput();
	}
	
	public AdventOfCode(String test) throws FileNotFoundException {
		setClassName();
		setInputPath();
		runTestFile();
		setInput();
	}
	
	public abstract void part1();
	
	public abstract void part2();
	
	private void setClassName() {
		this.classname = getClass().getSimpleName().toLowerCase();
	}
	
	public void setInputPath() {
		String path = "src/inputs/";
		
		this.inputPath = path + classname;
	}
	
	public void print() {
		part1();
		part2();
	}
	
	public String getInput() {
		return input;
	}
	
	public void setInput() throws FileNotFoundException {
		Scanner fileHandler = new Scanner(new File(inputPath));
		StringBuilder input = new StringBuilder();
		Formatter formatter = new Formatter(input);
		
		while(fileHandler.hasNext()) {
			formatter.format("%s%n", fileHandler.nextLine());
		}
		
		this.input = input.toString();
		fileHandler.close();
		formatter.close();
	}
	
	public void runTestFile() {
		inputPath = inputPath + "_test";
	}
	
}
