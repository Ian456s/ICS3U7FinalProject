package game;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
/**
 * Background class for rendering backgrounds efficiently within the game
 * @author Ian Tang
 *
 */
public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	public Background(String s) {
		
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * setPosition method to change the position of the background
	 * @param x - new x coordinate of background
	 * @param y - new y coordinate of background
	 */
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * setVector method to change the distance of the second background
	 * @param dx - distance in x coordinates
	 * @param dy - distance in y coordinates
	 */
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	/**
	 * update method to update the position of the backgrounds
	 */
	public void update() {
		x += dx;
		y += dy;
	}
	/**
	 * draw method to display the background onto a graphics
	 * @param g - Graphics
	 */
	public void draw(@SuppressWarnings("exports") Graphics2D g) {
		
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			g.drawImage(image,(int)x + GamePanel.WIDTH, (int)y, null);
		}
		if(x > 0) {
			g.drawImage(image,(int)x - GamePanel.WIDTH, (int)y, null);
		}
	}
	
}







