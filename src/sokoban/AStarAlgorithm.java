package sokoban;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AStarAlgorithm {

	final Logger logger = Logger.getLogger("AStarAlgorithm");

	private GameState current;
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer redundant;
	private Integer nodes;
	private PriorityQueue<GameState> queue = new PriorityQueue<>(11, new HeuristicComparator());

	public AStarAlgorithm() {
		this.redundant = 0;
		this.nodes = 0;
	}

	public String solve(GameState initialState) {
		String solution = "Queue Empty!";
		this.current = initialState;
		queue.add(current);
		while (!queue.isEmpty()) {
//			logger.log(Level.INFO, "" + queue.size());
			current = queue.poll();
			if (current.isSolved()) {
				return getSolution(current, nodes, redundant, queue.size(), traversed.size());
			}
			if (!current.deadlockTest(current)) {
				traversed.add(current);
				ArrayList<String> actions = current.valid(current);
				logger.log(Level.SEVERE, "" + actions);
				for (int i = 0; i < actions.size(); i++) {
					GameState child = getChildState(current, actions.get(i));
					if ((child != null)) {
						nodes++;
						if ((!traversed.contains(child)) && (!queue.contains(child))){
							queue.add(child);
						}
						else {
							logger.log(Level.INFO, "trav:" + child.getMove());
							redundant++;
							for (GameState next : queue) {
								if (next == child) {
									if (child.getCost() < next.getCost()) {
										next = child;
									}
								}
							}
						}
					}
				}
			}
		}
		return solution;

	}

	public GameState getChildState(GameState state, String dir) {
		String path = "";
		GameState last = state; 
		if (last == null) {
			path = "Failed to solve the puzzle";
		} else {
			while (last.getParent() != null) {
				path = last.getMove() + " " + path;
				last = last.getParent();
			}
		}
		logger.log(Level.INFO, path + "\"" + dir +"\"");
		ArrayList<Coordinate> stones = state.getStone();
		int row = state.getPlayer().x;
		int col = state.getPlayer().y;
		int newCost = state.getCost() + 1;
		Coordinate newPlayer = null;
		char choice = dir.charAt(0);
		switch (choice) {
		case 'u':
			// update player coordinate
			newPlayer = new Coordinate(row - 1, col);
			// check if player is pushing a box
			if (stones.contains(newPlayer)) {
				Coordinate newStone = new Coordinate(row - 2, col);
				// update box coordinate
				stones.remove(newPlayer);
				stones.add(newStone);
			}
			break;
		case 'd':
			// update player coordinate
			newPlayer = new Coordinate(row + 1, col);
			// check if player is pushing a box
			if (stones.contains(newPlayer)) {
				Coordinate newStone = new Coordinate(row + 2, col);
				// update box coordinate
				stones.remove(newPlayer);
				stones.add(newStone);
			}
			break;
		case 'l':
			// update player coordinate
			newPlayer = new Coordinate(row, col - 1);
			// check if player is pushing a box
//			logger.log(Level.INFO, "PlayerLeft:" + newPlayer.x + "," + newPlayer.y);
			if (stones.contains(newPlayer)) {
				Coordinate newStone = new Coordinate(row, col - 2);
				// update box coordinate
				stones.remove(newPlayer);
				stones.add(newStone);
			}
			break;
		case 'r':
			// update player coordinate
			newPlayer = new Coordinate(row, col + 1);
			// check if player is pushing a box
//			logger.log(Level.INFO, "PlayerRight:" + newPlayer.x + "," + newPlayer.y);
			if (stones.contains(newPlayer)) {
//				logger.log(Level.INFO, "trigger");
				Coordinate newStone = new Coordinate(row, col + 2);
				// update box coordinate
				stones.remove(newPlayer);
				stones.add(newStone);
			}
			break;
		}

		return new GameState(current.getinput(), newPlayer, current.getWall(), stones, current.getGoal(), current, dir,
				newCost);
	}

	public String getSolution(GameState last, int totalNode, int redundant, int fringeSize, int exploredSize) {
		String result = "";
		int steps = 0;
		if (last == null) {
			result = "Failed to solve the puzzle";
		} else {
			while (last.getParent() != null) {
				result = last.getMove() + " " + result;
				last = last.getParent();
				steps++;
			}
		}
		result = "Using " + ":\n" + result + "\n(total of " + steps + " steps)" + "\na) Number of nodes generated: "
				+ totalNode + "\nb) Number of nodes containing states that were generated previously: " + redundant
				+ "\nc) Number of nodes on the fringe when termination occurs: " + fringeSize
				+ "\nd) Number of nodes on the explored list (if there is one) when termination occurs: "
				+ exploredSize;
		// "\ne) The actual run time of the algorithm, expressed in actual time
		// units: " + totalTime + "ms";
		return result;
	}

}
