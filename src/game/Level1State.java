package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.entities.Player;
import game.objects.Block;

public class Level1State extends GameState{
	
	private Player player;
	private Block[] b;
	Image background;
	public Level1State(GameManager gm) {
		super(gm);
		
	}
	
	public void init() {
		player = new Player(30, 30);
		b = new Block[10];
		for(int i = 0; i < b.length; i++) {
			b[i] = new Block(i*100, i*100+600);
		}
		
	}

	public void tick() {	
		for(int i = 0; i < b.length; i++) {
			b[i].tick();
		}
		player.tick(b);
	}
	
	public void draw(Graphics g) throws FontFormatException, IOException {	
		
		player.draw(g);
		
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
		
	}

	public void keyPressed(int k)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {	
		player.keyPressed(k);
	}
	
	public void keyReleased(int k) {		
		player.keyReleased(k);
	}

}
