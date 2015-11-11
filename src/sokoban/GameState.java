package sokoban;

import java.util.ArrayList;

public class GameState {

	private Integer cost;
	private Integer HeuristicCost;
	private Coordinate player = new Coordinate();
	private ArrayList<String> input = new ArrayList<String>();
	private ArrayList<Coordinate> wall = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> stone = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> goal = new ArrayList<Coordinate>();
	private GameState parent = null;
	private String move = null;
	private ManhattanHeuristic mh = new ManhattanHeuristic();

	public GameState(ArrayList<String> input, Coordinate player, ArrayList<Coordinate> wall,
			ArrayList<Coordinate> stone, ArrayList<Coordinate> goal, GameState parent, String move) {
		this.input = input;
		this.player = player;
		this.wall = wall;
		this.stone = stone;
		this.goal = goal;
		this.parent = parent;
		this.move = move;
		this.cost = 0;
		this.HeuristicCost = this.mh.calculateHeuristic(this);
	}

	public GameState(ArrayList<String> input, Coordinate player, ArrayList<Coordinate> wall,
			ArrayList<Coordinate> stone, ArrayList<Coordinate> goal, GameState parent, String move, Integer cost) {
		this.input = input;
		this.player = player;
		this.wall = wall;
		this.stone = stone;
		this.goal = goal;
		this.parent = parent;
		this.move = move;
		this.cost = cost;
		this.HeuristicCost = this.mh.calculateHeuristic(this);
	}

	public boolean containing(ArrayList<Coordinate> list, Coordinate co) {
		for (Coordinate cos : list) {
			if (cos.x == co.x && cos.y == co.y) {
				return true;
			}
		}
		return false;
	}

	public Boolean isSolved() {
		for (Coordinate c : this.getGoal()) {
			if (!this.getStone().contains(c)) {
				return false;
			}
		}
		return true;
	}

	public boolean deadlockTest(GameState current) {
		for (Coordinate co : current.getStone()) {
			int row = co.x;
			int col = co.y;
			if (!setContains(goal, row, col)) {
				if (setContains(wall, row - 1, col) && setContains(wall, row, col - 1)) {
					return true; // top & left
				}
				if (setContains(wall, row - 1, col) && setContains(wall, row, col + 1)) {
					return true; // top & right
				}
				if (setContains(wall, row + 1, col) && setContains(wall, row, col - 1)) {
					return true; // bottom & left
				}
				if (setContains(wall, row + 1, col) && setContains(wall, row, col + 1)) {
					return true; // bottom & right
				}

				if (setContains(wall, row - 1, col - 1) && setContains(wall, row - 1, col)
						&& setContains(wall, row - 1, col + 1) && setContains(wall, row, col - 2)
						&& setContains(wall, row, col + 2) && (!setContains(goal, row, col - 1))
						&& !setContains(goal, row, col + 1)) {
					return true; // top & sides
				}
				if (setContains(wall, row + 1, col - 1) && setContains(wall, row + 1, col)
						&& setContains(wall, row + 1, col + 1) && setContains(wall, row, col - 2)
						&& setContains(wall, row, col + 2) && (!setContains(goal, row, col - 1))
						&& (!setContains(goal, row, col + 1))) {
					return true; // bottom & sides
				}
				if (setContains(wall, row - 1, col - 1) && setContains(wall, row, col - 1)
						&& setContains(wall, row + 1, col - 1) && setContains(wall, row - 2, col)
						&& setContains(wall, row + 2, col) && (!setContains(goal, row - 1, col))
						&& (!setContains(goal, row + 1, col))) {
					return true; // left & vertical
				}
				if (setContains(wall, row - 1, col + 1) && setContains(wall, row, col + 1)
						&& setContains(wall, row + 1, col + 1) && setContains(wall, row - 2, col)
						&& setContains(wall, row + 2, col) && (!setContains(goal, row - 1, col))
						&& (!setContains(goal, row + 1, col))) {
					return true; // right & top/bottom
				}
			}
		}

		return false;

	}

	@Override
	public int hashCode() {
		int result = 17;
		for (Coordinate b : stone) {
			result = 37 * result + b.hashCode();
		}
		result = 37 * result + player.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object object) {

		if (object == null)
			return false;
		if (object == this)
			return true;
		if (this.getClass() != object.getClass())
			return false;
		GameState s = (GameState) object;
		if (this.hashCode() == s.hashCode())
			return true;
		if ((this.stone == s.stone) && (this.player == s.player))
			return true;
		return false;
	}

	public ArrayList<String> valid(GameState state) {
		ArrayList<String> actionList = new ArrayList<>();
		int row = state.getPlayer().x;
		int col = state.getPlayer().y;
		ArrayList<Coordinate> walls = state.getWall();
		ArrayList<Coordinate> stones = state.getStone();
		Coordinate newPlayer = new Coordinate(row - 1, col);
		Coordinate newStone = new Coordinate(row - 2, col);
		if (!walls.contains(newPlayer)) {
			if (stones.contains(newPlayer) && (stones.contains(newStone) || walls.contains(newStone)))
				;
			else
				actionList.add("u");
		}
		newPlayer = new Coordinate(row, col + 1);
		newStone = new Coordinate(row, col + 2);
		if (!walls.contains(newPlayer)) {
			if (stones.contains(newPlayer) && (stones.contains(newStone) || walls.contains(newStone)))
				;
			else
				actionList.add("r");
		}
		newPlayer = new Coordinate(row + 1, col);
		newStone = new Coordinate(row + 2, col);
		if (!walls.contains(newPlayer)) {
			if (stones.contains(newPlayer) && (stones.contains(newStone) || walls.contains(newStone)))
				;
			else
				actionList.add("d");
		}
		newPlayer = new Coordinate(row, col - 1);
		newStone = new Coordinate(row, col - 2);
		if (!walls.contains(newPlayer)) {
			if (stones.contains(newPlayer) && (stones.contains(newStone) || walls.contains(newStone)))
				;
			else
				actionList.add("l");
		}
		return actionList;

	}

	private boolean setContains(ArrayList<Coordinate> set, int row, int col) {
		if (set.contains(new Coordinate(row, col)))
			return true;
		return false;
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getHeuristic() {
		return HeuristicCost;
	}

	public ArrayList<String> getinput() {
		return input;
	}

	public Coordinate getPlayer() {
		return player;
	}

	public ArrayList<Coordinate> getWall() {
		return wall;
	}

	public ArrayList<Coordinate> getStone() {
		return stone;
	}

	public ArrayList<Coordinate> getGoal() {
		return goal;
	}

	public String getMove() {
		return move;
	}

	public GameState getParent() {
		return parent;
	}

}
