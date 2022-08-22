import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball extends GameEntity implements Draw{		
	
	private int dy;
	
	public Ball(int gameW, int gameH, int startX, int startY, int w, int h, int speed) {
		super(gameW, gameH, startX, startY,w, h, speed);	
		
		dx = speed;
		dy = speed;
	}
	
	@Override
	public void update() {		
		x = x + dx;
		y = y + dy;			
		
		if(x <=0 ) {
			dx = +speed;
		}
		if(x + w >= gameW) {
			dx = -speed;
		}
		
		if(y <=0 ) {
			dy = +speed;
		}
		if(y + h >= gameH) {
			dy = -speed;
		}
	}		
	
	public void reverse()
	{
		dy = -speed;
	}

	@Override
	public void drawObject(Graphics2D g, ImageObserver observer) {
		// TODO Auto-generated method stub
		try {
			BufferedImage buffer = ImageIO.read(new File("./img/ball.png"));
			g.drawImage(buffer, x, y, observer);
		} catch (IOException e) {
			g.setColor(Color.WHITE);
			g.fillOval(getX(), getY(), getW(), getH());
			e.printStackTrace();
		}
	}
}
