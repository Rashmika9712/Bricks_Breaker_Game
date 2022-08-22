import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bat extends GameEntity implements Draw{		

	public Bat(int gameW, int gameH,  int startX, int startY, int w, int h, int speed) {
		super(gameW, gameH, startX, startY, w, h, speed);			
	}
	
	public void setLeft() {
		dx = -speed;
	}
	
	public void setRight() {
		dx = speed;
	}
		
	@Override
	public void update() {
		x = x + dx;
		
		if(x <= 0) {
			x = 0 ;
		}
		if(x >= gameW - w) {
			x = gameW - w;
		}
		dx = 0;
	}

	@Override
	public void drawObject(Graphics2D g, ImageObserver observer) {
		// TODO Auto-generated method stub
		try {
			BufferedImage buffer = ImageIO.read(new File("./img/bat.png"));
			g.drawImage(buffer, x, y, observer);
		} catch (IOException e) {
			g.setColor(Color.WHITE);
			g.fillRect(getX(), getY(), getW(), getH());
			e.printStackTrace();
		}
	}
}