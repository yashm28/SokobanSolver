package sokoban;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = Main.class.getResource("").getPath() + "../../src/" + args[0];
		Init init = new Init();
		init.readFile(path);
		GameState initialState = init.getInitialState();
		printState(initialState.getinput());
		AStarAlgorithm astar = new AStarAlgorithm();
		String answer = astar.solve(initialState);
		System.out.println(answer);
	}

	public static void printState(ArrayList<String> game) {
		Integer rows = game.size();
		for (int i = 0; i < rows; i++) {
			System.out.println(game.get(i));
		}
	}

}
