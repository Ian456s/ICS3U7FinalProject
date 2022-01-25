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
	JLabel backgroundLabel, titleLabel;
	
	TutorialPage() throws FontFormatException, IOException {
		
		background = new ImageIcon(getClass().getResource("/mainMenuBackground.jpg"));
		backgroundLabel = new JLabel(background);
		f = new JFrame("Our Lost Friend - Tutorial");
		f.setSize(1600, 900);
		f.add(backgroundLabel);
		back = new JButton("Back to Main Menu");
		back.setBounds(600, 500, 300, 60);
		backgroundLabel.setSize(1600, 900);
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		titleLabel = new JLabel(Title);
		makeLabel(titleLabel);
		
		
		
		makeButton(back);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
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
	}

}
