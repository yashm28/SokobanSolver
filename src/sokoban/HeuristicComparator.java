package sokoban;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HeuristicComparator implements Comparator<GameState> {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public int compare(GameState state0, GameState state1) {
		Integer costDiff = (state0.getCost() + state0.getHeuristic()) - (state1.getCost() + state1.getHeuristic());
		return costDiff;
	}

}
