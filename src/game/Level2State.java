package game;

import java.awt.Color;
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

public class Level2State extends GameState{
	
	private Player player;
	private Block[] b;
	private Map map;
	
	public Level2State(GameManager gm) {
		super(gm);
		
	}
	
	public void init() {
		player = new Player(30, 30);
		map = new Map("/Maps/map2.map");
		xOffset = -795;
		yOffset = -420;
		startX = xOffset;
		startY = yOffset;
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