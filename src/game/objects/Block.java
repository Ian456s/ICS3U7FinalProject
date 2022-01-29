package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.GameState;
import game.sprites.Images;
/**
 * Block class - one of the tiles in the game
 * @author Ian Tang
 *
 */
public class Block extends Rectangle{
	private static final long serialVersionUID = 1L;

	public static final int blockSize = 64; //size of one block (width and height)
	private int id; //id of the block, corresponding with the image that it is represented by
	public Block(int x, int y, int id) {
		setBounds(x,y,blockSize,blockSize);
		this.id = id;
	}

	/**
	 * tick method for whatever happens during one tick of the game
	 */
	
	public void tick() {
	}
	/**
	 * Method to draw a block into the game.
	 * @param g - Graphics
	 */
	public void draw(Graphics g) {
		g.setColor(Color.black); //default color to black if there is no icon
		if(id != 0) {
			g.drawImage(Images.blocks[id-1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}
	
	//getters and setters
	/**
	 * setID method to change the ID of this block
	 * @param id - new Integer id being assigned
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * getID method to retrieve the ID of this block
	 * @return - the id of the block, in Integer form
	 */
	public int getID() {
		return id;
	}
}
