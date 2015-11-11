package sokoban;

import java.util.ArrayList;

public class ManhattanHeuristic {

	public ManhattanHeuristic() {

	}

	public Integer getManhattanDistance(Coordinate c1, Coordinate c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

	public Integer calculateHeuristic(GameState state) {
		ArrayList<Coordinate> goal = state.getGoal();
		ArrayList<Coordinate> stone = state.getStone();
		Coordinate player = state.getPlayer();
		Integer cost = 0;
		for (Coordinate co : stone) {
			Integer minCost = Integer.MAX_VALUE;
			for (Coordinate c : goal) {
				Integer manhattan = getManhattanDistance(c, co);
				if (manhattan < minCost) {
					minCost = manhattan;
				}
			}
			cost += minCost;
		}
		for (Coordinate c : stone) {
			Integer playerCost = getManhattanDistance(player, c);
			cost += playerCost;
		}
		return cost;
	}

}
