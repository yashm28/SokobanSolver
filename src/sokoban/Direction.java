package sokoban;

public class Direction {

	public static final Coordinate UP = new Coordinate(-1, 0);
	public static final Coordinate DOWN = new Coordinate(1, 0);
	public static final Coordinate LEFT = new Coordinate(0, -1);
	public static final Coordinate RIGHT = new Coordinate(0, 1);

	public Direction() {
	}

	public char getDirection(Coordinate DIR) {
		if (DIR.equals(UP)) {
			return 'u';
		} else if (DIR.equals(DOWN)) {
			return 'd';
		} else if (DIR.equals(LEFT)) {
			return 'l';
		} else if (DIR.equals(RIGHT)) {
			return 'r';
		} else {
			throw new IllegalStateException("Invalid Move!");
		}
	}

}
