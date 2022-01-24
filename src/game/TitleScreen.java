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

public class TitleScreen implements ActionListener {
	String userName = Start.getUser();
	JFrame f;
	JLabel backgroundLabel, titleLabel;
	JButton continueButton;
	Font buttonFont, biggerFont;
	ImageIcon background, Title;
	private Clip clip;
	
	TitleScreen() throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		//initializing variables
		background = new ImageIcon(this.getClass().getResource("/forest.jpg"));
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));  
		biggerFont = buttonFont.deriveFont(Font.BOLD, 24f);
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("bgm.wav").getPath())));
		f = new JFrame("Our Lost Friend - Title");
		 backgroundLabel = new JLabel(background);
		titleLabel = new JLabel(Title);
		f.setSize(1280, 720);
		backgroundLabel.setSize(1280, 720);
		titleLabel.setSize(980,60);
		titleLabel.setBounds(60, 100, 1158, 97);
		continueButton = new JButton("Continue");
		continueButton.setBounds(590, 500, 150, 40);
		continueButton.addActionListener(this);
		continueButton.setFocusable(false);
		continueButton.setForeground(Color.white);
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		continueButton.setFont(biggerFont);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        backgroundLabel.add(continueButton);
        f.add(titleLabel);
        f.add(backgroundLabel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
}
