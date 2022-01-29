package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 * MainMenu class to display a window for the main menu
 * @author Ian Tang
 *
 */
public class MainMenu implements ActionListener{
	//variable declaration
	String userName = Start.getUser(); //fetching username from the Start class
	JFrame f;
	JButton exitGame, leaderboard, gameStart, tutorial, changePassword;
	JLabel backgroundLabel, titleLabel, authors, signedInAs;
	Font buttonFont, biggerFont;
	Font textFont = new Font("Verdana", Font.BOLD, 14); //declaring font
	boolean changedPass;
	Clip clip;
	ImageIcon background, Title;
	
	public MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		//initialization of variables
		background = new ImageIcon(getClass().getResource("/Backgrounds/mainMenuBackground.jpg")); // fetching background image
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF")); //fetching font
		biggerFont = buttonFont.deriveFont(40f); //deriving font into bigger size
		Title = new ImageIcon(this.getClass().getResource("/title.png")); //fetching title image
		clip = AudioSystem.getClip();  //initializing clip
        clip.open(AudioSystem.getAudioInputStream(getClass().getResource(new File("OurLostFriend.wav").getPath()))); //fetching audio
		changedPass = false; //initializing boolean
		f = new JFrame("Our Lost Friend - Main Menu - Ian Tang & Naveed Khan"); //initializing new JFrame
		backgroundLabel = new JLabel(background); //initializing new background label
		titleLabel = new JLabel(Title); //initializing new title label
		authors = new JLabel("<html><p style=\"width:200px\">"+"By: Ian Tang & Naveed Khan <br> Class: ICS3U7-01 <br> Teacher: Mrs. Xie"+"</p></html>"); //initializing new authors label
		makeLabel(authors);
		authors.setBounds(10, -10, 300, 100); //setting position of label
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>"); //initializing new signedInAs label
		makeLabel(signedInAs);
		signedInAs.setBounds(1400, -10, 210, 100); //setting position of label
		f.setSize(1600, 900); //setting size of frame
		backgroundLabel.setSize(1600, 900); //setting size of label
		titleLabel.setSize(980,60); //setting size of label 
		titleLabel.setBounds(180, 100, 1158, 97); //setting position of label
		gameStart = new JButton("Start Game"); //initializing new button for starting the game
		gameStart.setBounds(600, 300, 300, 60); //setting position of game button
		tutorial = new JButton("How to Play"); //initializing new button for the tutorial
		tutorial.setBounds(600, 400, 300, 60); //setting position of tutorial button
		leaderboard = new JButton("Leaderboard"); //initializing new button for the leaderboard
		leaderboard.setBounds(600, 500, 300, 60); //setting position of leaderboard 
		exitGame = new JButton("Exit Game"); //initializing new button to exit the game
		exitGame.setBounds(600, 600, 300, 60); //setting position of exit button
		changePassword = new JButton("Change Password"); //initializing new button to change the current user's password
		changePassword.setBounds(550, 700, 400, 60); //setting position of change password button
		makeButton(gameStart);
		makeButton(tutorial);
		makeButton(leaderboard);
		makeButton(exitGame);
		makeButton(changePassword);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
		f.add(signedInAs);
		f.add(authors);
		f.add(titleLabel);
		f.add(backgroundLabel);

	}
	
	/**
	 * makeButton method to store code that will change the appearance of a button
	 * @param b - button being manipulated
	 */
	
	public void makeButton(@SuppressWarnings("exports") JButton b) {
		b.setFont(biggerFont);
		b.setForeground(Color.white);
		b.setBackground(new Color(36, 100, 187));
		b.addActionListener(this);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		backgroundLabel.add(b);
	}
	
	/**
	 * makeLabel method to store code that will change the appearance of a label
	 * @param l - label being manipulated
	 */
	
	public void makeLabel(@SuppressWarnings("exports") JLabel l) {
		l.setForeground(Color.white);
		l.setFont(textFont);
	}
	
	/**
	 * actionPerformed method to keep track of the user's actions 
	 */
	
	public void actionPerformed(@SuppressWarnings("exports") ActionEvent e) {
		if(e.getSource() == gameStart) {
			try {
				new Game();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			clip.stop();
			f.dispose();
		} else if (e.getSource() == tutorial) {
			try {
				new TutorialPage();
				f.dispose();
				clip.stop();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			}
			clip.stop();
			f.dispose();
		} else if (e.getSource() == leaderboard) {
			try {
				new Leaderboard();
				f.dispose();
				clip.stop();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == exitGame) {
			reprompt();
		} else if (e.getSource() == changePassword) {
			if(!changedPass) {
				try {
					new changePassword();
					changedPass = true;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(f, "You've already changed your password!");
			}
		}

	}

	/**
	 * reprompt method to display window for user to confirm their exit
	 */
	
	public void reprompt() {
		int result = JOptionPane.showConfirmDialog(f,
				"Are you sure you want to quit?",
				"Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) System.exit(0);
	}
}
