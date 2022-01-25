package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class TutorialPage implements ActionListener {

	JFrame f;
	JButton back;
	ImageIcon background, Title;
	JLabel backgroundLabel, titleLabel, text, text2, text3;
	Font buttonFont, biggerFont, textFont;
	
	TutorialPage() throws FontFormatException, IOException {
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));
        textFont = buttonFont.deriveFont(Font.BOLD, 24f);
		biggerFont = buttonFont.deriveFont(Font.BOLD, 32f);
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		background = new ImageIcon(getClass().getResource("/mainMenuBackground.jpg"));
		backgroundLabel = new JLabel(background);
		backgroundLabel.setSize(1600, 900);
		titleLabel = new JLabel(Title);
		titleLabel.setSize(980,60);
		titleLabel.setBounds(180, 100, 1158, 97);
		makeLabel(titleLabel);
<<<<<<< HEAD
		back = new JButton("Back to Main Menu");
		makeBackButton(back);
		text = new JLabel("When the game begins, you will be taken to a menu.  Navigate by using the");
		text.setBounds(280, 250, 1300, 30);
		text2 = new JLabel("up and down arrow keys, as well as the enter key to select.");
		text2.setBounds(280, 285, 1300, 50);
		text3 = new JLabel("In this game the objective is to get to the end of the level. Press the a and d keys to move and press the space bar to jump.");
		text3.setBounds(280, 400, 1300, 50);
=======
		text = new JLabel("In this game the objective is to get to the end of the level. Press the a and d keys to move and press the space bar to jump.");
		text.setBounds(200, 220, 1300, 180);
>>>>>>> a095b5c8ea59495df1e8cd3fa4d0b53eb8a59b05
		makeLabel(text);
		makeLabel(text2);
		makeLabel(text3);
		f = new JFrame("Our Lost Friend - Tutorial");
		f.setSize(1600, 900);
		
		f.add(titleLabel);
		f.add(backgroundLabel);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

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
	
	private void makeBackButton(JButton b) {
		b.setFont(biggerFont);
		b.setForeground(Color.white);
		b.setBackground(new Color(36, 100, 187));
		b.addActionListener(this);
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorderPainted(false);
		b.setBounds(360, 700, 500, 100);
		backgroundLabel.add(b);
	}
	
	public void makeLabel(JLabel l) {
		l.setFont(textFont);
		l.setForeground(Color.white);
		backgroundLabel.add(l);
	}

}
