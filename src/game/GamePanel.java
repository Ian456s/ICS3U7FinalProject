package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import game.sprites.Images;

public class GamePanel extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 900;
	
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
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
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
	
	public void tick() {
		gm.tick();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		
		try {
			gm.draw(g);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		try {
			gm.keyPressed(e.getKeyCode());
		} catch (IOException | LineUnavailableException | UnsupportedAudioFileException | FontFormatException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void keyReleased(KeyEvent e) {
		gm.keyReleased(e.getKeyCode());
	}

}
