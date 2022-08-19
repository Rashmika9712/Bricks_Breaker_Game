
public class Ball extends GameEntity{		
	
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
}
