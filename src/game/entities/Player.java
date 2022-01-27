package game.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import game.Game;
import game.GamePanel;
import game.GameState;
import game.Start;
import game.objects.Block;
import game.objects.MovingBlock;
import game.physics.Collision;

/**
 * Player class for movement around the game, as well as displaying score and a background.
 * @author Ian Tang
 *
 */
public class Player {

	private static int curLevel = 1;
	private static double score = 0;
	//movement booleans
	private boolean right = false, left = false, jumping = false, falling = false;
	private boolean topCollision = false;
	//bounds
	private double x, y;
	private int width, height;

	//move speed
	private double moveSpeed = 5;

	//jump speed
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;

	//fall speed
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	public String username = Start.getUser();
	public Player(int width, int height) {
		x = GamePanel.WIDTH/2;
		y = GamePanel.HEIGHT/2;
		this.width = width;
		this.height = height;
	}

	/**
	 * The tick method embeds all the necessary processes for the game that must be executed each tick. 
	 * @param b - an array of blocks the game has to render and put into effect
	 * @param movingBlocks - an ArrayList of moving blocks that the game has to render and put into effect
	 */
	public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks) {
		
		int iX = (int)x;
		int iY = (int)y;
		for(int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {

				if(b[i][j].getID() != 0) {

					//right side collision
					if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j]) 
							|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset, iY+height + (int)GameState.yOffset - 2), b[i][j])) {
						right = false;
					}
					//left side collision
					if(Collision.playerBlock(new Point(iX+(int)GameState.xOffset, iY + (int)GameState.yOffset + 2), b[i][j]) 
							|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset, iY+height+ (int)GameState.yOffset - 2), b[i][j])) {
						left = false;
					}
					//top
					if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i][j]) 
							|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i][j])) {
						jumping = false;
						falling = true;
					}

					//bottom

					if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i][j]) 
							|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), b[i][j])) {
						y = b[i][j].getY() - height - GameState.yOffset;
						falling = false;
						topCollision = true;
					} else {
						if(!topCollision && !jumping) {
							falling = true;
						}
					}
				}
			}	
		}

		for(int i = 0; i < movingBlocks.size(); i++) {

			if(movingBlocks.get(i).getID() != 0) {

				//right side collision
				if(Collision.playerMovingBlock(new Point(iX + width + (int)GameState.xOffset, iY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
						|| Collision.playerMovingBlock(new Point(iX + width + (int)GameState.xOffset, iY+height + (int)GameState.yOffset - 1), movingBlocks.get(i))) {
					right = false;
				}
				//left side collision
				if(Collision.playerMovingBlock(new Point(iX+(int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), movingBlocks.get(i)) 
						|| Collision.playerMovingBlock(new Point(iX + (int)GameState.xOffset - 1, iY+height+ (int)GameState.yOffset - 1), movingBlocks.get(i))) {
					left = false;
				}
				//top
				if(Collision.playerMovingBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), movingBlocks.get(i)) 
						|| Collision.playerMovingBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), movingBlocks.get(i))) {
					jumping = false;
					falling = true;
				}

				//bottom

				if(Collision.playerMovingBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), movingBlocks.get(i)) 
						|| Collision.playerMovingBlock(new Point(iX + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), movingBlocks.get(i))) {
					y = movingBlocks.get(i).getY() - height - (int)GameState.yOffset;
					falling = false;
					topCollision = true;

					GameState.xOffset += movingBlocks.get(i).getMove();
				} else {
					if(!topCollision && !jumping) {
						falling = true;
					}
				}
			}

		}

		topCollision = false;

		if(right) { //when player is moving right (holding d)
			GameState.xOffset += moveSpeed; //xOffset increases, moving the screen as well as the character
			if(GameState.xOffset > GameState.farthest) { //if the player moves past their original "highscore point"
				GameState.farthest = GameState.xOffset; //the player's new highscore point is their current position
				score+=0.1; //score increases
			}
		}
		if(left) { //when player is moving left (holding a)
			GameState.xOffset -= moveSpeed; //xOffset decreases, moving the screen to the left as well as the character
		}

		if(jumping) { //whenever the player jumps (presses space bar)
			GameState.yOffset -= currentJumpSpeed; 

			currentJumpSpeed -= .1;

			if(currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}

		if(falling) { //whenever the player is in midair and falling
			GameState.yOffset += currentFallSpeed;

			if(currentFallSpeed < maxFallSpeed) { //when the currentFallSpeed has not met its peak
				currentFallSpeed += 0.1; //increase the fallSpeed
			}
			if(GameState.yOffset >= 500) { //when player falls below 500 pixels player dies
				death();
			}
		}

		if(!falling) { //resetting fall speed every time the player lands
			currentFallSpeed = 0.1;
		}
		if(score > Game.getUserScore(username)) {  //if the user has achieved a new highscore
			Game.saveScore((int) (score)); //save this score
			Game.saveData(); //save this data
		}
	}

	public void draw(Graphics g) throws IOException {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height); //filling frame with player icon
		g.setFont(new Font("Times New Roman", Font.BOLD, 20)); //setting font
		g.drawString("Score: " + (int)score, 1400, 50); //display of score
	}
	/**
	 * keyPressed method keeps track of the different keys the user is pushing and in turn results in different events happening.
	 * @param k - key being pressed
	 */
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_D)right = true;
		if(k == KeyEvent.VK_A)left = true;
		if(k == KeyEvent.VK_SPACE && !jumping & !falling)jumping = true;
		if(k == KeyEvent.VK_ESCAPE) {
			GameState.menu();
			GameState.xOffset = GameState.startX;
			GameState.yOffset = GameState.startY;
			score = 0;
		}
	}
	/**
	 * death method to reset player's position to start of level.
	 */
	public void death() {
		GameState.xOffset = GameState.startX;
		GameState.yOffset = GameState.startY;
	}
	/**
	 * keyReleased method keeps track of the different keys the user is releasing and in turn results in different events happening.
	 * @param k - key being released
	 */
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_D)right = false;
		if(k == KeyEvent.VK_A)left = false; 
	}
	/**
	 * setLevel method to change the curent level's number.
	 * @param l - level the current level is changing to
	 */
	public static void setLevel(int l) {
		curLevel = l;
	}
	/**
	 * getScore is a method to return the player's current score.
	 * @return - returns the score
	 */
	public static int getScore() {
		return (int)score;
	}
	/**
	 * getLevel is a method to return the current level the player is on.
	 * @return - returns the level, in the form of an integer.
	 */
	public static int getLevel() {
		return curLevel;
	}

}
