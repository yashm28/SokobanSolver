package sokoban;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DFS extends SearchAlgorithm {

	private GameState current;
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer redundant;
	private Integer nodes;
	private Stack<GameState> stack = new Stack<GameState>();
	private long start;
	private long end;

	public DFS() {
		nodes = 0;
		redundant = 0;
	}

	@Override
	public String solve(GameState initialState) {
		String solution = "Could not Solve Problem!";
		start = System.currentTimeMillis();
		this.current = initialState;
		stack.push(current);
		Integer x = 5000;
		while (!stack.isEmpty()) {
			if (traversed.size() == x) {
				System.out.println("nodes explored:" + x);
				x += 5000;
			}
			current = stack.pop();
			if (current.isSolved()) {
				end = System.currentTimeMillis();
				return getSolution(current, nodes, redundant, stack.size(), traversed.size(), start, end);
			}
			if (!current.deadlockTest(current)) {
				traversed.add(current);
				ArrayList<String> actions = current.valid(current);
				for (int i = 0; i < actions.size(); i++) {
					GameState child = getChildState(current, actions.get(i));
					if ((child != null)) {
						nodes++;
						if ((!traversed.contains(child)) && (!stack.contains(child))) {
							stack.push(child);
						} else {
							redundant++;
							for (GameState next : stack) {
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
