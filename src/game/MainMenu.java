package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu implements ActionListener{
	String userName = Start.getUser();
	JFrame f;
	JButton exitGame, leaderboard, gameStart, tutorial;
	JLabel MyLabel, title, authors, signedInAs;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 30);
	Font textFont = new Font("Verdana", Font.BOLD, 14);
	Clip clip;
	ImageIcon background, Title;
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		new MainMenu();
	}
	MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		background = new ImageIcon(this.getClass().getResource("/mainMenuBackground.jpg"));
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/OurLostFriend.wav").getPath())));
		f = new JFrame("Our Lost Friend - Ian Tang & Naveed Khan");
		MyLabel = new JLabel(background);
		title = new JLabel(Title);
		authors = new JLabel("<html><p style=\"width:200px\">"+"By: Ian Tang & Naveed Khan <br> Class: ICS3U7-01 <br> Teacher: Mrs. Xie"+"</p></html>");
		makeLabel(authors);
		authors.setBounds(10, -10, 300, 100);
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>");
		makeLabel(signedInAs);
		signedInAs.setBounds(1400, -10, 210, 100);
		f.setSize(1600, 900);
		MyLabel.setSize(1600, 900);
		title.setSize(980,60);
		title.setBounds(180, 100, 1158, 97);
		gameStart = new JButton("Start Game");
		gameStart.setBounds(600, 300, 300, 60);
		tutorial = new JButton("How to Play");
		tutorial.setBounds(600, 400, 300, 60);
		leaderboard = new JButton("Leaderboard");
		leaderboard.setBounds(600, 500, 300, 60);
		exitGame = new JButton("Exit Game");
		exitGame.setBounds(600, 600, 300, 60);
		makeButton(gameStart);
		makeButton(tutorial);
		makeButton(leaderboard);
		makeButton(exitGame);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
		f.add(signedInAs);
		f.add(authors);
        f.add(title);
        f.add(MyLabel);
		
	}
		public void makeButton(JButton b) {
			b.setFont(buttonFont);
			b.setForeground(Color.white);
			b.setBackground(new Color(36, 100, 187));
			b.addActionListener(this);
			b.setFocusable(false);
			b.setOpaque(false);
			b.setContentAreaFilled(false);
			b.setBorderPainted(false);
			MyLabel.add(b);
		}
		public void makeLabel(JLabel l) {
			l.setForeground(Color.white);
			l.setFont(textFont);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	
}
