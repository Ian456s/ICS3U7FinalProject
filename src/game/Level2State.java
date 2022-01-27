package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.entities.Player;
import game.mapping.Map;

public class Level2State extends GameState{
	
	private Player player;
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
		farthest = startX;
	}

	public void tick() {	
		player.tick(map.getBlocks(), map.getMovingBlocks());
		map.tick();
		System.out.println(xOffset + ", " + yOffset);
		if(xOffset > 4000) {
			Player.setLevel(3);
			gm.states.push(new Level3State(gm));
		}
	}
	
	public void draw(@SuppressWarnings("exports") Graphics g) throws FontFormatException, IOException {	
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
