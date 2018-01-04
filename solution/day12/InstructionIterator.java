package solution.day12;

import java.util.Iterator;

public class InstructionIterator implements Iterator<String> {
	
	private int index;
	private String instructions[];
	
	public InstructionIterator(String instructions[]) {
		this.instructions = instructions;
	}
	
	@Override
	public boolean hasNext() {
		if(index < instructions.length) return true;
		return false;
	}

	@Override
	public String next() {
		return instructions[index++];
	}
	
	public void jumpNotZero(int offset) {
		index += offset - 1;
	}

}
