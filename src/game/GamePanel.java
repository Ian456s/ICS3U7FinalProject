package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import game.entities.Player;
import game.sprites.Images;

/**
 * GamePanel class to keep track of the actual game
 * @author Ian Tang
 */

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	private Background[] backgrounds = {new Background("/Backgrounds/Level1Background.jpg"), new Background("/Backgrounds/Level2Background.jpeg"), new Background("/Backgrounds/Level3Background.jpg")};
	Background bg;
	private Thread thread;
	private boolean isRunning = true;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	private GameManager gm;
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		setFocusable(true);
		new Images();
		start();
	}
	/**
	 * start method to create a new thread while the game is running
	 */
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * run method containing code that the game uses to function
	 */
	public void run() {
		long start, elapsed, wait;
		gm = new GameManager();
		while(isRunning) {
			start = System.nanoTime();
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			
			if(wait <= 0) {
				wait = 5;
			}
			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * tick method to retrieve data from the GameManager
	 */
	public void tick() {
		gm.tick();
	}
	/**
	 * paintComponent method to draw the background and game
	 */
	public void paintComponent(@SuppressWarnings("exports") Graphics g) {
		super.paintComponent(g);
		
		Color colors[] = {Color.black, new Color(214, 178, 15), new Color(77, 86, 255)};
		g.setColor(colors[Player.getLevel()-1]);
		bg = backgrounds[Player.getLevel()-1];
		g.drawRect(0, 0, WIDTH, HEIGHT);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		bg.draw((Graphics2D)g);
		try {
			gm.draw(g);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * keyTyped method keeping track of keys being typed (not used)
	 */
	
	public void keyTyped(@SuppressWarnings("exports") KeyEvent e) {}
	
	/**
	 * keyPressed method keeping track of any keys being pressed and retrieving data on what to do from the game manager
	 */
	
	public void keyPressed(@SuppressWarnings("exports") KeyEvent e) {
		try {
			gm.keyPressed(e.getKeyCode());
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | FontFormatException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * keyReleased method keeping track of any keys being released and retrieving data on what to do from the game manager
	 */
	
	public void keyReleased(@SuppressWarnings("exports") KeyEvent e) {
		gm.keyReleased(e.getKeyCode());
	}

}
