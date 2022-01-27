package game.mapping;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import game.objects.Block;
import game.objects.MovingBlock;
/**
 * Map class for loading maps and retrieving data. 
 * @author Ian Tang
 *
 */
public class Map {
	
	private String path;
	private String line;
	private int width, height;
	private ArrayList<MovingBlock> movingBlocks;
	private Block[][] blocks;
	
	public Map(String mapPath) {
		path = mapPath;	
		loadMap();
	}
	/**
	 * draw method for drawing blocks onto the board.
	 * @param g
	 */
	public void draw (Graphics g) {
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[0].length; j++) {
				blocks[i][j].draw(g); //drawing blocks
			}
		}
		
		for(int i = 0; i < movingBlocks.size(); i++) {
			movingBlocks.get(i).draw(g); //drawing moving blocks
		}
	}
	/**
	 * loadMap method to load maps with given map file
	 */
	public void loadMap() {
		InputStream is = this.getClass().getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());

			blocks = new Block[height][width];	

			for(int y = 0; y < height; y++) {
				line = br.readLine();
				
				String[] tokens = line.split("\\s+");
				for(int x = 0; x < width; x++) {
					blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, Integer.parseInt(tokens[x]));
				}
			}		
			
			line = br.readLine();
			line = br.readLine();
			int length = Integer.parseInt(line);
			movingBlocks = new ArrayList<MovingBlock>(); //constructing ArrayList to hold moving blocks
			
			for(int i = 0; i < length; i++) {
				line = br.readLine();
				String[] tokens = line.split("\\s+");
				movingBlocks.add(new MovingBlock(Integer.parseInt(tokens[0]) * Block.blockSize, Integer.parseInt(tokens[1])*Block.blockSize, Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]) * Block.blockSize, Integer.parseInt(tokens[4]) * Block.blockSize));
			}
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
	/**
	 * tick method for whatever happens during a tick of the game
	 */
	public void tick() {
		for(int i= 0; i < movingBlocks.size(); i++) {
			movingBlocks.get(i).tick();
		}
	}
	/**
	 * getBlocks method to return the 2D plane of blocks
	 * @return - array of Blocks
	 */
	public Block[][] getBlocks() {
		return blocks;
	}
	/**
	 * getMovingBlocks method to return the 2D plane of moving blocks
	 * @return - array of MovingBlocks
	 */
	public ArrayList<MovingBlock> getMovingBlocks(){
		return movingBlocks;
	}
}
