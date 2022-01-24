package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	static int numUsers = Start.getUsers();
	static PlayerType[] players = new PlayerType[numUsers];
	static String[][] accounts = Start.getAccounts();
	String username = Start.getUser();
	static int userNum;
	static String fileName = "accounts.txt";
	static BufferedWriter out;
	static JFrame frame;
	
	public Game() throws IOException {
		frame = new JFrame("Our Lost Friend");
		frame.setSize(1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static int getUserScore(String username) {
		return players[userNum].getScore();
	}

	public static PlayerType[] getPlayers() {
		for(int i = 0; i < numUsers; i++)
			players[i] = new PlayerType();
		for(int i = 0; i < numUsers; i++) {
			players[i].setName(accounts[0][i]);
			players[i].setScore(Integer.valueOf(accounts[2][i]));
		}
		return players;
	}
	
	public static void saveScore(int updatedScore) {
		players[userNum].setScore(updatedScore);
		accounts[userNum][2] = updatedScore + "";
	}
	
	public static void saveData() {
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < numUsers; j++) {
					out.write(accounts[i][j]);
				}
				out.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void close() {
		frame.dispose();
	}
	
}
