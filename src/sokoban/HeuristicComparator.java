package sokoban;

import java.util.Comparator;

public class HeuristicComparator implements Comparator<GameState> {

	@Override
	public int compare(GameState state0, GameState state1) {
		return ((state0.getCost() + state0.getHeuristic()) - (state1.getCost() + state1.getHeuristic()));
	}

}
