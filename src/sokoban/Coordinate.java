package sokoban;

public class Coordinate {

	Integer x;
	Integer y;

	public Coordinate() {

	}

	public Coordinate(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return x * 1000 + y;
	}

	// equals method used for contains()
	@Override
	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (object == this)
			return true;
		if (this.getClass() != object.getClass())
			return false;
		Coordinate c = (Coordinate) object;
		if (this.hashCode() == c.hashCode())
			return true;
		return ((this.x == c.x) && (this.y == c.y));
	}

}
