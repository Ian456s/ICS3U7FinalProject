package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public abstract class GameState {
	
	protected GameManager gm;
	public static double xOffset;
	public static double yOffset;
	
	public GameState(GameManager gm) {
		this.gm = gm;
		
		xOffset = 0;
		yOffset = 0;
		
		
		init();
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw(Graphics g) throws FontFormatException, IOException;
	public abstract void keyPressed(int k) throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException;
	public abstract void keyReleased(int k);
}
