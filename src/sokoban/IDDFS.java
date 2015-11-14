package sokoban;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

public class IDDFS extends SearchAlgorithm {
	
	final Logger logger = Logger.getLogger("AStarAlgorithm");
	private HashSet<GameState> traversed = new HashSet<GameState>();
	private Integer redundant;
	private Integer nodes;
	private long start;
	private long end;
	private String solution = "Could not Solve Problem!";
	private Integer iterations;
	
	public IDDFS() {
		this.redundant = 0;
		this.nodes = 0;
		iterations = 0;
	}
	
	@Override
	public String solve(GameState initialState) {
		start = System.currentTimeMillis();
		for(int depth = 0; ; depth++){
			iterations++;
			Boolean flag = search(initialState, depth);
			if(flag != null){
				return solution;
			}
		}
	}
	
	@Override
	public Boolean search(GameState state, Integer depth) {
		if(depth == 0 && state.isSolved()){
			end = System.currentTimeMillis();
			solution = getSolution(state, nodes, redundant, iterations, traversed.size(), start, end);
			return true;
		}
		else if(depth > 0){
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
				Boolean flag = search(childState, depth-1);
				if(flag != null){
					return true;
				}
			}
		}
		return null;
	}

}
