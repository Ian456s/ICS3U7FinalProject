package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontFormatException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	static int numUsers = Start.getUsers();
	static PlayerType[] players;
	static String[][] accounts = Start.getAccounts();
	String username = Start.getUser();
	static int userNum;
	static String fileName = "accounts.txt";
	static BufferedWriter out;
	static JFrame frame;
	
	public Game() throws IOException {
		players = new PlayerType[numUsers];
		Game.getPlayers();
		Game.getUserNum(username);
		
		
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
		accounts[2][userNum] = updatedScore + "";
	}
	
	public static void saveData() {
		try {
			out = new BufferedWriter(new FileWriter(fileName));
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < numUsers; j++) {
					out.write(accounts[i][j] + " ");
				}
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int getUserNum(String user) {
		for(int i = 0; i < numUsers; i++) {
			if(accounts[0][i].equals(user))return i;
		}
		return 0;
	}
	public static void close() {
		frame.dispose();
		try {
			new MainMenu();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
}
