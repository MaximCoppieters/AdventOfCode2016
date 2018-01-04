package solution.day12;

import java.io.FileNotFoundException;

import skeleton.AdventOfCode;

public class Day12 extends AdventOfCode {
	public Day12() throws FileNotFoundException {
		super();
	}
	
	public Day12(String test) throws FileNotFoundException {
		super(" ");
	}

	@Override
	public void part1() {
		String instructions[] = this.getInput().split("\n");
		InstructionIterator iterator = new InstructionIterator(instructions);
		String line;
		String arguments[];
		Instruction currentInstruction;
		
		Instruction.setIterator(iterator);
		
		while(iterator.hasNext()) {
			line = iterator.next();
			System.out.println(line);
			arguments = line.split(" ");
			if(arguments.length < 3) {
				currentInstruction = new Instruction(arguments[0], arguments[1]);
				currentInstruction.executeInstruction();
			} else {
				currentInstruction = new Instruction(arguments[0], arguments[1], arguments[2]);
				currentInstruction.executeInstruction();
			}
		}
		
		Register.printRegistersByValue();
	}

	@Override
	public void part2() {
		String instructions[] = this.getInput().split("\n");
		InstructionIterator iterator = new InstructionIterator(instructions);
		String line;
		String arguments[];
		Instruction currentInstruction;
		
		Instruction.setIterator(iterator);
		
		Register.initiateRegister("c", 1);
		
		Register.printRegistersByValue();
		
		while(iterator.hasNext()) {
			line = iterator.next();
			arguments = line.split(" ");
			if(arguments.length < 3) {
				currentInstruction = new Instruction(arguments[0], arguments[1]);
				currentInstruction.executeInstruction();
			} else {
				currentInstruction = new Instruction(arguments[0], arguments[1], arguments[2]);
				currentInstruction.executeInstruction();
			}
		}
		
		Register.printRegistersByValue();
		
	}
	
}
