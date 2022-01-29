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
 * TitleScreen.java is a class that creates a window for a title screen before the main menu.
 * @author Ian Tang
 *
 */
public class TitleScreen implements ActionListener {
	//global variable declaration
	String userName = Start.getUser();
	JFrame f;
	JLabel backgroundLabel, titleLabel;
	JButton continueButton;
	Font buttonFont, biggerFont;
	ImageIcon background, Title;
	private Clip clip;
	
	TitleScreen() throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		//initializing variables
		background = new ImageIcon(this.getClass().getResource("/Backgrounds/LoadingBackground.jpg")); //fetching background image
		Title = new ImageIcon(this.getClass().getResource("/title.png")); //fetching title image
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF")); //fetching font  
		biggerFont = buttonFont.deriveFont(Font.BOLD, 24f); //deriving font to different font size
		clip = AudioSystem.getClip(); //initializing clip
        clip.open(AudioSystem.getAudioInputStream(getClass().getResource(new File("bgm.wav").getPath()))); //fetching audio
		f = new JFrame("Our Lost Friend - Title"); //initializing JFrame
		backgroundLabel = new JLabel(background); //initializing backgroundLabel
		titleLabel = new JLabel(Title); //initializing titleLabel
		f.setSize(1280, 720); //setting size of JFrame
		backgroundLabel.setSize(1280, 720); //setting size of backgroundLabel
		titleLabel.setSize(980,60); //setting size of titleLabel
		titleLabel.setBounds(60, 100, 1158, 97); //setting position of titleLabel
		continueButton = new JButton("Continue"); //initializing JButton
		continueButton.setBounds(590, 500, 150, 40); //setting position of continueButton 
		makeContinueButton(continueButton);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true); //enabling visibility
		f.setLocationRelativeTo(null); //centering frame
		clip.loop(Clip.LOOP_CONTINUOUSLY); // looping music
        clip.start(); //starting music
        f.add(titleLabel);
        f.add(backgroundLabel);
		
	}
	
	/**
	 * actionPerformed method to keep track of the user's actions
	 */
	
	public void actionPerformed(@SuppressWarnings("exports") ActionEvent e) {
		if(e.getSource() == continueButton) {
			try {
				new MainMenu();
				clip.stop();
				f.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (FontFormatException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				System.out.println("Error, please move the files bgm.wav and OurLostFriend.wav to the \"game\" folder within \"bin\".");
			}
			
		}
		
	}
	
	/**
	 * makeContinueButton to store code that will change the appearance of the continue button
	 * @param continueButton
	 */
	
	public void makeContinueButton(@SuppressWarnings("exports") JButton continueButton) {
		continueButton.addActionListener(this);
		continueButton.setFocusable(false);
		continueButton.setForeground(Color.white);
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		continueButton.setFont(biggerFont);
		backgroundLabel.add(continueButton);
	}
}
