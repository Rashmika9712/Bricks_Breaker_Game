import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Brick extends GameEntity implements Draw{
	
	public Brick(int gameW, int gameH, int startX, int startY, int w, int h, int speed) {
		super(gameW, gameH, startX, startY, w, h, speed);
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawObject(Graphics2D g, ImageObserver observer) {
		try {
			BufferedImage buffer = ImageIO.read(new File("./img/brick.png"));
			g.drawImage(buffer, x, y, observer);
		} catch (IOException e) {
			g.setColor(Color.WHITE);
			g.fillRect(getX(), getY(), getW(), getH());
			e.printStackTrace();
		}
		
	}		
}
