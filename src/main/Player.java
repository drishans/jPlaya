package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import display.jPlayaGUI;
import utils.Utils;

@SuppressWarnings("unused")
public class Player {

	public Queue<File> playQ;
	private File songFile;
	private File[] album;
	private int songIndex;
	private boolean songIsPlaying;
	private int Current_Volume = 50;
	private ArrayList<Integer> shuffledIndex;
	static File log;
	boolean isShuffled;
	boolean repeat;
	static String searchTitle;
	static PrintWriter pw;

	public static void main(String[] args) {
		jPlayaGUI ui = new jPlayaGUI();
		log = new File("log.txt");
		try {
			pw = new PrintWriter(log);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Launching the Player!");
		pw.println("player opened " + System.currentTimeMillis());

		jPlayaGUI.main(args);
	}

	public int search(String title) {
		for (int i = 0; i < playQ.getLength(); i++) {
			searchTitle = title;
			Mp3File m = new Mp3File();
			// we can pop through the queue looking for a title match
			// more code needed here
			if (m.getTitle().equals(searchTitle)) {

				return i;
			}
		}
		return -1;
	}

	void Shuffle() {
		if (!isShuffled) {
			shuffledIndex = new ArrayList<Integer>();
			Random rnd = new Random();
			int index, a;
			for (int i = 0; i < shuffledIndex.size(); i++) {
				shuffledIndex.set(i, i);
			}
			for (int i = shuffledIndex.size() - 1; i > 0; i--) {
				index = rnd.nextInt(i + 1);
				a = shuffledIndex.get(index);
				shuffledIndex.set(index, shuffledIndex.get(i));
				shuffledIndex.set(i, a);
			}
		}
		isShuffled = true;
	}

}
