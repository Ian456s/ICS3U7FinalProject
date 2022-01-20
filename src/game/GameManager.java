package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameManager {
	
	private Stack<GameState> states;
	public GameManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	public void tick() {
		states.peek().tick();
	}
	
	public void draw(Graphics g) throws FontFormatException, IOException {
		
		states.peek().draw(g);
	}
	
	public void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		states.peek().keyPressed(k);
	}
	public void keyReleased(int k) {
		states.peek().keyReleased(k);
	}
}
