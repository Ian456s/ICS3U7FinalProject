package game;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu implements KeyListener {
	String userName = Start.getUser();
	JFrame f;
	JLabel MyLabel;
	Font buttonFont = new Font("Comic Sans",Font.BOLD, 14);
	ImageIcon background;
	private Clip clip;
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		new MainMenu();
	}
	MainMenu() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		background = new ImageIcon(this.getClass().getResource("/forest.png"));
		clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(new File(getClass().getResource("/bgm.wav").getPath())));
		f = new JFrame("Main Menu");
		MyLabel = new JLabel(background);
		f.setSize(1024, 577);
		MyLabel.setSize(1024, 577);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.addKeyListener(this);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
		f.add(MyLabel);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
