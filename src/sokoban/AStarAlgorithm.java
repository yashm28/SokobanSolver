package sokoban;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class AStarAlgorithm extends SearchAlgorithm {

	final Logger logger = Logger.getLogger("AStarAlgorithm");

	private GameState current;
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer redundant;
	private Integer nodes;
	private PriorityQueue<GameState> queue = new PriorityQueue<>(11, new HeuristicComparatorAStar());
	private long start;
	private long end;

	public AStarAlgorithm() {
		this.redundant = 0;
		this.nodes = 0;
	}

	@Override
	public String solve(GameState initialState) {
		String solution = "Could not Solve Problem!";
		start = System.currentTimeMillis();
		this.current = initialState;
		queue.add(current);
		Integer x = 5000;
		while (!queue.isEmpty()) {
			if (traversed.size() == x) {
				System.out.println("nodes explored:" + x);
				x += 5000;
			}
			current = queue.poll();
			if (current.isSolved()) {
				end = System.currentTimeMillis();
				path = getPath(current);
				return getSolution(current, nodes, redundant, queue.size(), traversed.size(), start, end);
			}
			if (!current.deadlockTest(current)) {
				traversed.add(current);
				ArrayList<String> actions = current.valid(current);
				for (int i = 0; i < actions.size(); i++) {
					GameState child = getChildState(current, actions.get(i));
					if ((child != null)) {
						nodes++;
						if ((!traversed.contains(child)) && (!queue.contains(child))) {
							queue.add(child);
						} else {
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

}
