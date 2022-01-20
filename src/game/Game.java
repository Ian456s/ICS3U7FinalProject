package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;

public class Game implements ActionListener {
	static int numUsers = Start.getUsers();
	static PlayerType[] players = new PlayerType[numUsers];
	static String[][] accounts = Start.getAccounts();
	String username = Start.getUser();
	static int userNum;
	String fileName = "accounts.txt";
	BufferedWriter out;
	JFrame frame;
	
	public Game() throws IOException {
		frame = new JFrame("Platformer");
		frame.setSize(1600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.add(new GamePanel(), BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static int getUserScore(String username) {
		return players[userNum].getScore();
	}
	
	public void actionPerformed(ActionEvent e) {
		
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
	}

	
}
