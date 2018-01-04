package solution.day12;

public class Instruction {
	private String command;
	private String argument1;
	private String argument2;
	private static InstructionIterator iterator;
	
	public Instruction(String command, String argument) {
		this(command, argument, null);
	}
	
	public Instruction(String command, String argument1, String argument2) {
		this.command = command;
		this.argument1 = argument1;
		this.argument2 = argument2;
		
	}
	
	public void executeInstruction() {
		if(argument2 == null) {
			Register targetRegister = Register.getRegisterFromName(argument1);
			if(command.equals("inc")) {
				targetRegister.increment();
			} else {
				targetRegister.decrement();
			}
		} else {
			if(command.equals("cpy")) {
				Register targetRegister = Register.getRegisterFromName(argument2);
				int value;
				if(isArgumentNumeric(argument1)) {
					value = Integer.parseInt(argument1);
				} else {
					Register registerGetValueOf = Register.getRegisterFromName(argument1);
					value = registerGetValueOf.getValue();
				}
				targetRegister.copy(value);
			} else {
				if(command.equals("jnz")) {
					int offset;
					int value;
					if(isArgumentNumeric(argument1)) {
						value = Integer.parseInt(argument1);
					} else {
						value = Register.getRegisterFromName(argument1).getValue();
					}
					if(isArgumentNumeric(argument2)) {
						offset = Integer.parseInt(argument2);
					} else {
						offset = Register.getRegisterFromName(argument1).getValue();
					}
					if(value != 0) {
						iterator.jumpNotZero(offset);
					}
				}
			}
		}
	}
	
	public boolean isArgumentNumeric(String argument) {
		if(argument.charAt(0) <= '9' && argument.charAt(0) >= '0' || argument.charAt(0) == '-') {
			return true;
		}
		return false;
	}
	
	public static void setIterator(InstructionIterator instructionIterator) {
		iterator = instructionIterator;
	}
}
