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
		String solution = astar.getAnwer();
		displayGame(initialState, solution);
	}

	public static void printState(ArrayList<String> game) {
		Integer rows = game.size();
		for (int i = 0; i < rows; i++) {
			System.out.println(game.get(i));
		}
	}

	public static void displayGame(GameState initial, String solution) {
		ArrayList<String> game = initial.getinput();
		Integer x = initial.getPlayer().x;
		Integer y = initial.getPlayer().y;
		System.out.println();
		printState(game);
		for (int i = 0; i < solution.length(); i++) {
			System.out.println();
			String row = game.get(x);
			char[] array = row.toCharArray();
			switch (row.charAt(y)) {
			case '@':
				array[y] = ' ';
				row = String.valueOf(array);
				game.set(x, row);
				break;
			case '+':
				array[y] = '.';
				row = String.valueOf(array);
				game.set(x, row);
				break;
			}
			switch (solution.charAt(i)) {
			case 'U':
				char[] uparray2 = game.get(x - 2).toCharArray();
				switch (uparray2[y]) {
				case ' ':
					uparray2[y] = '$';
					game.set(x - 2, String.valueOf(uparray2));
					break;
				case '.':
					uparray2[y] = '*';
					game.set(x - 2, String.valueOf(uparray2));
					break;
				}
			case 'u':
				char[] uparray = game.get(x - 1).toCharArray();
				switch (uparray[y]) {
				case '$':
				case ' ':
					uparray[y] = '@';
					game.set(x - 1, String.valueOf(uparray));
					break;
				case '.':
				case '*':
					uparray[y] = '+';
					game.set(x - 1, String.valueOf(uparray));
					break;
				}
				x = x - 1;
				break;
			case 'D':
				char[] downarray2 = game.get(x + 2).toCharArray();
				switch (downarray2[y]) {
				case ' ':
					downarray2[y] = '$';
					game.set(x + 2, String.valueOf(downarray2));
					break;
				case '.':
					downarray2[y] = '*';
					game.set(x + 2, String.valueOf(downarray2));
					break;
				}
			case 'd':
				char[] downarray = game.get(x + 1).toCharArray();
				switch (downarray[y]) {
				case '$':
				case ' ':
					downarray[y] = '@';
					game.set(x + 1, String.valueOf(downarray));
					break;
				case '.':
				case '*':
					downarray[y] = '+';
					game.set(x + 1, String.valueOf(downarray));
					break;
				}
				x = x + 1;
				break;
			case 'L':
				switch (array[y - 2]) {
				case ' ':
					array[y - 2] = '$';
					game.set(x, String.valueOf(array));
					break;
				case '.':
					array[y - 2] = '*';
					game.set(x, String.valueOf(array));
					break;
				}
			case 'l':
				switch (array[y - 1]) {
				case '$':
				case ' ':
					array[y - 1] = '@';
					game.set(x, String.valueOf(array));
					break;
				case '.':
				case '*':
					array[y - 1] = '+';
					game.set(x, String.valueOf(array));
					break;
				}
				y = y - 1;
				break;
			case 'R':
				System.out.println("\"" + array[y + 2] + "\"");
				switch (array[y + 2]) {
				case ' ':
					array[y + 2] = '$';
					game.set(x, String.valueOf(array));
					break;
				case '.':
					array[y + 2] = '*';
					game.set(x, String.valueOf(array));
					break;
				}
			case 'r':
				switch (array[y + 1]) {
				case '$':
				case ' ':
					array[y + 1] = '@';
					game.set(x, String.valueOf(array));
					break;
				case '.':
				case '*':
					array[y + 1] = '+';
					game.set(x, String.valueOf(array));
					break;
				}
				y = y + 1;
				break;
			}
			printState(game);
			System.out.println();
		}
	}
}
