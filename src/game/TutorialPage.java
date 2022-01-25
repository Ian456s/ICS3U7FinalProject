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
	JLabel backgroundLabel, titleLabel, text;
	Font buttonFont, biggerFont;
	
	TutorialPage() throws FontFormatException, IOException {
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));
        biggerFont = buttonFont.deriveFont(Font.BOLD, 20f);
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		background = new ImageIcon(getClass().getResource("/mainMenuBackground.jpg"));
		backgroundLabel = new JLabel(background);
		titleLabel = new JLabel(Title);
		titleLabel.setSize(980,60);
		titleLabel.setBounds(180, 100, 1158, 97);
		text = new JLabel("In this game the objective is to get to the end of the level. Press the a and d keys to move and press the space bar to jump.");
		text.setBounds(300, 250, 1700, 180);
		f = new JFrame("Our Lost Friend - Tutorial");
		f.setSize(1600, 900);
		f.add(backgroundLabel);
		back = new JButton("Back to Main Menu");
		back.setBounds(800, 700, 500, 260);
		backgroundLabel.setSize(1600, 900);
		
		makeLabel(text);
		makeLabel(titleLabel);
		
		
		
		makeButton(back);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.add(text);
		f.add(titleLabel);
		f.add(backgroundLabel);
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
	
	public void makeButton(JButton b) {
		b.setFont(buttonFont);
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
		l.setFont(biggerFont);
		l.setForeground(Color.white);
	}

}
