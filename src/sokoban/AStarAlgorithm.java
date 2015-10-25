package sokoban;

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarAlgorithm {

	private GameState current;
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer visited;
	private HashMap<GameState, GameState> route = new HashMap<GameState, GameState>();
	private PriorityQueue<GameState> queue = new PriorityQueue<GameState>();

	public AStarAlgorithm() {
		this.visited = 0;
	}

	public String solve(GameState initialState) {
		String solution = null;
		this.current = initialState;
		queue.add(current);
		while(!queue.isEmpty()){
			current = queue.poll();
		}
		return solution;

	}

}
