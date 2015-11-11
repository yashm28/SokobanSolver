package sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Init {

	private Integer rows;
	private Integer max = 0;
	private Coordinate player = new Coordinate();
	private ArrayList<Integer> cols = new ArrayList<Integer>();
	private ArrayList<String> input = new ArrayList<String>();
	private ArrayList<Coordinate> wall = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> stone = new ArrayList<Coordinate>();
	private ArrayList<Coordinate> goal = new ArrayList<Coordinate>();

	public Init() {

	}

	public void readFile(String path) throws IOException {
		File file = new File(path);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while (line != null) {
			input.add(line);
			line = br.readLine();
		}
		br.close();
		rows = input.size();
		for (int i = 0; i < rows; i++) {
			char[] array = input.get(i).toCharArray();
			Integer length = array.length;
			cols.add(length);
			if (length > max) {
				max = length;
			}
			for (int j = 0; j < array.length; j++) {
				if (array[j] == '#') {
					wall.add(new Coordinate(i, j));
				}
				if (array[j] == '$' || array[j] == '*') {
					stone.add(new Coordinate(i, j));
				}
				if (array[j] == '@' || array[j] == '+') {
					player = new Coordinate(i, j);
				}
				if (array[j] == '.' || array[j] == '*' || array[j] == '+') {
					goal.add(new Coordinate(i, j));
				}
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	public GameState getInitialState() {
		return new GameState(input, player, wall, stone, goal, null, null);
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getMax() {
		return max;
	}

	public ArrayList<Integer> getCols() {
		return cols;
	}

}
