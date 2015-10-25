package sokoban;

import java.util.ArrayList;

public class GameState {

	private Integer cost;
	private Coordinate player = new Coordinate();
	private ArrayList<String> input = new ArrayList<String>();
	private ArrayList<Coordinate> wall = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> stone = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> goal = new ArrayList<Coordinate>();

	public GameState(ArrayList<String> input, Coordinate player, ArrayList<Coordinate> wall,
			ArrayList<Coordinate> stone, ArrayList<Coordinate> goal) {
		this.input = input;
		this.player = player;
		this.wall = wall;
		this.stone = stone;
		this.goal = goal;
		this.cost = 0;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getCost() {
		return this.cost;
	}

	public ArrayList<String> getinput() {
		return this.input;
	}

	public Coordinate getPlayer() {
		return this.player;
	}

	public ArrayList<Coordinate> getWall() {
		return this.wall;
	}

	public ArrayList<Coordinate> getStone() {
		return this.stone;
	}

	public ArrayList<Coordinate> getGoal() {
		return this.goal;
	}

}
