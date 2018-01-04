package solution.day11;

public class Microchip extends RTG {
	public Microchip(String type) {
		super(type.substring(0, type.indexOf('-')));
	}

	public String getType() {
		return type.toUpperCase().substring(0,2) + "-M";
	}
	
	public String toString() {
		return type;
	}
}
