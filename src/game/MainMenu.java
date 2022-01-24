package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu implements ActionListener{
	String userName = Start.getUser();
	JFrame f;
	JButton exitGame, leaderboard, gameStart, tutorial, changePassword;
	JLabel backgroundLabel, titleLabel, authors, signedInAs;
	Font buttonFont, biggerFont;
	Font textFont = new Font("Verdana", Font.BOLD, 14);
	boolean changedPass;
	Clip clip;
	ImageIcon background, Title;
	
	MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		background = new ImageIcon(getClass().getResource("/mainMenuBackground.jpg"));
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));  
		biggerFont = buttonFont.deriveFont(Font.BOLD, 40f);
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(getClass().getResource(new File("OurLostFriend.wav").getPath())));
		changedPass = false;
		f = new JFrame("Our Lost Friend - Main Menu - Ian Tang & Naveed Khan");
		backgroundLabel = new JLabel(background);
		titleLabel = new JLabel(Title);
		authors = new JLabel("<html><p style=\"width:200px\">"+"By: Ian Tang & Naveed Khan <br> Class: ICS3U7-01 <br> Teacher: Mrs. Xie"+"</p></html>");
		makeLabel(authors);
		authors.setBounds(10, -10, 300, 100);
		signedInAs = new JLabel("<html><p style=\"width:200px\">"+"Currently signed in as: <br>" + userName +"</p></html>");
		makeLabel(signedInAs);
		signedInAs.setBounds(1400, -10, 210, 100);
		f.setSize(1600, 900);
		backgroundLabel.setSize(1600, 900);
		titleLabel.setSize(980,60);
		titleLabel.setBounds(180, 100, 1158, 97);
		gameStart = new JButton("Start Game");
		gameStart.setBounds(600, 300, 300, 60);
		tutorial = new JButton("How to Play");
		tutorial.setBounds(600, 400, 300, 60);
		leaderboard = new JButton("Leaderboard");
		leaderboard.setBounds(600, 500, 300, 60);
		exitGame = new JButton("Exit Game");
		exitGame.setBounds(600, 600, 300, 60);
		changePassword = new JButton("Change Password");
		changePassword.setBounds(550, 700, 400, 60);
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
	public void makeButton(JButton b) {
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
	public void makeLabel(JLabel l) {
		l.setForeground(Color.white);
		l.setFont(textFont);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gameStart) {
			try {
				new Game();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			clip.stop();
			f.dispose();
		} else if (e.getSource() == tutorial) {
			notYetMade();
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

	public void reprompt() {
		int result = JOptionPane.showConfirmDialog(f,
				"Are you sure you want to quit?",
				"Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
		if (result == JOptionPane.YES_OPTION) System.exit(0);
	}
	public void notYetMade() {
		JOptionPane.showMessageDialog(f, "Hey! This hasn't been added to our game yet!");
	}
}
