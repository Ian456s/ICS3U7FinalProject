package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * GameManager class to manage different screens within the game.
 * @author Ian Tang
 *
 */
public class GameManager {
	
	public Stack<GameState> states;
	public GameManager() {
		states = new Stack<GameState>();
		states.push(new MenuState(this));
	}
	
	/**
	 * tick method for whatever happens during one tick of the game
	 */
	public void tick() {
		states.peek().tick();
	}
	
	/**
	 * draw method to project everything onto the screen
	 * @param g - Graphics
	 * @throws FontFormatException
	 * @throws IOException
	 */
	public void draw(@SuppressWarnings("exports") Graphics g) throws FontFormatException, IOException {
		states.peek().draw(g);
	}
	
	/**
	 * keyPressed method directs key presses to the current state of the game
	 * @param k
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @throws FontFormatException
	 */
	
	public void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {
		states.peek().keyPressed(k);
	}
	
	/**
	 * keyReleased method directs any key being released to the current state of the game
	 * @param k
	 */
	public void keyReleased(int k) {
		states.peek().keyReleased(k);
	}

}
