package solution.day11;

public class Generator extends RTG {

	public Generator(String type) {
		super(type);
	}
	
	public String getType() {
		return type.toUpperCase().substring(0, 2) + "-G";
	}
	
	public String toString() {
		return type;
	}
	
}
