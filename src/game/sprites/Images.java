package game.sprites;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
public class Images {
	public static BufferedImage[] blocks;
	public Images() {
		blocks = new BufferedImage[2];
		try {
			blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/grass.jpg"));
			blocks[1] = ImageIO.read(getClass().getResourceAsStream("/Blocks/movingBlock.png")); 
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
}
