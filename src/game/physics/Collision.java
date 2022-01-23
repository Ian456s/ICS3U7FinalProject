package game.physics;

import java.awt.Point;

import game.objects.Block;

public class Collision {
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p);
	}
}
