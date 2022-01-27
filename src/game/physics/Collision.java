package game.physics;

import java.awt.Point;

import game.objects.Block;
import game.objects.MovingBlock;
/**
 * Collision method to check if the player is currently making contact to a block
 * @author Ian Tang
 *
 */
public class Collision {
	/**
	 * playerBlock method for checking if the player is making contact with a block
	 * @param p - the point that is being checked
	 * @param b - the block that is being checked
	 * @return a boolean depending on whether or not the point is within the block.
	 */
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
	/**
	 * playerMovingBlock method for checking if the player is currently making contact with a moving block
	 * @param p - the point of which is being checked
	 * @param b - the block that is being checked
	 * @return a boolean depending on whether or not the point is within the block.
	 */
	public static boolean playerMovingBlock(Point p, MovingBlock b) {
		return b.contains(p);
	}
}
