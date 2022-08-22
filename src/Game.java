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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Game extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;

	BufferedImage buffer; // Create the buffer
	Ball ball;
	Bat bat;
	Brick brick;
//	BufferedImage bg;
//	BufferedImage ballImg;
//	BufferedImage batImg;
//	BufferedImage brickImg;
	
	private int totalBrics = 20;
	private int brickW = 38;
	private int brickH = 18;
	private int brickS = 11;
	List<GameEntity> gEntity;
	
	boolean isLeft;
	boolean isRight;
	boolean isReversed;
	/**
	 * Create the game using the width and the height specified
	 */
	public Game(Dimension dim) {
		buffer = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		this.setIgnoreRepaint(true); // Ignore repainting as we are doing all
		ball = new Ball(dim.width , dim.height, 150, 450, 10, 10, 1);
		bat = new Bat(dim.width , dim.height, (dim.width -100)/2, 500, 100, 10, 1);
		
		int brickX = brickS + 2;
		int brickY = brickS + 2;
		gEntity = new ArrayList<>();
		for(int i = 0; i < totalBrics; i++) {
			gEntity.add(new Brick(dim.width , dim.height, brickX, brickY, brickW, brickH, 0));
			if(brickX + brickW > dim.width - brickS + 2) {
				brickY += brickH;
				brickX = brickS +2;
			}
			else {
				brickX += brickW;
			}
		}		
		gEntity.addAll(Arrays.asList(ball,bat));
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
			for (GameEntity e : gEntity)
				e.update();			
			
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
		List<Brick> destroy = new ArrayList<>();
		gEntity.stream().filter(entity-> entity instanceof Ball).forEach(ball -> {
			Rectangle rectBall = new Rectangle(ball.getX(), ball.getY(), ball.getW(), ball.getH());
		
			//Bat collision
			gEntity.stream().filter(entity -> entity instanceof Bat).forEach(bat -> {
				Rectangle rectBat = new Rectangle(bat.getX(), bat.getY(), bat.getW(), bat.getH());
			
				if(rectBall.intersects(rectBat)) {
					((Ball)ball).reverse();
				}
			});
			
			//Brick Collision			
			gEntity.stream().filter(entity -> entity instanceof Brick).forEach(brick -> {
				Rectangle rectBrick = new Rectangle(brick.getX(), brick.getY(), brick.getW(), brick.getH());

				if (rectBall.intersects(rectBrick)) {
					if (!isReversed) {
						((Ball) ball).reverse();
						isReversed = true;
					}
					destroy.add((Brick) brick);
				}
			});
			
		});	
		isReversed = false;
		for (Brick b : destroy) {
			gEntity.remove(b);			
		}
	}

	/**
	 * Draw the image buffer
	 */
	public void drawBuffer() {
		Graphics2D b = buffer.createGraphics();
		BufferedImage image;
		// Black color background
//		b.setColor(Color.BLACK);
//		b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());	
//		
		try {
			image = ImageIO.read(new File("./img/bg.png"));
			b.drawImage(image, 0, 0, this);
		} catch (IOException e) {

			// Black color background
			b.setColor(Color.BLACK);
			b.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
			e.printStackTrace();
		}
		
		
		
		//Baller color and Bat color
		b.setColor(Color.WHITE);
		//b.fillOval(ball.getX(), ball.getY(), ball.getW(), ball.getH());		
		b.fillRect(bat.getX(), bat.getY(), bat.getW(), bat.getH());
		
		gEntity.stream().filter(entity -> entity instanceof Draw).forEach(entity -> {
			((Draw) entity).drawObject(b, this);
		});

		
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