package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.entities.Player;
import game.mapping.Map;
import game.objects.Block;

public class Level1State extends GameState{
	
	private Player player;
	private Block[] b;
	private Map map;
	Image background;
	public Level1State(GameManager gm) {
		super(gm);
		
	}
	
	public void init() {
		player = new Player(32, 32);
		map = new Map("/Maps/map1.map");
		xOffset = -200;
		yOffset = -1000;
		
	}

	public void tick() {	
		player.tick(map.getBlocks(), map.getMovingBlocks());
		map.tick();
	}
	
	public void draw(Graphics g) throws FontFormatException, IOException {	
		player.draw(g);
		map.draw(g);
		
	}

	public void keyPressed(int k)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {	
		player.keyPressed(k);
	}
	
	public void keyReleased(int k) {		
		player.keyReleased(k);
	}

}
