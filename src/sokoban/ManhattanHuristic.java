package sokoban;

public class ManhattanHuristic {

	public ManhattanHuristic() {

	}

	public Integer getManhattanDistance(Coordinate c1, Coordinate c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

}
