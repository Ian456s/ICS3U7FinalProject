package game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.GameState;
import game.sprites.Images;
/**
 * MovingBlock class, a tile that can move from one x bound to another.
 * @author Ian Tang
 *
 */
public class MovingBlock extends Rectangle {
	private static final long serialVersionUID = 1L;

	private int leftBound, rightBound; //the left and right bounds the block moves between
	private int move = 2; //how much a block moves each tick
	private int id; //id of the block

	public MovingBlock(int x, int y, int id, int leftBound, int rightBound) {
		setBounds(x, y, Block.blockSize, Block.blockSize);
		this.id = id;
		this.leftBound = leftBound;
		this.rightBound = rightBound;
	}
	/**
	 * tick method for whatever happens during one tick of the game
	 */
	public void tick() {
		if(x + width - GameState.xOffset >= rightBound - GameState.xOffset && move != -1) { //if it has exceeded the right bound
			move *= -1; //change direction
		}
		if(x - GameState.xOffset <= leftBound - GameState.xOffset && move != 1) { //if it has exceeded the left bound
			move *= -1; //change direction
		}
		x += move; //move the block
		
	}
	/**
	 * draw method to draw the block onto the game
	 * @param g - Graphics
	 */
	public void draw(Graphics g) {
		if(id != 0) {
			g.drawImage(Images.blocks[id-1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height, null);
		}
	}

	/**
	 * getMove method to retrieve how many pixels the block is currently moving for each tick
	 * @return the amount of pixels, in integer form, the block moves each tick
	 */
	public int getMove() {
		return move;
	}
	/**
	 * getID method to retrieve the ID of this block
	 * @return the id of this block, in integer form.
	 */
	public int getID() {
		return id;
	}
}
