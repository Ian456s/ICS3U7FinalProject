package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * Leaderboard class to create a window that displays the leaderboard of top ten scores
 * @author Ian Tang
 *
 */
public class Leaderboard implements ActionListener {
	//variable declaration
	JFrame f;
	String fileName = "scores.txt";
	String userName = Start.getUser(); //fetching username
	static int numUsers = Start.getUsers(); //fetching number of users
	String[][] accounts = Start.getAccounts(); //fetching 2D array of account details
	Font buttonFont, biggerFont, textFont = new Font("Verdana", Font.BOLD, 14); //declaring fonts
	JLabel backgroundLabel, titleLabel, signedInAs, scores; //declaring JLabels
	ImageIcon background, Title; //declaring images
	JButton back; //declaring the back button
	public static PlayerType[] players; //array of PlayerType, containing their username and password
	Leaderboard() throws FontFormatException, IOException {
		//initialization of variables
		background = new ImageIcon(this.getClass().getResource("/Backgrounds/mainMenuBackground.jpg")); //fetching background image
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));   //fetching font
		biggerFont = buttonFont.deriveFont(Font.BOLD, 36f); //deriving font and setting different size
		Title = new ImageIcon(this.getClass().getResource("/title.png")); //fetching title image
		players = Game.getPlayers(); //fetching the player
		backgroundLabel = new JLabel(background); //initializing background label
		backgroundLabel.setSize(1600, 900); //setting size of background label
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>"); //initializing signedInAs label
		signedInAs.setFont(textFont); //setting font
		signedInAs.setForeground(Color.white); //changing text colour
		signedInAs.setBounds(1400, -10, 210, 100); //setting position
		f = new JFrame("Our Lost Friend - Leaderboard"); //initializing JFrame
		f.setSize(1600, 900); //setting size of frame
		titleLabel = new JLabel(Title); //initializing title label
		titleLabel.setSize(980,60); //setting size of title label
		titleLabel.setBounds(180, 100, 1158, 97); //setting position of title label
		scores = new JLabel();
		displayLabel();
		//button creation
		back = new JButton("Back to Main Menu"); //initializing back button
		makeBackButton(back); 
		backgroundLabel.add(signedInAs);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(titleLabel);
		f.add(backgroundLabel);

	}
	
	/**
	 * displayLabel method to generate text and put onto screen after getting the top scores
	 */
	private void displayLabel() {
		PlayerType.sortPlayers(players);
		if(numUsers >= 10) {
			for(int i = 9; i >= 0; i--) {
				String score = 10-i + ". " + players[numUsers-(10-i)].getName() + ", " + players[numUsers-(10-i)].getScore();
				scores = new JLabel(score);
				scores.setBounds(600, (10-i)*50, 1000, 50);
				makeTextLabel(scores);
			}
		}
		if(numUsers < 10) {
			for(int i = numUsers; i > 0; i--) {
				String score = numUsers-i+1 + ". " + players[numUsers-(numUsers-i+1)].getName() + ", " + players[numUsers-(numUsers-i+1)].getScore();
				scores = new JLabel(score);
				scores.setBounds(600, (10-i)*50, 1000, 50);
				makeTextLabel(scores);
			}
			
		}
		
	}
	
	/**
	 * makeTextLabel to store code that will be used to change the appearance of labels
	 * @param l - JLabel being changed
	 */
	private void makeTextLabel(JLabel l) {
		l.setFont(biggerFont);
		l.setForeground(Color.white);
		backgroundLabel.add(l);
	}
	
	/**
	 * makeBackButton method to store code that will be used to change the appearance of the back button
	 * @param b
	 */
	private void makeBackButton(JButton b) {
		b.setFont(biggerFont);
		b.setForeground(Color.white);
		b.setBackground(new Color(36, 100, 187));
		b.addActionListener(this);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setBounds(60, 650, 500, 100);
		backgroundLabel.add(b);
	}
	
	/**
	 * actionPerformed method to keep track of the user's actions
	 */
	
	public void actionPerformed(@SuppressWarnings("exports") ActionEvent e) {
		if(e.getSource() == back) {
				try {
					new MainMenu();
					f.dispose();
				} catch (IOException | LineUnavailableException | UnsupportedAudioFileException
						| FontFormatException e1) {
					e1.printStackTrace();
				}
		}
		
	}
	
	/**
	 * updateScore method to change a specific player's score
	 * @param playerName - specific player's username
	 * @param updatedScore - specific player's score
	 */
	
	public static void updateScore(String playerName, int updatedScore) {
		for(int i = 0; i < numUsers; i++) {
			if(players[i].getName().equals(playerName)) {
				players[i].setScore(updatedScore);
			}
		}
	}
	

}
