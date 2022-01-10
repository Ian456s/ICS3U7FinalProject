package game;

import java.awt.Color;
import java.awt.Font;
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
	JLabel MyLabel, title;
	JButton continueButton;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 18);
	ImageIcon background, Title;
	private Clip clip;
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		new MainMenu();
	}
	TitleScreen() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		background = new ImageIcon(this.getClass().getResource("/forest.jpg"));
		Title = new ImageIcon(this.getClass().getResource("/title.png"));
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/bgm.wav").getPath())));
		f = new JFrame("Title");
		MyLabel = new JLabel(background);
		title = new JLabel(Title);
		f.setSize(1280, 720);
		MyLabel.setSize(1280, 720);
		title.setSize(980,60);
		title.setBounds(60, 100, 1158, 97);
		continueButton = new JButton("Continue...");
		continueButton.setBounds(590, 500, 150, 40);
		continueButton.addActionListener(this);
		continueButton.setFocusable(false);
		continueButton.setForeground(Color.white);
		continueButton.setOpaque(false);
		continueButton.setContentAreaFilled(false);
		continueButton.setBorderPainted(false);
		continueButton.setFont(buttonFont);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        MyLabel.add(continueButton);
        f.add(title);
        f.add(MyLabel);
		
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
			}
			
		}
		
	}
	
}
