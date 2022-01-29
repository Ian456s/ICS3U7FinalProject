package game;

import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import game.entities.Player;
import game.mapping.Map;
/**
 * Level2State class is a GameState class for the second level of the game.
 * @author Ian Tang
 *
 */
public class Level2State extends GameState{
	//variable declaration
	private Player player; //creating player
	private Map map; //creating map
	
	public Level2State(GameManager gm) {
		super(gm);
		
	}
	
	/**
	 * init method for what happens when we are in the game
	 */
	
	public void init() {
		player = new Player(30, 30); //initializing player
		map = new Map("/Maps/map2.map"); //initializing new map
		xOffset = -795; //creating xOffset to match the start of the level
		yOffset = -420; //creating yOffset to match the start of the level
		startX = xOffset; //creating startX for resets
		startY = yOffset; //creating startY for resets
		farthest = startX; //initializing the farthest distance the player has made it
	}

	/**
	 * tick method for what happens each tick within the game 
	 */
	
	public void tick() {	
		player.tick(map.getBlocks(), map.getMovingBlocks()); //ticking the player with the map's blocks and moving blocks
		map.tick(); //ticking the map
		if(xOffset > 12000) { //whenever the player reaches past the end of the level
			Player.setLevel(3); //the background is set to the third level's
			gm.states.push(new Level3State(gm)); //game manager pushes the 3rd level's state, switching the map
		}
	}
	
	/**
	 * draw method for the graphics of the current level
	 */
	
	public void draw(@SuppressWarnings("exports") Graphics g) throws FontFormatException, IOException {	
		player.draw(g); //drawing player
		map.draw(g); //drawing map
	}
	
	/**
	 * keyPressed method to keep track of the user's actions within the level
	 */
	
	public void keyPressed(int k)
			throws IOException, LineUnavailableException, UnsupportedAudioFileException, FontFormatException {	
		player.keyPressed(k); //sending data to player, then performing the actions
	}
	
	/**
	 * keyReleased method to keep track of the user's keys that are released within the level 
	 */
	
	public void keyReleased(int k) {		
		player.keyReleased(k); //sending the data to the player, then performing the actions
	}

}
