package game.mapping;

import game.objects.Block;

public class Map {
	
	private String path;
	private int width, height;
	
	private Block[][] blocks;
	public Map(String mapPath, int width, int height) {
		path = mapPath;
		this.width = width;
		this.height = height;
		
		blocks = new Block[height][width];
	}
}
