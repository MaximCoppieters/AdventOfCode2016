package solution.day11;

public abstract class RTG {
	public String type;
	
	public RTG(String type) {
		this.type = type;
	}
	
	public String toString() {
		return type;
	}
	
	public abstract String getType();
}
