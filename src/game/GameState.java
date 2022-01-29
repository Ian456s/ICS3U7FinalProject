package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.entities.Player;
/**
 * GameState is an abstract class that the other game states of the game can use
 * @author Ian Tang
 *
 */
public abstract class GameState {
	
	protected static GameManager gm; //Game manager is essential
	public static double xOffset; //the offset of the player in the current state
	public static double yOffset; //the offset of the player in the current state
	public static double startX; //the offset of the player in the current state at the start
	public static double startY; //the offset of the player in the current state at the start
	public static double farthest; //the farthest the player has travelled within that state
	public GameState(GameManager gm) {
		GameState.gm = gm;
		//initializing offsets
		xOffset = 0;
		yOffset = 0;
		startX = 0;
		startY = 0;
		farthest = 0;
		init();
	}
	
	public abstract void init(); //what is happening in the game
	public abstract void tick(); //what is happening each tick 
	public abstract void draw(@SuppressWarnings("exports") Graphics g) throws FontFormatException, IOException; //what to draw each tick in the state
	public abstract void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException; //which keys are being pressed
	public abstract void keyReleased(int k); //which keys are being released
	
	/**
	 * menu method to make returning to the main menu easier
	 */
	public static void menu() {
		Player.setLevel(1);
		GameState.xOffset = GameState.startX;
		GameState.yOffset = GameState.startY;
		Player.setScore(0);
		gm.states.push(new MenuState(gm));
	}
}
