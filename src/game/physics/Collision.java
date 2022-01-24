package game.physics;

import java.awt.Point;

import game.objects.Block;
import game.objects.MovingBlock;

public class Collision {
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
	
	public static boolean playerMovingBlock(Point p, MovingBlock b) {
		return b.contains(p);
	}
}
