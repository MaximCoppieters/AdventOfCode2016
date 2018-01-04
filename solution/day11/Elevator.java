package solution.day11;

public class Elevator {
	private static final int CAPACITY = 2;
	private RTG[] objects = new RTG[CAPACITY];
	private int floorNumber;
	private int movementCount = 0;
	
	public void move(int floorToGo) {
		int distance = Math.abs(floorNumber - floorToGo);
		
		movementCount += distance;
	}
	
	public void setObject(RTG object) {
		if(objects[0] == null) {
			objects[0] = object;
		} else {
			if(objects[1] == null) {
				objects[1] = object;
			}
		}
	}
	
	public Generator getGenerator(String type) {
		for(RTG object : objects) {
			if(object.getClass().getSimpleName().equals("Generator") && object.getType() == type) {
				return (Generator) object;
			}
		}
		return null;
	}
	
	public Microchip getMicrochip(String type) {
		for(RTG object : objects) {
			if(object.getClass().getSimpleName().equals("Microchip") && object.getType() == type) {
				return (Microchip) object;
			}
		}
		return null;
	}
	
	public int getMovementCount() {
		return movementCount;
	}
}
