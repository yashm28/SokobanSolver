package sokoban;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

public class IDAStarAlgorithm extends SearchAlgorithm {

	final Logger logger = Logger.getLogger("AStarAlgorithm");
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer redundant;
	private Integer nodes;
	private long start;
	private long end;
	private String solution = "Could not Solve Problem!";
	private Boolean flag = false;
	private Integer iterations;

	public IDAStarAlgorithm() {
		this.redundant = 0;
		this.nodes = 0;
		iterations = 0;
	}

	@Override
	public String solve(GameState initialState) {
		Integer bound = initialState.getHeuristic();
		start = System.currentTimeMillis();
		traversed.add(initialState);
		while (true) {
			iterations++;
			Integer result = search(initialState, 0, bound);
			if (flag || result == Integer.MAX_VALUE) {
				return solution;
			}
			bound = result;
		}
	}

	@Override
	public Integer search(GameState state, Integer cost, Integer bound) {
		Integer heuristic = cost + state.getHeuristic();
		if (heuristic > bound) {
			return heuristic;
		}
		if (state.isSolved()) {
			end = System.currentTimeMillis();
			flag = true;
			solution = getSolution(state, nodes, redundant, iterations, traversed.size(), start, end);
			return heuristic;
		}
		Integer min = Integer.MAX_VALUE;
		ArrayList<String> actions = state.valid(state);
		for (String dir : actions) {
			nodes++;
			GameState childState = getChildState(state, dir);
			if(!traversed.contains(childState)){
				traversed.add(childState);
			}
			else{
				redundant++;
			}
			heuristic = search(childState, cost + 1, bound);
			if (flag) {
				return heuristic;
			}
			if (heuristic < min) {
				min = heuristic;
			}
		}
		return heuristic;
	}

}
