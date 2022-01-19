package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
	static int numUsers = Start.getUsers();
	static PlayerType[] players = new PlayerType[numUsers];
	static String[][] accounts = Start.getAccounts();
	String username = Start.getUser();
	static int userNum;
	public Game() {
		for(int i = 0; i < numUsers; i++) {
			if(players[i].getName().equals(username)) {
				userNum = i;
			}
		}
		int curScore = 0;
		while(true) {
			updateScore(curScore);
		}
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
	
	public void updateScore(int updatedScore) {
		players[userNum].setScore(updatedScore);
	}
	
}
