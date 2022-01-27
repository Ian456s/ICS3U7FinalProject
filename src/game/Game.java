package game;
import java.awt.BorderLayout;
import java.awt.FontFormatException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Game class contains a frame that projects a game panel.
 * @author - Ian Tang
 */
public class Game extends JFrame {
	private static final long serialVersionUID = 1L;
	static int numUsers = Start.getUsers();
	public static PlayerType[] players = new PlayerType[numUsers];
	static String[][] accounts = Start.getAccounts();
	String username = Start.getUser();
	static int userNum;
	static String fileName = "accounts.txt";
	static BufferedWriter out;
	static JFrame frame;
	
	public Game() throws IOException {
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
	
	/**
	 * getUserScore method to get the player's current score
	 * @param username - username of current player
	 * @return - current score of current player
	 */
	
	public static int getUserScore(String username) {
		return players[userNum].getScore();
	}

	/**
	 * getPlayers method fills the players array with PlayerType objects, containing the username and score of a player.
	 * @return - returns an array of PlayerType
	 */
	
	public static PlayerType[] getPlayers() {
		for(int i = 0; i < numUsers; i++)
			players[i] = new PlayerType();
		for(int i = 0; i < numUsers; i++) {
			players[i].setName(accounts[0][i]);
			players[i].setScore(Integer.valueOf(accounts[2][i]));
		}
		return players;
	}
	
	/**
	 * saveScore method to change the score of the current player
	 * @param updatedScore - the new score the player has achieved
	 */
	
	public static void saveScore(int updatedScore) {
		players[userNum].setScore(updatedScore);
		accounts[2][userNum] = updatedScore + "";
	}
	
	/**
	 * saveData method to write the data of the current playerbase, containing their usernames, passwords and scores
	 * onto a text file named accounts.txt.
	 */
	
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
	
	/**
	 * getUserNum method to iterate through the current players in the database and find what number they are.
	 * @param user - username of current player
	 * @return - the player's number according to the text file accounts.txt
	 */
	
	public static int getUserNum(String user) {
		for(int i = 0; i < numUsers; i++) {
			if(accounts[0][i].equals(user))return i;
		}
		return 0;
	}
	
	/**
	 * close method to return to the main menu.
	 */
	
	public static void close() {
		frame.dispose();
		try {
			new MainMenu();
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * end method to signify to the user that they have completed the game and are returning to the main menu.
	 */
	
	public static void end() {
		JOptionPane.showMessageDialog(frame, "You have made it to the end of the game! Congratulations!");
		
	}
	
}
