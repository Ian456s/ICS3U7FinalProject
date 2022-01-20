package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public class MenuState extends GameState {

	private String[] buttons = {"Resume", "Quit"};
	private int currentSelection = 0;
	Font buttonFont;
	Font biggerFont;
	public MenuState(GameManager gm) {
		super(gm);
	}

	public void init() {}

	public void tick() {
		
		
	}

	public void draw(Graphics g) throws FontFormatException, IOException {
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		for(int i = 0; i < buttons.length; i++) {
			if(i == currentSelection) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.white);
			}
			buttonFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/textFont.TTF"));  
			biggerFont = buttonFont.deriveFont(Font.BOLD, 80f);
			g.setFont(biggerFont);
			g.drawString(buttons[i], GamePanel.WIDTH/2 - 125, 150 + i * 300);
		}
		
	}

	public void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		if(k == KeyEvent.VK_DOWN) {
			currentSelection ++;
			if(currentSelection >= buttons.length) {
				currentSelection = 0;
			}
		} else if(k == KeyEvent.VK_UP) {
			currentSelection --;
			if(currentSelection < buttons.length) {
				currentSelection = buttons.length-1;
			}
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if(currentSelection == 0) {
				//play
			} else if(currentSelection == 1) {
				new MainMenu();
			}
		}
	}

	public void keyReleased(int k) {
		
		
	}

}
