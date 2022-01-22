package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.entities.Player;

public class Level1State extends GameState{
	
	private Player player;
	
	public Level1State(GameManager gm) {
		super(gm);
		
	}
	
	public void init() {
		player = new Player(30, 30);
	}

	public void tick() {	
		player.tick();
	}
	
	public void draw(Graphics g) throws FontFormatException, IOException {	
		player.draw(g);
	}

	public void keyPressed(int k)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {	
		player.keyPressed(k);
	}
	
	public void keyReleased(int k) {		
		player.keyReleased(k);
	}

}
