/**
 * Description	:Engine for the brick game
 * Copyright	:Copyright (c) 2014
 * Company		:Embla Software Innovations (Pvt) Ltd
 * Created on	:2014.09.01
 * @author 		:Chandimal
 * @version 	:1.0
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;

public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;

	BufferedImage buffer; // Create the buffer
	Ball ball;
	Bat bat;
	Brick brick;
	BufferedImage bg;
	BufferedImage ballImg;
	BufferedImage batImg;
	BufferedImage brickImg;
	
	boolean isLeft;
	boolean isRight;

	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
		buffer = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
		ball = new Ball(dim.width , dim.height, 200, 500, 10, 10, 1);
		bat = new Bat(dim.width , dim.height, (dim.width -100)/2, 500, 100, 10, 1);
		brick = new Brick(3,7);		
	}
	
	/**
	 * Start the game
	 */	
	public void Start() {

		while (true) {
			//Event info
			if (this.isLeft) bat.setLeft();
			if (this.isRight) bat.setRight();
			
			//Logic
			ball.update();				
			bat.update();			
			
			detectCollision();
			// Draw the buffer
			drawBuffer();
			// Paint the buffer on screen
			drawScreen();

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public void detectCollision() {
		Rectangle rectBall = new Rectangle(ball.getX(), ball.getY(), ball.getW(), ball.getH());
		Rectangle rectBat = new Rectangle(bat.getX(), bat.getY(), bat.getW(), bat.getH());
		//Temporary values
		Rectangle rectBrick = new Rectangle(0, 0, brick.getW(), brick.getH());
		
		if(rectBall.intersects(rectBat)) {
			ball.reverse();
		}
		if(rectBall.intersects(rectBrick)) {
			brick.destroy();
			ball.reverse();
		}
	}

	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
		Graphics2D b = buffer.createGraphics();
		// Black color background
		b.setColor(Color.BLACK);
		b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());		
		
		//border
//		b.setColor(Color.BLUE);
//		b.fillRect(0, 0, buffer.getWidth(), 10);
//		b.fillRect(0, 10, 10, buffer.getHeight()-10);
//		b.fillRect(buffer.getWidth()-10, 10, 10, buffer.getHeight()-10);
		
		//Brick
		brick.draw((Graphics2D)b);	
		
		//Baller color and Bat color
		b.setColor(Color.WHITE);
		b.fillOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());		
		b.fillRect(bat.getX(), bat.getY(), bat.getW(), bat.getH());
	}

	/**
	 * Update it to the screen
	 */
	public void drawScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Listen to key presses -left right
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() ==37 ) {
			this.isLeft = true;
			
		}
		
		if(e.getKeyCode() ==39 ) {
			this.isRight = true;
		}
	}

	/**
	 * Listen to key released -left right
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() ==37 ) {
			this.isLeft = false;
		}
		
		if(e.getKeyCode() ==39 ) {
			this.isRight = false;
		}
		
	}

}