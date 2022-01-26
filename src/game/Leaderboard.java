package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;


public class Leaderboard implements ActionListener {
	JFrame f;
	String fileName = "scores.txt";
	String userName = Start.getUser();
	static int numUsers = Start.getUsers();
	String[][] accounts = Start.getAccounts();
	Font buttonFont, biggerFont, textFont = new Font("Verdana", Font.BOLD, 14);
	JLabel backgroundLabel, titleLabel, signedInAs, scores;
	ImageIcon background, Title;
	JButton back;
	PlayerType[] topScores = new PlayerType[10];
	public static PlayerType[] players;
	Leaderboard() throws FontFormatException, IOException {
		//initial declarations
		background = new ImageIcon(this.getClass().getResource("/mainMenuBackground.jpg"));
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));  
		biggerFont = buttonFont.deriveFont(Font.BOLD, 36f);
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		players = Game.getPlayers();
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(1600, 900);
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>");
		signedInAs.setFont(textFont);
		signedInAs.setForeground(Color.white);
		signedInAs.setBounds(1400, -10, 210, 100);
		f = new JFrame("Our Lost Friend - Leaderboard");
		f.setSize(1600, 900);
		titleLabel = new JLabel(Title);
		titleLabel.setSize(980,60);
		titleLabel.setBounds(180, 100, 1158, 97);
		scores = new JLabel();
		displayLabel();
		//button creation
		back = new JButton("Back to Main Menu");
		makeBackButton(back);
		backgroundLabel.add(signedInAs);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(titleLabel);
		f.add(backgroundLabel);

	}
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
	private void makeTextLabel(JLabel l) {
		l.setFont(biggerFont);
		l.setForeground(Color.white);
		backgroundLabel.add(l);
	}
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
	@Override
	public void actionPerformed(ActionEvent e) {
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
	
	public static void updateScore(String playerName, int updatedScore) {
		for(int i = 0; i < numUsers; i++) {
			if(players[i].getName().equals(playerName)) {
				players[i].setScore(updatedScore);
			}
		}
	}
	

}
