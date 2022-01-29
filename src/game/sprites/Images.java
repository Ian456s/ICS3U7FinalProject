package game.sprites;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;

/**
 * Images class for rendering images for our game
 */

public class Images {
	public static BufferedImage[] blocks;
	public Images() {
		blocks = new BufferedImage[4];
		try {
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/grass.jpg"));
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/movingBlock.png")); 
			blocks[2] = ImageIO.read(getClass().getResourceAsStream("/Blocks/waterBlock.gif"));
			blocks[3] = ImageIO.read(getClass().getResourceAsStream("/Blocks/sandBlock.jpg"));
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
}
