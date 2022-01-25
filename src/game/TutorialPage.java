package game;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class TutorialPage implements ActionListener {
	
	
	JButton back;
	
	
	TutorialPage() throws FontFormatException, IOException {
	
	
	back = new JButton("Back to Main Menu");
	
	
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
	
}
