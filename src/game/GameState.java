package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class GameState {
	
	protected static GameManager gm;
	public static double xOffset;
	public static double yOffset;
	public static double startX;
	public static double startY;
	public static double farthest;
	public GameState(GameManager gm) {
		GameState.gm = gm;
		
		xOffset = 0;
		yOffset = 0;
		startX = 0;
		startY = 0;
		farthest = 0;
		init();
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw(@SuppressWarnings("exports") Graphics g) throws FontFormatException, IOException;
	public abstract void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException;
	public abstract void keyReleased(int k);
	
	public static void menu() {
		gm.states.push(new MenuState(gm));
	}
}
