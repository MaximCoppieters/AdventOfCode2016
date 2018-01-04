package solution.day12;

import java.util.HashMap;
import java.util.Map;

public class Register {
	private static Map<String, Register> registersByName = new HashMap<>();
	private int value;
	private String name;
	
	public Register(String name) {
		this.name = name;
	}
	
	public Register(String name, int value) {
		this.name = name;
		this.value = value;
	}
	
	public void increment() {
		this.value++;
	}
	
	public void decrement() {
		this.value--;
	}
	
	public void copy(int value) {
		this.value = value;
	}
	
	public static Register getRegisterFromName(String name) {
		if(registersByName.get(name) == null) {
			registersByName.put(name, new Register(name));
		}
		return registersByName.get(name);
	}
	
	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Register other = (Register) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	public static void printRegistersByValue() {
		for(Map.Entry<String, Register> entry : registersByName.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public String toString() {
		return "" + this.getValue();
	}
	
	public static void initiateRegister(String name, int value) {
		registersByName.put(name, new Register(name, value));
	}
	
}
