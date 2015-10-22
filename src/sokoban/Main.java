package sokoban;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = Main.class.getResource("").getPath() + "../../src/" + args[0];
		Init init = new Init();
		init.readFile(path);
		System.out.println(init.getMax());
		System.out.println(init.getPlayer().x + " " + init.getPlayer().y);
	}

}
