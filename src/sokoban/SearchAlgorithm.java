package sokoban;

import java.util.ArrayList;

public class SearchAlgorithm {

	protected String path = null;

	public String solve(GameState initialState) {
		return null;
	}

	public Integer search(GameState state, Integer cost, Integer bound) {
		return null;
	}

	public Boolean search(GameState state, Integer depth) {
		return null;
	}

	public GameState getChildState(GameState state, String dir) {
		@SuppressWarnings("unchecked")
		ArrayList<Coordinate> stones = (ArrayList<Coordinate>) state.getStone().clone();
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
				dir = "U";
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
				dir = "D";
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
			if (stones.contains(newPlayer)) {
				dir = "L";
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
			if (stones.contains(newPlayer)) {
				dir = "R";
				Coordinate newStone = new Coordinate(row, col + 2);
				// update box coordinate
				stones.remove(newPlayer);
				stones.add(newStone);
			}
			break;
		}

		return new GameState(state.getinput(), newPlayer, state.getWall(), stones, state.getGoal(), state, dir,
				newCost);
	}

	public String getPath(GameState last) {
		String result = "";
		if (last == null) {
			result = "Failed to solve the puzzle";
		} else {
			while (last.getParent() != null) {
				result = last.getMove() + result;
				last = last.getParent();
			}
		}
		return result;
	}

	public String getAnwer() {
		return path;
	}

	public String getSolution(GameState last, int totalNode, int redundant, int fringeSize, int exploredSize,
			long start, long end) {
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
		long totalTime = (end - start);
		result = "Using " + ":\n" + result + "\n(total of " + steps + " steps)" + "\na) Number of nodes generated: "
				+ totalNode + "\nb) Number of nodes containing states that were generated previously: " + redundant
				+ "\nc) Number of nodes on the fringe when termination occurs(Number of iterations for IDAStar,IDDFS): "
				+ fringeSize + "\nd) Number of nodes on the explored list (if there is one) when termination occurs: "
				+ exploredSize + "\ne) The actual run time of the algorithm, expressed in actual time units: "
				+ totalTime + "ms";
		return result;
	}

}
